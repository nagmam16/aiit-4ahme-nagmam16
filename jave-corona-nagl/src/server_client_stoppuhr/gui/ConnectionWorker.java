package server_client_stoppuhr.gui;


import com.google.gson.Gson;
import java.io.BufferedReader;
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
	protected String doInBackground() throws Exception {//hintergrund arbeiten // damit ich leichter auf die gui zugreifen kann
	    final Gson g = new Gson();//gson objekt
	    final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));//herauslesenn
	    final OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());//hineinschrieben
	
	    while(true) {
		try {
		    final Request req = new Request();
		    final String reqString = g.toJson(req);
		    writer.write(reqString);
		    writer.flush();

		    final String respString = reader.readLine();
		    final Response resp = g.fromJson(respString, Response.class);
		    publish(resp);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    }
	}

}