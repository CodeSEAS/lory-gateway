package com.lory.gateway;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class EmailCronJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        EmailFormat e = new EmailFormat();
        EmailFormat mailutils = new EmailFormat();
        try {
            mailutils.sendMail(
                        "Email Tests",
                        "Mail sent by a 63 line Java code copied from: http://forums.sun.com/thread.jspa?threadID=668779",
                        "wmj1997happy@gmail.com",
                        "wmj1997happy@gmail.com, mw3357@tc.columbia.edu");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
