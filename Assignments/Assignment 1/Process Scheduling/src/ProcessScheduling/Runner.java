package ProcessScheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Runner {
	
	 
	public static void main(String[] args) 
	{
		Scanner console = new Scanner(System.in);
		
		List<Process> processArray = new ArrayList<>();
		int totalProcesses;
		int userOption;
		int quantum;
				
		//Gather total amount of processes
		System.out.println("Enter the total number of processes: ");
		totalProcesses = console.nextInt();
		
		//Get the burst times for each process
		System.out.println("\nEnter the burst times for each process: ");
		//for loop which prompts for a burst time
		//we then add the process into the arrayList and move to next iteration
		for(int i = 1; i <= totalProcesses; i++)
		{
			processArray.add(new Process(i, console.nextInt()));
		}
		
		//Print the choices and prompt the user to enter an option
		System.out.println("\n1: FCFS - First Come First Serve \n2: SJF - Shortest Job First \n3: Round Robin \n4: Exit");
		System.out.println("Please choose a scheduling algorithm: ");
		userOption = console.nextInt();
		
		while(userOption != 4)
		{
			switch(userOption)
			{
				case 1:
					//will call the fcfs method which takes in the processes and also the number of the processes
					//fcfs will attempt to complete each job in its order
					
					fcfs(processArray, totalProcesses);
					break;
				case 2:
					//Collections.sort allows us to reorder the process array by a parameter
					//In this case it is ordered by the burst time
					//This uses the compareTo() method in the PiD class which has been overridden
					Collections.sort(processArray);
					
					//to be added : after sorting the array we can simply reuse FCFS
					break;
				case 3:
					//this will hold the round robin algo
				default:
					break;
			}
			
			System.out.println("Please choose a scheduling algorithm: ");
			userOption = console.nextInt();
		}
		
	} //end main()
	
	public static void fcfs (List<Process> processArray, int totalProcesses)
	{
		int totalWaitTime = 0;
		float averageWaitTime;
		
		System.out.println("Process No | Burst Time | Wait Time");
		
		//Instantiate an array size of total number of processes
		int[] waitingTime = new int[totalProcesses];
		//Initialize a counter variable to 0
		int count = 0;
		
		//Loop through the ArrayList and add up the wait time
		for(Process process : processArray)
		{
			//print the process information
			System.out.printf("%10d %10d %10d\n", process.getProcessID(), process.getBurstTime(), waitingTime[count]);
			
			//increment the counter to calculate the wait time for the process
			//the wait time on the first process will always be 0
			count++;
			//if the counter is less than the number of processes then add to the wait time
			if(count < totalProcesses)
			{
				//The wait time = the previous wait time + the current burst time
				waitingTime[count] = waitingTime[count - 1] + process.getBurstTime();
			}
			
		} //end for
		
		//Add up the total waiting time for each process
		for(int i = 0; i < totalProcesses; i++)
		{
			totalWaitTime += waitingTime[i];
		}
		System.out.println("\nThe total wait time = " + totalWaitTime);
		
		averageWaitTime = (float)totalWaitTime / totalProcesses;
		System.out.println("\nThe average wait time = " + averageWaitTime);
	} //end fcfs()
	

}
