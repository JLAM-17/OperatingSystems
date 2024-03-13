/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ur_os;


/**
 *
 * @author super
 */
public class OS {
    
    ReadyQueue rq;
    IOQueue ioq;
    private static int process_count = 0;
    SystemOS system;
    CPU cpu;
    
    public OS(SystemOS system, CPU cpu, IOQueue ioq){
        rq = new ReadyQueue(this);
        this.ioq = ioq;
        this.system = system;
        this.cpu = cpu;
    }
    
    public void update(){
        rq.update();
    }
    
    public boolean isCPUEmpty(){
        return cpu.isEmpty();
    }
    
    public void interrupt(InterruptType t, Process p){
        
        switch(t){
        
            case CPU: //It is assumed that the process in CPU is done and it has been removed from the cpu
                if(p.isFinished()){//The process finished completely
                    p.setState(ProcessState.FINISHED);
                    p.setTime_finished(system.getTime());
                }else{
                    ioq.addProcess(p);
                }
            break;
            
            case IO: //It is assumed that the process in IO is done and it has been removed from the queue
                rq.addProcess(p);
            break;
            
            case SCHEDULER_TO_RQ:
                
                //When the scheduler is preemptive and will send the current process in CPU to the Ready Queue
            break;
            
            case SCHEDULER_TO_CPU:
                //When the scheduler defined which process will go to CPU
                cpu.addProcess(p);
                p.setState(ProcessState.CPU);
                
            break;
            
            
        }
        
    }
    
    
    public void create_process(){
        rq.addProcess(new Process(process_count++, system.getTime()));
    }
    
    public void create_process(Process p){
        /*Process p2 = new Process(p);
        p.setPid(process_count);
        p2.setPid(process_count++);
        p2.setState(ProcessState.READY);
        rq.addProcess(p2);*/
        
        p.setPid(process_count++);
        p.setState(ProcessState.READY);
        rq.addProcess(p);
    }
    
    
    public void showProcesses(){
        System.out.println("Process list:");
        System.out.println(rq.toString());
    }
    
    
    
    
}
