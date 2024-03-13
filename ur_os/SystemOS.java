/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ur_os;

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

    public SystemOS() {
        os = new OS(this);
    }
    
    public int getTime(){
        return clock;
    }

    @Override
    public void run() {
        double tp;
        for (int i = 0; i < MAX_SIM_CYCLES; i++) {
            tp = r.nextDouble();
            if(PROB_PROC_CREATION >= tp){
                os.create_process();
            }
            clock++;
        }
        os.showProcesses();
    }
    
    
    
}
