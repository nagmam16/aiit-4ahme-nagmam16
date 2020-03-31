package k22.k22_4.k22_4_4;

import java.io.*;

/**
 *
 * @author user
 */

public class MyThread2213 {
    public static void main(String[] args) throws Exception {
        PipedInputStream inPipe = new PipedInputStream();
        PipedOutputStream outPipe = new PipedOutputStream(inPipe);
        
        Producer2213 p = new Producer2213(outPipe);
        Consumer2213 c = new Consumer2213(inPipe);
        p.start();
        c.start();
    }
}

class Producer2213 extends Thread {
    private PipedOutputStream pipe;
 
    public Producer2213(PipedOutputStream pipe) {
        this.pipe = pipe;
    }
 
    public void run() {
        
        while (true) {
            byte b = (byte)(Math.random() * 128);
            try {
                pipe.write(b);
                System.out.println("Produzent erzeugte " + b);
            } catch (IOException e) {
                System.err.println(e.toString());
            }
            
            try {
                Thread.sleep((int)(100*Math.random()));
            } catch (InterruptedException e) {
            }
        }
    }
}
 
class Consumer2213 extends Thread {
    private PipedInputStream pipe;
 
    public Consumer2213(PipedInputStream pipe) {
        this.pipe = pipe;
    }
 
    public void run() {
        while (true) {
            try {
                byte b = (byte)pipe.read();
                System.out.println(" Konsument fand " + b);
            } catch (IOException e) {
                System.err.println(e.toString());
            }
            
            try {
                Thread.sleep((int)(100*Math.random()));
            } catch (InterruptedException e) {
            }
        }
    }
}
 