package k22.k22_3.k22_3_2;

import k22.k22_3.k22_3_2.MyThread2205_PrimeNumberTools;
import java.io.*;
/**
 *
 * @author Nagl
 */
///Verwendung der KLasse zur Primfaktorzerlegung / ohne Hintergrundverarbeitung und durch aufruf von methoden

public class MyThread2206 {
    
    public static void main(String[] args) {
        
        MyThread2205_PrimeNumberTools pt = new MyThread2205_PrimeNumberTools();//PrimeNumberTools verwenden
        BufferedReader   in = new BufferedReader(
                           new InputStreamReader(
                           new DataInputStream(System.in)));
        int i;
 
        try {
            while (true) {
            System.out.print("Bitte eine Zahl eingeben: ");
            System.out.flush();
            i = (new Integer(in.readLine())).intValue();
            if (i == -1) {
                break;
            }
            pt.printPrimeFactors(i);
        }
        } catch (IOException ex) {
        }
    }
}
