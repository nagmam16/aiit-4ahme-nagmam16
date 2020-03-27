package k22.k22_2.k22_2_2;

/**
 *
 * @author Nagl
 * 
 */
public class MyThread2202 {
    //Abbrechen eines Threads
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
        
        try {
            Thread.sleep(3000);//schlafmodus
        } catch (InterruptedException ex){
            
        }
        t.stop();//deprecated, wegen unsicherheit //damti wird der Thread beendet
        
        //alternative methode mittels Membervarbiable cancelled welche beim initialisieren auf false gesetzt ist
        //kann dann zu beliebigen zeiten in der funktion cancel auf true gesetzt werden und in run
        //muss abgefragt werden ob die variable false oder true ist. wenn sie true ist, sollte sich run beenden.
        //wichtig dabei ist, dass cancelled nicht zu oft abgefragt wird, um zu verhindern, dassdas Programm unnötig lang wird 
        //und das laufzeitverhalten des thread beeinträchtigt wird.
    }
}

class MyThread extends Thread {
    public void run() {//thread body
        int i = 0;
        
        while(true){
            System.out.println(i++);
        }
    }

}