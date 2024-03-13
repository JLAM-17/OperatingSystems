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
    
    RoundRobin(){
        super();
        q = 5;
    }
    
    RoundRobin(int q){
        this();
        this.q = q;
    }
    
    
    public Process getNext() {
        return null;
    }
    
}
