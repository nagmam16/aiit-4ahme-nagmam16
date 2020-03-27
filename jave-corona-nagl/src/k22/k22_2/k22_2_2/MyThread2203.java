package k22.k22_2.k22_2_2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nagl
 * 
 */

// damit cancelled nicht zu oft abgefragt wird aber auch nicht zu selten, gibt einige methoden  in der klass thread
// public void interrupt(); public boolean isInterrupted(); public static boolean interrupted;
    
//durch interrupt wir ein flag gesetzt, welches eine unterbrechungsanforderung signalisiert
//isInterrupted ob abbruchflag gesetzt und thread beendet werden soll
//interrupted = akutelle status von abbruchflag false oder true


public class MyThread2203 extends Thread{
    int cnt = 0;
    
    public void run() {
        while(true){
            if(isInterrupted()){
                break;
            }
            printLine(++cnt);
            
        }
    }

    private void printLine(int cnt) {
        //zeile ausgeben
        System.out.println(cnt + ": ");
        for(int i = 0; i< 30 ; ++i){
            System.out.println(i == cnt % 30 ? "* " : ". ");  
        }
        System.out.println();
        
        //schlafzustand für 100ms also 100ms wartet das programm
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            interrupt();
        }
    }
    
    public static void main(String[] args) {
        MyThread2203 th = new MyThread2203();
        th.start(); //thread wird gestartet
        
        try {
            Thread.sleep(2000); //schlafzustand für 2000ms also 2000ms wartet das programm
        } catch (InterruptedException ex) {
        }
        th.interrupt();//thread wird abgebrochen
    }
}


