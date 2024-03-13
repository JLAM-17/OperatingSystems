/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os;

/**
 *
 * @author prestamour
 */
public class FCFS extends Scheduler{

    
    FCFS(OS os){
        super(os);
    }
    
   
    @Override
    public void getNext(boolean cpuBusy) {
        if(!processes.isEmpty() && os.isCPUEmpty())
        {        
            Process p = processes.get(0);
            processes.remove();
            os.interrupt(InterruptType.SCHEDULER_TO_CPU, p);
        }
    }
    
}
