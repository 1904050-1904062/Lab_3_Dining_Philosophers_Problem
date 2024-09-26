import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class Philosopher extends Thread{
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;
    private final TableManager tableManager;
    private final Random random = new Random();


    public Philosopher(int id, Lock leftFork, Lock rightFork, TableManager tableManager){
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.tableManager = tableManager;
    }

    public void run(){
        try {
            while (true) {
                think();
                if (pickUpLeftFork()) {
                   if (pickUpRightFork()) {
                        eat();
                        putDownForks();
                   } else{
                    leftFork.unlock();
                   }
                }
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
            System.out.println("Philo "+id+" was Interrupted");
        }
    }


    private void think() throws InterruptedException{
        System.out.println("Philo" + id + "in noq thinking.");
        Thread.sleep(random.nextInt(10)*1000);
    }


    private boolean pickUpLeftFork(){
        if(leftFork.tryLock()){
            System.out.println("Philo "+ id + "Picked up the left fork.");
            return true;
        }
        return false;
    }


    private boolean pickUpRightFork() throws InterruptedException{
        Thread.sleep(4000);
        if(rightFork.tryLock()){
            System.out.println("Philo "+id+ "Picked up right fork");
            return true;
        }
        return false;
    }

    private void eat() throws InterruptedException{
        System.out.println("Philo. "+id+ "is eating.");
        Thread.sleep(random.nextInt(5)*1000);
    }

    
    private void putDownForks(){
        rightFork.unlock();
        System.out.println("Philo. "+id+" put down the right fork.");
        leftFork.unlock();
        System.out.println("Philo. "+id+" put down the left fork.");
    }
    
}