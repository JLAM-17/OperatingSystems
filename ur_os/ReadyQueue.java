/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ur_os;

import java.util.ArrayList;

/**
 *
 * @author super
 */
public class ReadyQueue {
    
    ArrayList<Process> processes;
    
    
    public ReadyQueue(){
        processes =  new ArrayList();
    }
    
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
