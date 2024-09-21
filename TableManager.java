import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TableManager {
     private final int numTables;
     private final List<Lock[]> forks = new ArrayList<>();
     private final List<Integer> philosophersAtSixthTable = new ArrayList<>();


     public TableManager(int numTables){
        this.numTables = numTables;

        for(int i=0; i<numTables; i++){
            Lock[] tableForks = new Lock[5];
            for(int j=0; j<5; j++){
                tableForks[j] = new ReentrantLock();
            }

            forks.add(tableForks);
        }
     }

     //getfork()

     //isDeadLock()


    //movePhilosopherToTheSixth_Table()

}
