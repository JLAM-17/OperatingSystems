/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ur_os;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author super
 */
public class SystemOS implements Runnable{
    
    private static int clock = 0;
    private static final int MAX_SIM_CYCLES = 100;
    private static final double PROB_PROC_CREATION = 0.2;
    private static Random r = new Random();
    private OS os;
    
    protected ArrayList<Process> processes;

    public SystemOS() {
        os = new OS(this);
        processes = new ArrayList();
        initSimulationQueue();
    }
    
    public int getTime(){
        return clock;
    }
    
    public ArrayList<Process> getProcessAtI(int i){
        ArrayList<Process> ps = new ArrayList();
        
        for (Process process : processes) {
            if(process.getTime_init() == i){
                ps.add(process);
            }
        }
        
        return ps;
    }

    public void initSimulationQueue(){
        double tp;
        Process p;
        for (int i = 0; i < MAX_SIM_CYCLES; i++) {
            tp = r.nextDouble();
            if(PROB_PROC_CREATION >= tp){
                p = new Process();
                p.setTime_init(clock);
                processes.add(p);
            }
            clock++;
        }
        clock = 0;
    }
    
    @Override
    public void run() {
        double tp;
        ArrayList<Process> ps;
        for (int i = 0; i < MAX_SIM_CYCLES; i++) {
            ps = getProcessAtI(i);
            for (Process p : ps) {
                os.create_process(p);
            }
            clock++;
        }
        os.showProcesses();
    }
    
    public void showProcesses(){
        System.out.println("Process list:");
        StringBuffer sb = new StringBuffer();
        
        for (Process process : processes) {
            sb.append(process);
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }
    
    
}
