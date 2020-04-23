package Server_Client.Stoppuhr;

import java.io.IOException;
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
        return startMillis;
    }
    
    public void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(8080);
        timeOffset = 0;
        startMillis = System.currentTimeMillis();
        
        
        if(startMillis > 0) {
           timeOffset = getTimerMillis();
           startMillis = 0;
        }
        
        
        long time;
        if(startMillis > 0){
            time = timeOffset + (System.currentTimeMillis() - startMillis);
        } else {
            time = timeOffset;
        }
    }
    
    
}
