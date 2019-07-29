package org.gavin.config;

import org.gavin.quartz.OrderQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail orderjobDetail(){
        return JobBuilder.newJob(OrderQuartz.class)
                         .withIdentity("orderQuartz")
                         .storeDurably()
                         .build();
    }

    @Bean
    public Trigger orderTrigger(){
        CronScheduleBuilder scheduleBuilder
                = CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");
        return TriggerBuilder
                .newTrigger()
                .forJob(orderjobDetail())
                .withIdentity("orderQuartz")
                .withSchedule(scheduleBuilder).build();
    }

}
