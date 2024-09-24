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
}
