/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author prestamour
 */
public abstract class Scheduler {
    
    OS os;
    protected LinkedList<Process> processes;
    
    
    public Scheduler(OS os){
        this.os = os;
        processes = new LinkedList();
    }

    public Process getNext(){
        return getNext(false);
    }
    
    public abstract Process getNext(boolean cpuBusy);
    
    public void addProcess(Process p){
        processes.add(p);
    }
    
    public void update(){
        if(processes.size()> 0){
            Process p = this.getNext();
            os.interrupt(InterruptType.SCHEDULER_TO_CPU, p);
        }
    }
    
    public Process removeProcess(Process p){
        Process temp = p;
        processes.remove(p);
        return temp;
    }
    
    public String toString(){
        StringBuffer sb = new StringBuffer();
        
        for (Process process : processes) {
            sb.append(process);
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
