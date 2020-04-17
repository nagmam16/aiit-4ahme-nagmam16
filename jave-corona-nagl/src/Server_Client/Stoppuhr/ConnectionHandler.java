package Server_Client.Stoppuhr;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author Nagl
 */
public class ConnectionHandler implements Runnable {
    private Socket socket;
    private boolean master;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    public boolean isClosed() {
        return socket.isClosed();
    }
    
    public boolean isMaster() {
        return master;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStream);
        
        String line;
        try{
            line = reader.readLine();
        } catch(Exception ex) {
            throw new IllegalArgumentException();
        }
        
        
        Gson gson = new Gson(); 
        gson.toJson(line);
        
    }

}
