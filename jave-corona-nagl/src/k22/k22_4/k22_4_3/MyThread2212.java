package k22.k22_4.k22_4_3;

import java.util.*;

/**
 *
 * @author user
 */
//Producer-/Consumer-Beispiel mit wait und notify

public class MyThread2212 {
    public static void main(String[] args) {
        Vector newV = new Vector();
 
        Producer2212 p = new Producer2212(newV);
        Consumer2212 c = new Consumer2212(newV);
        p.start();
        c.start();
    }
}


class Producer2212 extends Thread {
    private Vector v1;
 
    public Producer2212(Vector v) {
        this.v1 = v;
    }
 
    public void run() {
        String s;
 
        while (true) {
            synchronized (v1) {
                s = "Wert "+Math.random();
                v1.addElement(s);
                System.out.println("Produzent erzeugte "+s);
                v1.notify();
            }
            
            try {
                Thread.sleep((int)(100*Math.random()));
            } catch (InterruptedException ex) {
            }
        }
    }
}
 
class Consumer2212 extends Thread {
    private Vector v2;
 
    public Consumer2212(Vector v) {
       this.v2 = v;
    }
 
    public void run() {
        
        while (true) {
            synchronized (v2) {
                if (v2.size() < 1) {
                    
                    try {
                        v2.wait();
                    } catch (InterruptedException ex) {
                    }
                }
                System.out.print(" Konsument fand "+(String)v2.elementAt(0));
                v2.removeElementAt(0);
                System.out.println(" (verbleiben: "+v2.size()+")");
            }
            
            try {
                Thread.sleep((int)(100*Math.random()));
            } catch (InterruptedException e) {
            }
        }
    }
}

