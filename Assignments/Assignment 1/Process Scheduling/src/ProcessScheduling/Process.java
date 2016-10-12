package ProcessScheduling;
/**
 * 
 */

/**
 * @author college
 *
 */
public class Process implements Comparable<Process> {
	//needed for process creations
	private String processName;
	private int pid;
	
	//More so used for the algos
	private int startTime;	//The start time is when the process enters the CPU
	private int waitTime;	//Wait time is the amount of time the process needs to wait
	private int processTime; //Process time is the time remaining until the process is completed
	
	
	//Standard constructor for Process
	public Process(String processName, int pid) {
        this.pid = pid;
        this.processName = processName;
        
       
    }
	public int getProcessID(){
		
		return this.pid;
	}
	@Override
    public int compareTo(Process compareProcess) {
        int compareID=((Process)compareProcess).getProcessID();
        /* For Ascending order*/
        return this.pid-compareID;

        /* For Descending order do like this */
        //return compareage-this.studentage;
    }

    @Override
    public String toString() {
        return "[ Process name=" + this.processName + ", PID=" + this.pid + "]";
    }

}
