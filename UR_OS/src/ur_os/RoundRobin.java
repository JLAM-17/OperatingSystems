/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os;

/**
 *
 * @author prestamour
 */
public class RoundRobin extends Scheduler{

    int q;
    
    RoundRobin(OS os){
        super(os);
        q = 5;
    }
    
    RoundRobin(OS os, int q){
        this(os);
        this.q = q;
    }
    
   
    @Override
    public Process getNext(boolean cpuBusy) {
        return null;
    }
    
}
