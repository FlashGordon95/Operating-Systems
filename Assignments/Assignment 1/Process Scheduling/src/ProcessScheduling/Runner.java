package ProcessScheduling;

import javax.swing.JOptionPane;

public class Runner {
	//Prompt for input 
	 static int numOfProcesses;
	 
	
	
	public static void main(String args[]){
		numOfProcesses = Integer.parseInt(JOptionPane.showInputDialog("Enter how many processes you want to schedule"));
		//Take the num and prompt for which algo
		
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
		 	
		 
		 
		 }
		 
		 //Print the results to the user
		 //Calc the avg waiting time
		
	}
	
	
}
