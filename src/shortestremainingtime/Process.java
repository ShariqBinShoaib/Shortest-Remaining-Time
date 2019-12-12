package shortestremainingtime;

import java.util.ArrayList;
import java.util.Random;

public class Process
{
    ArrayList<Integer> process = new ArrayList<>();
    public int processID;
    public int arrivalTime;
    public int executionTime;
    public final int executionTime1;
    public int completionTime;
    public int turnArroundTime;
    public int waitingTime;
    public double utilization;
    public boolean isArrived;

    public Process(int processArrivalTime)
    {
        this.executionTime = new Random().nextInt(5) + 4;
        this.executionTime1 = executionTime;
        this.arrivalTime = processArrivalTime;
        for (int i = 0; i < executionTime; i++)
        {
            process.add(new Random().nextInt(8) + 1);
        }
    }

    public void printProcessInfo()
    {
        System.out.println("Process ID: 0" + processID);
        System.out.println("Arrival Time: " + arrivalTime);
        System.out.println("Execution Time: " + executionTime);
        System.out.print("Process Values: ");
    }

    public void calculateTime()
    {
        this.turnArroundTime = completionTime - arrivalTime;
        this.waitingTime = turnArroundTime - executionTime1;
        this.utilization = (double) executionTime1 / (double) turnArroundTime;
    }
}