/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os;

import java.util.ArrayList;

/**
 *
 * @author prestamour
 */
public abstract class Scheduler {
    
    protected ArrayList<Process> processes;
    
    public Scheduler(){
        processes = new ArrayList();
    }

    public abstract Process getNext();
    
    public void addProcess(Process p){
        processes.add(p);
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
