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
public class Process {
    int pid;
    int time_init;

    public Process() {
        pid = -1;
        time_init = 0;
    }
    
    public Process(int pid, int time_init) {
        this.pid = pid;
        this.time_init = time_init;
    }
    
    public Process(Process p) {
        this.pid = p.pid;
        this.time_init = p.time_init;
        //All info about bursts
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTime_init() {
        return time_init;
    }

    public void setTime_init(int time_init) {
        this.time_init = time_init;
    }
        
    public String toString(){
        return "Process "+pid+" t:"+time_init;
    }
    
}
