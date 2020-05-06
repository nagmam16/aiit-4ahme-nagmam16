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
        
        while(handlers.size() <= 3){
            final Socket clientSocket = serverSocket.accept();
            final ConnectionHandler handler = new ConnectionHandler(clientSocket);
            
	    new Thread(handler).start();
	    
            handlers.add(handler);
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

    class ConnectionHandler implements Runnable {
	Server server = new Server(); // neues Sever Object
	private Socket socket;
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
		line = reader.readLine();

		Gson gson = new Gson(); 
		gson.toJson(line);

		Request r = gson.fromJson(line, Request.class);



		if(r.isMaster()) {
		    for(ConnectionHandler c : handlers){
			this.master = true;
			if( c != this && c.isMaster() == true){
			    this.master = false;
			} else {
			    this.master = true;
			}
		    }     
		}

		if(r.isStart()) {
		    startMillis = System.currentTimeMillis();
		}
		if(r.isClear()) {
		    timeOffset = 0;
		}
		if(r.isStop()) {
		    startMillis = 0;
		}
		if(r.isEnd()) {
		    socket.close();
		    handlers.remove(this);
		}

	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}

    }
}