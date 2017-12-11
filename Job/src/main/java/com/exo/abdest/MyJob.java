package com.exo.abdest;

/**
 * Created by exo on 22/09/16.
 */
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class MyJob implements Job {



    private static final Log log = ExoLogger.getLogger(MyJob.class);



    public void execute(JobExecutionContext context) throws JobExecutionException {

        log.warn("-----------------");
        log.info("My Job ");
        log.warn("-----------------");
    }

}