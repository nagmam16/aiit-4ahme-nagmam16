package k22.k22_3.k22_3_2;

import k22.k22_3.k22_3_2.MyThread2207_ThreadedPrimeNumberTools;
import java.io.*;
/**
 *
 * @author user
 */
//Verwendung der Klasse zur Primfaktorzerlegung mit Threads
public class MyThread2208 {
    public static void main(String[] args) {
       
        MyThread2207_ThreadedPrimeNumberTools pt;
        int i;
        
        BufferedReader in = new BufferedReader(
                         new InputStreamReader(
                         new DataInputStream(System.in)));
        
 
        try {
            while (true) {
                System.out.print("Bitte eine Zahl eingeben: ");
                System.out.flush();
                
                i = (new Integer(in.readLine())).intValue();
                
                if (i == -1) {
                    break;
                }
                
                pt = new MyThread2207_ThreadedPrimeNumberTools();
                pt.printPrimeFactors(i);
            }
        } catch (IOException e) {
        }
    }
}
