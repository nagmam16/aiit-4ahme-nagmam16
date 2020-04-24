package server_client_stoppuhr;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
        
        while(true){
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
}


class ConnectionHandler implements Runnable {
    Server server = new Server();
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
        BufferedReader reader = new BufferedReader(new InputStream());
        
        String line;
        try{
            line = reader.readLine();
        } catch(Exception ex) {
            throw new IllegalArgumentException();
        }

        Gson gson = new Gson(); 
        gson.toJson(line);
        
        for(int i = 0; i < handlers.size; i++){
            if() {
                master = true;
                
            }
        }
    }

}