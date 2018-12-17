package cn.yangw.demo.deadlock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeadlockApplication {
    private static SynchronizedDeadLockExample synchronizedDeadLockExample = null;
    private static ReentrantLockDeadLockExample reentrantLockDeadLockExample = null;

    public DeadlockApplication(SynchronizedDeadLockExample synchronizedDeadLockExample,ReentrantLockDeadLockExample reentrantLockDeadLockExample) {
        DeadlockApplication.synchronizedDeadLockExample = synchronizedDeadLockExample;
        DeadlockApplication.reentrantLockDeadLockExample = reentrantLockDeadLockExample;
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DeadlockApplication.class, args);
//        DeadlockApplication.synchronizedDeadLockExample.execute();
        DeadlockApplication.reentrantLockDeadLockExample.execute();
    }
}
