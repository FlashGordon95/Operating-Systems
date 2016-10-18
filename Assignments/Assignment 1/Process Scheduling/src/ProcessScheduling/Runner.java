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
		int option;
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
		option = console.nextInt();
		
		while(option != 4)
		{
			switch(option)
			{
				case 1:
					//will call the fcfs method which takes in the processes and also the number of the processes
					//fcfs will attempt to complete each job in its order
					System.out.print("running algo 1");
					//fcfs(processArray, totalProcesses);
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
			option = console.nextInt();
		}
		
	} //end main()
	

}
