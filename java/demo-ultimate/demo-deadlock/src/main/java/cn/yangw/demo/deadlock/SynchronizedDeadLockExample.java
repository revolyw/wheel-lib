package cn.yangw.demo.deadlock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SynchronizedDeadLockExample {
    private final TaskExecutor taskExecutor;

    @Autowired
    public SynchronizedDeadLockExample(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @AllArgsConstructor
    public class RunableImpl implements Runnable {
        private Object o1;
        private Object o2;

        public RunableImpl(Object o1, Object o2) {
            this.o1 = o1;
            this.o2 = o2;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            print(name,"o1","start");
            synchronized (o1) {
                print(name,"o1","execute");
                execute();

                print(name,"o2","start");
                synchronized (o2){
                    print(name,"o2","execute");
                    execute();
                    print(name,"o2","end");
                }
            }
            print(name,"o1","end");
        }

        private void print(String threadName,String object,String suffix) {
            System.out.println(threadName+" "+object+" "+suffix);
        }
        private void execute() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void execute() {
        Object o1 = new Object();
        Object o2 = new Object();

        RunableImpl r1 = new RunableImpl(o1,o2);
        RunableImpl r2 = new RunableImpl(o2,o1);
        taskExecutor.execute(r1);
        taskExecutor.execute(r2);
    }
}