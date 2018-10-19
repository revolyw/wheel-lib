package cn.yangw.demo.deadlock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeadlockApplication {
    private static TaskExecutorExample executorExample = null;

    public DeadlockApplication(TaskExecutorExample executorExample) {
        DeadlockApplication.executorExample = executorExample;
    }

    public static void main(String[] args) {
        SpringApplication.run(DeadlockApplication.class, args);
        DeadlockApplication.executorExample.printMessages();
        System.out.println("done");
    }
}
