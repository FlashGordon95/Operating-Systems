package lab8;
//Lab 8 - Operating Systems
//G00326349 - Ryan Gordon
public class RequesterResponderTest extends Thread{
    public static void main(String[] args) throws InterruptedException {
        ThreadHole c = new ThreadHole();
        Responder p1 = new Responder(c, 1);
        Requester c1 = new Requester(c, 1);
        System.out.println("Starting threads, putting main to sleep for 10s after");
        p1.start();
        c1.start();
        
        sleep(10000); //thread will sleep for 10s
        System.out.println("Threads are finished. Sleep for 10 more seconds then shutdown");
        sleep(10000);
        System.out.println("Shutting down");
    }
}
//One thread puts in how many times the question has been asked. Responder gets this string and displays on each answer
class ThreadHole {
    private String contents;
    private boolean available = false;

    public synchronized String get() {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        
        available = false;
        notifyAll();
        return contents;
    }

    public synchronized void put(String timesAsked) {
        while (available == true) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        contents = timesAsked;
        available = true;
        notifyAll();
    }
}


class Requester extends Thread {
    private ThreadHole bunghole;

    public Requester(ThreadHole c, int number) {
        bunghole = c;
    }

    public void run() {
        String value;
        for (int i = 0; i < 10; i++) {
            value = bunghole.get(); //i taken from bunghole
            System.out.println("Galway!! - "+value); //value is the value passed from requester
        }
    }
}



class Responder extends Thread {
    private ThreadHole bunghole;

    public Responder(ThreadHole c, int number) {
        bunghole = c;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            bunghole.put("Times Asked: "+(i+1)); //i put into bunghole for requester to get
            System.out.println("What city are we in?");
            try {
                sleep(1000);
            } catch (InterruptedException e) { }
        }
    }
}
           