/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author prestamour
 */
public class MFQ extends Scheduler{

    int currentScheduler;
    
    private ArrayList<Scheduler> schedulers;
    //This may be a suggestion... you may use the current sschedulers to create the Multilevel Feedback Queue, or you may go with a more tradicional way
    //based on implementing all the queues in this class... it is your choice. Change all you need in this class.
    
    MFQ(OS os){
        super(os);
        currentScheduler = -1;
        schedulers = new ArrayList();
    }
    
    MFQ(OS os, Scheduler... s){ //Received multiple arrays
        this(os);
        schedulers.addAll(Arrays.asList(s));
        for (Scheduler scheduler : schedulers){
            scheduler.setMFQParent(this);
        }

        if(s.length > 0)
            currentScheduler = 0;
    }
        
    @Override
    public void addProcess(Process p){        
       if (p.getState() == ProcessState.NEW) {
            p.setCurrentScheduler(0); // If the process is NEW it enters to the most priority queue
            processes.add(p);
        } else if (p.getState() == ProcessState.IO) {
            IOReturningProcess(os.isCPUEmpty()); // If the process is returning from IO, let the scheduler defines what
                                                // it will do to update the queue to select the next
        }        
        
        p.setState(ProcessState.READY); // If the process comes from the CPU, just add it to the list
        schedulers.get(p.getCurrentScheduler()).processes.add(p);
    }
    
    void defineCurrentScheduler(){
        for (Scheduler scheduler : schedulers){
            if (!scheduler.processes.isEmpty()){
                currentScheduler = schedulers.indexOf(scheduler);
                return;
            }
        }
        currentScheduler = -1;        
    }
    
   
    @Override
    public void getNext(boolean cpuEmpty) {        
        Process actualProcess;
        Process nextProcess;
        Scheduler actualProcessScheduler;
        
        defineCurrentScheduler();      
          
        if (currentScheduler == -1){
            if (cpuEmpty) {
                return;
            } else {
                actualProcess = os.getProcessInCPU();
                actualProcessScheduler = schedulers.get(actualProcess.getCurrentScheduler());

                if (actualProcessScheduler.isMFQQueueDowngraded()){
                    actualProcess.setCurrentScheduler(currentScheduler+1);
                    os.interrupt(InterruptType.SCHEDULER_CPU_TO_RQ, actualProcess);                    
                } else {
                    actualProcessScheduler.getNext(cpuEmpty);
                }
            }

        } else {
            if (cpuEmpty){
                actualProcessScheduler = schedulers.get(currentScheduler);
                actualProcessScheduler.getNext(cpuEmpty);

            } else {                                
                actualProcess = os.getProcessInCPU();
                actualProcessScheduler = schedulers.get(actualProcess.getCurrentScheduler());
                if (currentScheduler != schedulers.size()-1){ // If is not the last queue
                    if (actualProcessScheduler.isMFQQueueDowngraded()){
                        actualProcess.setCurrentScheduler(actualProcess.getCurrentScheduler()+1);
                        
                        nextProcess = schedulers.get(currentScheduler).getNextProcess();
                        os.interrupt(InterruptType.SCHEDULER_CPU_TO_RQ, nextProcess);
                    } else {
                        actualProcessScheduler.getNext(cpuEmpty);
                    }
                } else {
                    actualProcessScheduler.getNext(cpuEmpty);
                }
            }
        }
  
    }
    
    @Override
    public void newProcess(boolean cpuEmpty) {} //Non-preemtive in this event

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {} //Non-preemtive in this event
    
}
