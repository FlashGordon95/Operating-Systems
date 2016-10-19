package ProcessScheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.BinaryOperator;

import javax.swing.JOptionPane;


public class Runner {
	
	 
	public static void main(String[] args) 
	{
		Scanner console = new Scanner(System.in);
		
		List<Process> processArray = new ArrayList<>();
		int totalProcesses;
		int userOption;
				
		//Gather total amount of processes
		System.out.println("Enter the total number of processes: ");
		totalProcesses = console.nextInt();
		
		
		
		//for loop which prompts for a burst time
		//we then add the process into the arrayList and move to next iteration
		for(int i = 1; i <= totalProcesses; i++)
		{
			//Get the burst times for each process
			System.out.println("\nEnter the burst times for process "+i+": ");
			processArray.add(new Process(i, console.nextInt()));
		}
		//will run code inside at least once and then evaluate the while condition
		do
		{
		
		//Print the choices and prompt the user to enter an option
		System.out.println("\n1: FCFS - First Come First Serve \n2: SJF - Shortest Job First \n3: Round Robin \n4: Exit");
		System.out.println("Please choose a scheduling algorithm: ");
		userOption = console.nextInt();
		
		
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
					fcfs(processArray, totalProcesses);
					//to be added : after sorting the array we can simply reuse FCFS
					break;
				case 3:
					//this will hold the round robin algo
					roundRobin(processArray, totalProcesses);
					break;
				default:
					break;
			}
			//Upon completion of the chosen algo, we notify user
			//Key prompt is asked here to prevent the loop for repeating endlessly
			System.out.println("Scheduling processes has completed");
			System.out.println("Press any key to continue...");
			console.next();

		}while(userOption != 4);
		
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
			System.out.printf("%5d %7d %13d %10d\n", process.getProcessID(), process.getInitialBurstTime(), process.getBurstTime(), waitingTime[count]);
			
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
	public static void roundRobin(List<Process> processArray, int totalProcesses){
		BinaryOperator<Integer> integerAdder = (i, j) -> i + j; // operator used for the streams below
		Scanner console = new Scanner(System.in);
		int quantum;	//time each process can have in the cpu in their cycle of the rr

		System.out.println("Please enter a quantum time: ");
		quantum = console.nextInt();
		
		
		//Java 8 Streams implementation.
		//Here is make use of Streams and in particular the reduction features
		//Adapted from https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
		// - Ryan Gordon
		Integer totalBurstReduce =  processArray
				.stream()
				.map(Process::getBurstTime)
				.reduce(0, integerAdder);
		
		
		System.out.println("Total process time of all processes :"+totalBurstReduce);
		
		System.out.println("Process No | Initial Time | Remaining Time | Start Time | Burst Time | Wait Time");
		
		int startTime = 0;
		int burstTime = 0;
		int[] waitingTime = new int[totalProcesses];
		int i = 0;
		
		//while totalBurstReduce > 0 loop through the array and attempt to complete processes
		while(totalBurstReduce > 0)
		{
			i = 0;
			for(Process process : processArray)
			{
				
				if(process.getBurstTime() < quantum)
				{
					burstTime = process.getBurstTime(); 
					if(process.getBurstTime()==0){
						
					}
					else if (i==(waitingTime.length-1)){
						waitingTime[0] = waitingTime[0] + (process.getBurstTime()*(waitingTime.length-1));

					}
					else{
						waitingTime[i+1] = waitingTime[i] + (process.getBurstTime()*(waitingTime.length-1));

					}
					process.setBurstTime(0); //process is finished 
				}//end if
				else
				{
					burstTime = quantum;
					if(process.getBurstTime()==0){
						
					}
					else if (i==(waitingTime.length-1)){
						waitingTime[0] = waitingTime[0] + (quantum*(waitingTime.length-1));

					}
					else{
						waitingTime[i+1] = waitingTime[i] + (quantum*(waitingTime.length-1));

					}
					process.setBurstTime(process.getBurstTime() - quantum);
				}//end else
				
				System.out.printf("%5d %15d %16d %12d %12d %11d\n", process.getProcessID(), process.getInitialBurstTime(), process.getBurstTime(), startTime, burstTime, waitingTime[i]);
				
				startTime += burstTime; //add to start time for next variable
				totalBurstReduce = totalBurstReduce - burstTime; //Reduce totalBurstReduce
				i++;
			} //end for
			//Placeholder text 
			System.out.println("<==============================================================================>");
		} //end while
		

	}//end roundRobin()


}
