package server_client_stoppuhr.gui;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import javax.swing.SwingWorker;
import server_client_stoppuhr.Request;
import server_client_stoppuhr.Response;

/**
 *
 * @author user
 */

//Durch die Klasse Swingworker können laufende Prozesse, 
//die die Oberfläche beeinflussen im Hintergrund laufen, dass die Oberfläche weiterhin normal benutzt werden kann

public class ConnectionWorker extends SwingWorker< String, Response> { // Rückgabewert von doinBackground , Wert welcher zwsichen druch übermittelt wird
    Socket socket; 
    private server_client_stoppuhr.gui.Client gui;
    
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

    @Override
    protected String doInBackground() throws Exception {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}