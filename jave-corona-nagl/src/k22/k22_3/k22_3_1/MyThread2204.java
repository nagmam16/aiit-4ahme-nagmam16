package k22.k22_3.k22_3_1;

/**
 *
 * @author user
 */
//Implementieren von Runnable

public class MyThread2204 {
    
    public static void main(String[] args) {
       B2204 b = new B2204();
       Thread t = new Thread(b);
       t.start();
       try {
          Thread.sleep(1000);
       } catch (InterruptedException e){
       }
       t.interrupt();
    }
 }    
 
class A2204 {
   int i;
}
 
class B2204 extends A2204 implements Runnable {
    
    public void run(){
        int i = 0;
        while (true) {
            if (Thread.interrupted()) {
             break;
            }
        System.out.println(i++);
        }
    }
}
 

