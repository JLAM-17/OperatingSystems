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
    private PriorityQueue<Process> RQ;

    SJF_P(OS os) {
        super(os);
        RQ = new PriorityQueue<>(Comparator.comparingInt(Process::getRemainingTimeInCurrentBurst));
        RQ.addAll(processes);
    }

    @Override
    public void newProcess(boolean cpuEmpty) {// When a NEW process enters the queue, process in CPU, if any, is
                                              // extracted to compete with the rest

    }

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {// When a process return from IO and enters the queue, process in
                                                      // CPU, if any, is extracted to compete with the rest

    }

    @Override
    public void getNext(boolean cpuEmpty) {
        if (!processes.isEmpty()) {
            Process arrivingProcess = processes.getFirst();
            Process currentProcess = os.cpu.extractProcess();

            if (cpuEmpty || arrivingProcess.getRemainingTimeInCurrentBurst() < currentProcess
                    .getRemainingTimeInCurrentBurst()) {
                os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, processes.removeFirst());
            } else {
                os.interrupt(InterruptType.SCHEDULER_CPU_TO_RQ, arrivingProcess);
            }
        }
    }
}
