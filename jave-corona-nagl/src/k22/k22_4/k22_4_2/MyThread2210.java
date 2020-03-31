package k22.k22_4.k22_4_2;

/**
 *
 * @author user
 */

public class MyThread2210 extends Thread {
   static int zähler = 0;
 
    public static void main(String[] args) {
        Thread t1 = new MyThread2210();
        Thread t2 = new MyThread2210();
        t1.start();
        t2.start();
    }
 
    public void run() {
        while (true) {
            synchronized (getClass()) {
                System.out.println(zähler++);
            }
        }
    }    
}
