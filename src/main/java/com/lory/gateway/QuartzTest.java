package com.lory.gateway;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {
    public static void main(String[] args) throws SchedulerException {
        JobDetail j = JobBuilder.newJob(EmailCronJob.class).build();
        Trigger t1 = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
//        Trigger t2 = TriggerBuilder.newTrigger().withIdentity("CroneTrigger")
//                .withSchedule(CronScheduleBuilder.cronSchedule(" 0 0 10 ? * * *")).startNow().build();
        Scheduler s = StdSchedulerFactory.getDefaultScheduler();
        s.start();
        s.scheduleJob(j, t1);

    }
}
