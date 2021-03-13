package com.lory.gateway;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class EmailCronJob implements Job {
    protected static String body;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        EmailFormat e = new EmailFormat();
        EmailFormat mailutils = new EmailFormat();
        try {
            mailutils.sendMail(
                        "Hurry! The price of Bitcoin is exceeding 50000!",
                        body,
                        "wmj1997happy@gmail.com",
                        "wmj1997happy@gmail.com, mw3357@tc.columbia.edu");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
