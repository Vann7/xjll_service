package com.van.webSpring.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * 定时器
 */
@Service
public class QuartzJob {

    @Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder.newJob(SampleJob.class).withIdentity("sampleJob")
                .usingJobData("name", "World").storeDurably().build();
    }


    @Bean
    public Trigger sampleJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(15).withRepeatCount(10);
        return TriggerBuilder.newTrigger().forJob(sampleJobDetail())
                .withIdentity("sampleTrigger").withSchedule(scheduleBuilder).build();
    }
}
