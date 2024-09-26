import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;




public class DiningPhilosophersSimulation {
    private static final int NUM_TABLES = 6;
    private static final int PHILOSOPHERS_PER_TABLE = 5;
    private final TableManager tableManager = new TableManager(NUM_TABLES);

    public static void main(String[] args){
        new DiningPhilosophersSimulation().startSimulation();
    }

    //startSimulation() should be written here.
    public void startSimulation(){
        List<Thread> threads = new ArrayList<>();

        for(int table=0; table<NUM_TABLES-1; table++){
            for(int i=0; i<PHILOSOPHERS_PER_TABLE; i++){
                Lock leftFork = tableManager.getFork(table, i);
                Lock rightFork = tableManager.getFork(table, (i+1)%PHILOSOPHERS_PER_TABLE);
                Philosopher philosopher = new Philosopher(table*PHILOSOPHERS_PER_TABLE+i, leftFork, rightFork, tableManager);
                threads.add(philosopher);
                philosopher.start();
            }
        }

        monitorDeadlock();
    }

    private void monitorDeadlock(){
        //in this section i have checked deadlock and move to the sixth table
        while (true) {
            if (tableManager.isDeadlock()) {
                System.out.println("Dedlock Detected....Moving philosopher to last(6)th table");
                tableManager.movePhilosopherToSixthTable();
                if (tableManager.isSixthTableDeadlocked()) {
                    System.out.println("Over!!! Sixth table has enterfd Deadlock State");
                    System.out.println("Last Philosopher : "+tableManager.getLastMovedPhilosopher());
                    break;
                }
            }
        }
    }
}
