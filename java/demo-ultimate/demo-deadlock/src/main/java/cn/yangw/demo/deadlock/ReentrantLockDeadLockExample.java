package cn.yangw.demo.deadlock;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class ReentrantLockDeadLockExample {
    private final TaskExecutor taskExecutor;
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    @Autowired
    public ReentrantLockDeadLockExample(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @AllArgsConstructor
    public class RunnableImpl implements Runnable {
        private boolean criticalResource;

        public RunnableImpl(boolean criticalResource) {
            this.criticalResource = criticalResource;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            try {
                print(name, criticalResource, "start");
                if (criticalResource) {
                    lock1.tryLock(10, TimeUnit.SECONDS);
                    print(name, criticalResource, "lock1 executing");
                    execute();
                    print(name, criticalResource, "lock1 executed");

                    lock2.tryLock(10, TimeUnit.SECONDS);
                    print(name, criticalResource, "lock2 executing");
                    execute();
                    print(name, criticalResource, "lock2 executed");
                } else {
                    lock2.tryLock(10, TimeUnit.SECONDS);
                    print(name, criticalResource, "lock2 executing");
                    execute();
                    print(name, criticalResource, "lock2 executed");

                    lock1.tryLock(10, TimeUnit.SECONDS);
                    print(name, criticalResource, "lock1 executing");
                    execute();
                    print(name, criticalResource, "lock1 executed");
                }
                print(name,criticalResource,"done");
            } catch (Exception e) {
                print(name, criticalResource, "exception");
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }
            }
        }

        private void print(String threadName, boolean criticalResource, String message) {
            System.out.println(threadName + " criticalResource:" + criticalResource + " " + message);
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
        RunnableImpl r1 = new RunnableImpl(true);
        RunnableImpl r2 = new RunnableImpl(false);
        taskExecutor.execute(r1);
        taskExecutor.execute(r2);
    }
}
