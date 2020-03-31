package k22.k22_4.k22_4_1;

/**
 *
 * @author user
 */
/* Listing2209.java */
//Zwei Zählerthreads

public class MyThread2209 extends Thread {
    static int zähler = 0;
 
    public static void main(String[] args) {
        Thread t1 = new MyThread2209();
        Thread t2 = new MyThread2209();
        t1.start();
        t2.start();
    }
 
    public void run() {
        while (true) {
            System.out.println(zähler++);
        }
    }
}
