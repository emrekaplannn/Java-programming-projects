import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lab2 implements Runnable {
    private static int scribenumm2=1;
    
    private static int pens;
    private static int bottles;    
    private static int books;
    
    private static Lock lockpen;
    private static Lock lockbottle;
    private static Lock lockbook;

    private int scribenumm;
    public Lab2() {
        scribenumm=scribenumm2++; // increase scribenumm number
    }

    @Override
    public void run() {
    while (true) {
        try {
            lockpen.lock(); // getting pen
            if(pens>0){ //there is available pen
                pens--;
                System.out.println("Scribe " + scribenumm + " takes a pen");
                lockpen.unlock();    
            }
            else{ // no pen available
                lockpen.unlock(); 
                
                Thread.sleep(1000);
                continue;
            }

            lockbottle.lock(); // getting bottle
            if(bottles>0){ // there is available bottle
                bottles--;
                System.out.println("Scribe " + scribenumm + " takes a bottle");
                lockbottle.unlock();
            }
            else{ // no bottle available
                lockbottle.unlock();
                
                lockpen.lock();
                pens++;
                System.out.println("Scribe " + scribenumm + " puts the pen back");
                lockpen.unlock();
                
                Thread.sleep(1000);
                continue;
            }
      
            lockbook.lock(); // getting book
            if(books >0){ // there is available book
                books--;
                System.out.println("Scribe " + scribenumm + " takes a book");
                lockbook.unlock();    
            }
            else{ // no book available
                lockbook.unlock();
                
                lockpen.lock();
                pens++;
                System.out.println("Scribe " + scribenumm + " puts the pen back");
                lockpen.unlock();
                
                lockbottle.lock();
                bottles++;
                System.out.println("Scribe " + scribenumm + " puts the bottle back");
                lockbottle.unlock(); 
                
                Thread.sleep(1000);
                continue; 
            }

            System.out.println("Scribe " + scribenumm + " writes a record"); // write a record

            //release the items
            lockpen.lock();
            lockbottle.lock();
            lockbook.lock();
            
            pens++;
            bottles++;
            books++;
            
            System.out.println("Scribe " + scribenumm + " puts the pen back");
            System.out.println("Scribe " + scribenumm + " puts the bottle back");
            System.out.println("Scribe " + scribenumm + " puts the book back");

            lockpen.unlock();
            lockbottle.unlock();
            lockbook.unlock();

            Thread.sleep((int)(Math.random() * 10000)); //sleep
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }

        //main
    public static void main(String[] args) {
        // command line args
    int numofscribe=Integer.parseInt(args[0]);
    pens=Integer.parseInt(args[1]);
    bottles=Integer.parseInt(args[2]);
    books=Integer.parseInt(args[3]);

    lockpen=new ReentrantLock();
    lockbottle=new ReentrantLock();
    lockbook=new ReentrantLock();
        
        //start threads
    for(int mr=0; mr<numofscribe; mr++){
        Thread thread=new Thread(new Lab2());
        thread.start();
    }
    }
}