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
    
    Scheduler s;
    
    
    public ReadyQueue(){
        s = new RoundRobin();
    }
    
    public ReadyQueue(Scheduler s){
        this.s = s;
    }
    
    public void addProcess(Process p){
        s.addProcess(p);
    }
        
    public String toString(){
        
        return s.toString();
    }
    
}
