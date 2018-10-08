package cn.willow.demo.keeper;

import cn.willow.demo.keeper.cn.willow.demo.keeper.schedule.KeeperJob;
import org.quartz.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Willow
 */
@SpringBootApplication
public class DemoHealthKeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoHealthKeeperApplication.class, args);
    }

    @Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder.newJob(KeeperJob.class).withIdentity("keeperJob")
                .usingJobData("name", "keeperJob").storeDurably().build();
    }

    @Bean
    public Trigger sampleJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2).repeatForever();

        return TriggerBuilder.newTrigger().forJob(sampleJobDetail())
                .withIdentity("sampleTrigger").withSchedule(scheduleBuilder).build();
    }
}
