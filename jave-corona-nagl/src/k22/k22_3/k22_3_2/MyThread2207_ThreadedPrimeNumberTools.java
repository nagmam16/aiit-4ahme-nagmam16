package k22.k22_3.k22_3_2;

import k22.k22_3.k22_3_2.MyThread2205_PrimeNumberTools;

/**
 *
 * @author user
 */
//Primfaktorzerlegung mit Threads

public class MyThread2207_ThreadedPrimeNumberTools extends MyThread2205_PrimeNumberTools implements Runnable {
    private int arg;
    private int func;

    public void printPrimeFactors(int num) {
        execAsynchron(1,num);
    }

    public void printPrime(int cnt) {
        execAsynchron(2,cnt);
    }
 
    public void run() {
        if (func == 1) {
            super.printPrimeFactors(arg);
        } else if (func == 2) {
            int result = super.getPrime(arg);
            System.out.println("prime number #"+arg+" is: "+result);
        }
    }
 
    private void execAsynchron(int func, int arg) {
        Thread t = new Thread(this);
        this.func = func;
        this.arg  = arg;
        t.start();
    }
}
