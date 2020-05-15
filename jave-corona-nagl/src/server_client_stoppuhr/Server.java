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
    private final List<ConnectionHandler> handlers = new LinkedList<>();
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
		
		for(int i = 0; i < 3; i++){//drei aktive verbindungen?
		    if(!clientSocket.isConnected()){
			clientSocket.close();
		    }
		}
		
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
        return timeOffset + (System.currentTimeMillis() - startMillis); //aktuelle zeit
    }
    
    public static void main(String[] args) throws IOException {
        final Server server = new Server(); // neuer server
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
	    return socket.isClosed();
	}

	public boolean isMaster() {
	    return master;
	}

	@Override
	public void run() {
	    //synchronisation fehlt noch
	    int count = 0;
	    
	    while(true){
		try{

		    final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    final String line = reader.readLine(); // zeichen werden in das attribut line gespeichert
		    
		    
		    Gson gson = new Gson(); 
		    gson.toJson(line); // die einkommenden Zeilen werden in ein Object gespeichert
		    final Request r = gson.fromJson(line, Request.class); //neues Request Object, welches die Zeichen beinhaltet

		    final Gson gsonrsp = new Gson();
		    Response rsp = gsonrsp.fromJson(line, Response.class);//response objekt erstellt und unten zurückgesendet
		    count++;
		    rsp.setCount(count);//wie viele response gesendet wurden

		    if(r.isMaster()) {
			synchronized(handlers){
			    for(ConnectionHandler c : handlers){
				this.master = true;
				if( c != this && c.isMaster() == true){
				    this.master = false;
				    break;
				}
			    }
			}
		    }

		    if(this.master == true) {
			rsp.setMaster(true);
			//Respone objekt zurücksenden
			if(r.isStart()) {
			    startMillis = System.currentTimeMillis();
			    rsp.setRunning(true);
			}
			if(r.isClear()) {
			    if(isTimerRunning()) {
				startMillis = System.currentTimeMillis();
			    }
			    timeOffset = 0;
			    rsp.setRunning(true);
			    rsp.setTime(0);
			}
			if(r.isStop()) {
			    timeOffset = getTimerMillis();
			    startMillis = 0;
			    rsp.setTime(timeOffset);
			    rsp.setRunning(false);
			}
			if(r.isEnd()) {
			    serverSocket.close(); 
			    socket.close();
			    handlers.remove(this);
			}
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    } 
	    
	}
    }
}