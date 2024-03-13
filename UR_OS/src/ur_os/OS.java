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
    private static int process_count = 0;
    SystemOS system;
    
    public OS(SystemOS system){
        rq = new ReadyQueue();
        this.system = system;
        
        
    }
    
    public void create_process(){
        rq.addProcess(new Process(process_count++, system.getTime()));
    }
    
    public void create_process(Process p){
        Process p2 = new Process(p);
        p2.setPid(process_count++);
        rq.addProcess(p2);
    }
    
    
    public void showProcesses(){
        System.out.println("Process list:");
        System.out.println(rq.toString());
    }
    
}
