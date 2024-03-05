/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author prestamour
 */
public class SJF_P extends Scheduler {

    SJF_P(OS os) {
        super(os);
    }

    @Override
    // When a NEW process enters the queue, process in CPU, if any, is extracted to
    // compete with the rest
    public void newProcess(boolean cpuEmpty) {

        if (processes.isEmpty()) {
            return;
        }

        if (!cpuEmpty) {
            Process arrivingProcess = processes.getLast();
            Process currentProcess = os.cpu.extractProcess();

            if (arrivingProcess.getRemainingTimeInCurrentBurst() < currentProcess
                    .getRemainingTimeInCurrentBurst()) {
                os.interrupt(InterruptType.SCHEDULER_CPU_TO_RQ, arrivingProcess);
            }
        } else {
            // if cpu is empty and there are processes in the rq
            int cont = 100000; // crazy high number
            Process targetProcess = null;

            for (Process p : processes) {
                int temp = p.getRemainingTimeInCurrentBurst();
                if (temp < cont) {
                    cont = temp;
                    targetProcess = p;
                }
            }
            os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, targetProcess);
        }

    }

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {// When a process returns from IO and enters the queue, process in
                                                      // CPU, if any, is extracted to compete with the rest
        if (!cpuEmpty) {
            int cont = 100000; // crazy high number
            Process targetProcess = null;

            for (Process p : processes) {
                int temp = p.getRemainingTimeInCurrentBurst();
                if (temp < cont) {
                    cont = temp;
                    targetProcess = p;
                }
            }
            os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, targetProcess);

        }
    }

    @Override
    public void getNext(boolean cpuEmpty) {

    }
}
