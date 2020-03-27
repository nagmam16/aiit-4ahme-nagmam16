package k22.k22_2.k22_2_1;

/**
 *
 * @author Nagl
 * 
 */
class MyThread extends Thread{
    //Erzeugen eines neuen Threads //Thread mit einer endlosschleife
    
    public void run() {//Thread body wird gestartets
        int i = 0;
        while(true){
            System.out.println(i++);
        }
    }
}

public class MyThread2201 {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();// mit diesem aufruf wird ein neuer Thread erzeugt
    }
}