package ProcessScheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Runner {
	
	 
	 
	 
	
	public static void main(String args[]){
		//Prompt for input 
		 int numOfProcesses;
		 ArrayList<Process> processQueue = new ArrayList<Process>();
		
		Scanner in = new Scanner(System.in);
		
		
		//numOfProcesses = Integer.parseInt(JOptionPane.showInputDialog("Enter how many processes you want to schedule"));
		System.out.println("Enter how many processes you want to schedule");
		numOfProcesses = in.nextInt();
		

		//Take the num and prompt for which algo
		int[] processArray = new int[numOfProcesses];
		
		for (int i = 0; i < processArray.length; i++){
			//enter name for process
			//enter burst time for process
			
			System.out.println("Enter something : ");
			String s = in.next();
			in.nextLine();
			
			System.out.print("Enter burst time : ");
			int b = in.nextInt();
			
			
			Process process = new Process(s, b);
			
			processQueue.add(process);
			
			
			//create a new process object
			//push object in ArrayList
			
		}
		
		
		/*
		int prefferedAlgo = Integer.parseInt(JOptionPane.showInputDialog("Which also do you want to use? \n 1. FCFS \n 2. SJF \n 3. Round Robin (Quantum needed)"));
		 
		switch(prefferedAlgo){
		 case 1: 
			 //run the FCFS algo
			 
			 break;
		 case 2 : 
			 //run the sjw algo
			 
			 break;
			 
		 case 3: 
			 //run the round robin algo
			 //need to prompt user for the quantum (do this in the RR class)
			 
			 break;
			 
		default:
			JOptionPane.showMessageDialog(null,"You need to pick a valid algo. Wise up");
		 	
		 }//end switch
		 */
		 //Print the results to the user
		 //Calc the avg waiting time
		
		for (Process process : processQueue){
			
			System.out.println(process.getProcessID());// get the time 
		}
	
		 FCFS(processQueue);
		
	}//end main
	
	 public static void FCFS(ArrayList<Process> processQueue){
		
		Collections.sort(processQueue);
		
		for (Process process : processQueue){
			
			System.out.println(process.getProcessID());// get the time 
		}
		
	}
	
	
}
