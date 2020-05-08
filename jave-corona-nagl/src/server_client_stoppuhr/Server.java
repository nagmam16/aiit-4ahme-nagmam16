package server_client_stoppuhr;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Nagl
 */

public class Server {
    private ServerSocket serverSocket;
    final List<ConnectionHandler> handlers = new LinkedList<>();
    private long timeOffset;
    private long startMillis;
    
    public void start(int port) throws IOException{
        serverSocket = new ServerSocket(port);
	timeOffset = 0;
        
        while(true){ //damit nur 3 verbindungen gleichzeitig verbunden sind // eine vierte wird nicht zugelassen
            final Socket clientSocket = serverSocket.accept();
            if(handlers.size() < 3){ // prüfen ob 3 Verbindungen vorhanden
		final ConnectionHandler handler = new ConnectionHandler(clientSocket);
                new Thread(handler).start(); //hintergrund Thread
		handlers.add(handler);
            } else {
		clientSocket.close();
            }
        }
    }
    
    public boolean isTimerRunning(){
        return startMillis > 0;
    }

    public long getTimerMillis() {
        if(startMillis == 0) {
           return timeOffset;
        }
        
        return timeOffset + (System.currentTimeMillis() - startMillis);
    }
    
    public static void main(String[] args) throws IOException {
        final Server server = new Server();
        server.start(8080);
    }

//------------------------------------------------------------------------------
//------------------------------------------------------------------------------

    private class ConnectionHandler implements Runnable {
	private final Socket socket;
	private boolean master;

	public ConnectionHandler(Socket socket) {
	    this.socket = socket;
	}

	public boolean isClosed() throws IOException {
	    if(!socket.isConnected()){
		socket.close();
	    }
	    return socket.isClosed();
	}

	public boolean isMaster() {
	    return master;
	}

	@Override
	public void run() {
	    try{
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line;
		line = reader.readLine(); // zeichen werden in das attribut line gespeichert

		Gson gson = new Gson(); 
		gson.toJson(line); // die einkommenden Zeilen werden in ein Object gespeichert
		Request r = gson.fromJson(line, Request.class); //neues Request Object, welches die Zeichen beinhaltet

		if(r.isMaster()) {
		    for(ConnectionHandler c : handlers){
			this.master = true;
			if( c != this && c.isMaster() == true){
			    this.master = false;
			    break;
		    }     
		}
		
		if(this.master == true) {
		    if(r.isStart()) {
			startMillis = System.currentTimeMillis();
		    }
		    if(r.isClear()) {
			if(isTimerRunning()) {
			    startMillis = System.currentTimeMillis();
			}
			timeOffset = 0;
		    }
		    if(r.isStop()) {
			timeOffset = getTimerMillis();
			startMillis = 0;
		    }
		    if(r.isEnd()) {
			serverSocket.close(); 
			socket.close();
			handlers.remove(this);
		    }
		}
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    }
}