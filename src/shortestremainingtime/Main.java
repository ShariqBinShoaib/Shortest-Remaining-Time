package shortestremainingtime;

import java.util.ArrayList;
import java.util.Random;

public class Main
{
    static ArrayList<Process> processList = new ArrayList<>();

    public static void main(String[] args)
    {
        int timeConsumed = 1;
        boolean isProcessArrived = false;
        createProcesses();
        printProcessValues();

        Process currentProcessObject;
        Process nextProcessObject;
        while (!(processList.isEmpty()))
        {
            for (int i = 0; i < processList.size(); i++)
            {
                currentProcessObject = processList.get(i);
                jump:
                {
                    boolean print = true;
                    while (!(currentProcessObject.process.isEmpty()))
                    {
                        if (print)
                        {
                            currentProcessObject.printProcessInfo();
                            System.out.println(currentProcessObject.process.toString());
                            System.out.println("\nExecution of Current Process:");
                            System.out.println();
                            print = false;
                        }
                        System.out.println("Process Value: " + currentProcessObject.process.get(0) + "\t\tTime Consumed: " + timeConsumed);
                        currentProcessObject.executionTime--;
                        currentProcessObject.process.remove(0);
                        if (currentProcessObject.process.isEmpty())
                        {
                            currentProcessObject.completionTime = timeConsumed;
                            currentProcessObject.calculateTime();
                            System.out.println("\nPROCESS 0" + currentProcessObject.processID + " COMPLETED!");
                            System.out.printf("%nCompletion Time: %d  |   Turn Arround Time: %d   |   Waiting Time: %d    |   Utilization: %.2f%n%n",
                                    currentProcessObject.completionTime, currentProcessObject.turnArroundTime, currentProcessObject.waitingTime, currentProcessObject.utilization);
                            processList.remove(i);
                            i--;
                            timeConsumed++;
                            break;
                        }
                        for (int j = 0; j < processList.size(); j++)
                        {
                            if (j != i)
                            {
                                nextProcessObject = processList.get(j);
                                if (timeConsumed == nextProcessObject.arrivalTime)
                                {
                                    nextProcessObject.isArrived = true;
                                    isProcessArrived = true;
                                }
                                if (nextProcessObject.isArrived)
                                {
                                    if (currentProcessObject.executionTime > nextProcessObject.executionTime)
                                    {
                                        if (isProcessArrived)
                                        {
                                            System.out.println("\n|-------------------------------------NEW PROCESS ARRIVED----------------------------------|\n\nComparing Execution Time of current process & arrived process...");
                                            System.out.println("Remaining Execution Time of Current Process(0" + currentProcessObject.processID + "): " + currentProcessObject.executionTime);
                                            System.out.println("Execution Time of newly Arrived Process(0" + nextProcessObject.processID + "): " + nextProcessObject.executionTime);
                                            System.out.println("Next Process Execution Time is LESS than the Remaining Execution Time of current Process!");
                                            System.out.println("Current Process terminates and next process will execute\n");
                                            System.out.println("____________________________________________________________________________________________\n");
                                            isProcessArrived = false;
                                        }
                                        timeConsumed++;
                                        break jump;
                                    } else
                                    {
                                        if (isProcessArrived)
                                        {
                                            System.out.println("\n|-------------------------------------NEW PROCESS ARRIVED----------------------------------|\n\nComparing Execution Time of current process & arrived process...");
                                            System.out.println("Remaining Execution Time of Current Process(0" + currentProcessObject.processID + "): " + currentProcessObject.executionTime);
                                            System.out.println("Execution Time of newly Arrived Process(0" + nextProcessObject.processID + "): " + nextProcessObject.executionTime);
                                            System.out.println("Next Process Execution Time is GREATER than or EQUAL TO the Remaining Execution Time of current Process!");
                                            System.out.println("Current Process will continue running\n");
                                            System.out.println("____________________________________________________________________________________________\n");
                                            isProcessArrived = false;
                                        }
                                    }
                                }
                            }
                        }
                        timeConsumed++;
                    }
                }
            }
        }
    }

    public static void createProcesses()
    {
        int arrivalTime = 0;
        for (int i = 0; i < 5; i++)
        {
            if (i != 0)
            {
                arrivalTime += new Random().nextInt(2) + 2;
            }
            processList.add(new Process(arrivalTime));
            processList.get(i).processID = i;
        }
    }

    public static void printProcessValues()
    {
        System.out.println("|-----------PROCESSES DETAILS-----------|\n");
        for (int i = 0; i < 5; i++)
        {
            processList.get(i).printProcessInfo();
            System.out.println(processList.get(i).process.toString());
            System.out.println();
        }
        System.out.println("___________________________________________\n");
        System.out.println("\n ****************** EXECUTION OF PROCESSES ********************\n");
    }
}