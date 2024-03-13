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
public class CPU {
    
    Process p;
    OS os;
    
    public CPU(){
    }
    
    public CPU(OS os){
        this.os = os;
    }
    
    public void setOS(OS os){
        this.os = os;
    }
    
    public void addProcess(Process p){
        this.p = p;
        p.setState(ProcessState.CPU);
    }
    
    public boolean isEmpty(){
        return p == null;
    }
    
    public void update(){
        if(!isEmpty())
            advanceBurst();
    }
    
    public boolean advanceBurst(){
        boolean temp = p.advanceBurst();
        if(temp){
            Process tempp = p;
            removeProcess();
            os.interrupt(InterruptType.CPU, tempp);
        }    
            
        return temp;
    }
    
    public void removeProcess(){
        
        p = null;

    }
    
    public String toString(){
        if(!isEmpty())
            return "CPU: "+p.toString();
        else
            return "CPU: Empty";
    }
    
}
