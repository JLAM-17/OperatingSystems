/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ur_os;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author super
 */
public class SystemOS implements Runnable{
    
    private static int clock = 0;
    private static final int MAX_SIM_CYCLES = 100;
    private static final double PROB_PROC_CREATION = 0.1;
    private static Random r = new Random(1234);
    private OS os;
    private CPU cpu;
    private IOQueue ioq;
    
    protected ArrayList<Process> processes;

    public SystemOS() {
        cpu = new CPU();
        ioq = new IOQueue();
        os = new OS(this, cpu, ioq);
        cpu.setOS(os);
        ioq.setOS(os);
        
        processes = new ArrayList();
        //initSimulationQueue();
        initSimulationQueueSimple();
        showProcesses();
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
    
    public void initSimulationQueueSimple(){
        Process p;
        int cont = 0;
        for (int i = 0; i < MAX_SIM_CYCLES/2; i++) {
            if(i % 10 == 0){
                p = new Process(cont++,-1);
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
        
        System.out.println("******SIMULATION START******");
        
        for (int i = 0; i < MAX_SIM_CYCLES; i++) {
            System.out.println("******Clock: "+i+"******");

            //Crear procesos, si aplica en el ciclo actual
            ps = getProcessAtI(i);
            for (Process p : ps) {
                os.create_process(p);
            }
                        
            //Actualizar la CPU
            cpu.update();
            
            ///Actualizar la IO
            ioq.update();
            
            //Las actualizaciones de CPU y IO pueden generar interrupciones que actualizan a cola de listos, cuando salen los procesos
            
            //Actualizar el OS, quien va actualizar el Scheduler            
            os.update();
            
            
            System.out.println(cpu);
            /*
            System.out.println(cpu);
            if(!cpu.isEmpty())
                if(cpu.advanceBurst())
                    System.out.println("CPU Burst finished");

            */
            clock++;
        }
        System.out.println("******SIMULATION FINISHES******");
        os.showProcesses();
    }
    
    public void showProcesses(){
        System.out.println("Process list:");
        StringBuilder sb = new StringBuilder();
        
        for (Process process : processes) {
            sb.append(process);
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }
    
    
}
