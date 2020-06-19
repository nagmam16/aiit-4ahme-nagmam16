package server_client_stoppuhr.gui;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import javax.swing.SwingWorker;
import server_client_stoppuhr.Response;

/**
 *
 * @author user
 */
public class ConnectionWorker extends SwingWorker< Object, Response> { // Rückgabewert von doinBackground , Wert welcher zwsichen druch übermittelt wird
    private Socket socket; 
    private server_client_stoppuhr.gui.Client gui;

    public ConnectionWorker(server_client_stoppuhr.gui.Client gui, int port) throws IOException {
	socket = new Socket("127.0.0.1", port);
	this.gui = gui;
    }
    
    @Override
    protected Object doInBackground() throws Exception {
	while(true) {
	    Response resp = null;
	    publish(resp);
	}
    }
    
    @Override
    protected void process(List<Response> list) {
	for (Response r : list){
	    gui.handleResponse(r);
	}
    }

    @Override
    protected void done() {
	super.done();
	/*try {
	    //super.done();
	    String ergebnis = (String) get();
	    System.out.println(ergebnis);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}*/
    }
}