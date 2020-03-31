package k22.k22_4.k22_4_2;

/**
 *
 * @author user
 */
public class MyThread2211 extends Thread {
    private String name;
    private Zähler2211 counter;
 
    public MyThread2211(String name, Zähler2211 counter) {
        this.name = name;
        this.counter = counter;
    }
 
    public static void main(String[] args) {
        Thread[] t = new Thread[5];
        Zähler2211 cnt = new Zähler2211(10);
        for (int i = 0; i < 5; ++i) {
            t[i] = new MyThread2211("Thread-"+i,cnt);
            t[i].start();
        }
    }
 
    public void run() {
        while (true) {
            System.out.println(counter.nextNumber()+" for "+name);
        }
    }
}

class Zähler2211 {
    int Zähler;
 
    public Zähler2211(int zähler) {
        this.Zähler = zähler;
    }
 
    public int nextNumber() {
        int ret = Zähler;
        
        //-------------------------------------------
        //Synchronisieren der Zählermethode
        //-------------------------------------------
        //Hier erfolgen ein Berechnungen, um so zu tun, 
        //als sei das Errechnen des Nachfolgezählers eine langwierige Operation
        //die leicht unterbrochen werden kann
        
        
        double x = 1.0, y, z;
        
        for (int i= 0; i < 1000; ++i) {
            x = Math.sin((x*i%35)*1.13);
            y = Math.log(x+10.0);
            z = Math.sqrt(x+y);
        }
        
        //Jetzt ist der Wert gefunden
        Zähler++;
        return ret;
    }
}
 
