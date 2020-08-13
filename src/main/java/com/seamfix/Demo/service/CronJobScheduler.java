//package com.seamfix.Demo.service;
//
//import com.seamfix.Demo.utils.SpringContext;
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//import org.springframework.context.ApplicationContext;
//
//public class CronJobScheduler {
//    public static void startJobs() {
//
//
//        JobKey ReportJob = new JobKey("report", "report");
//        JobDetail ReportJobs = JobBuilder.newJob(ReportJob.class)
//                .withIdentity(ReportJob).build();
//
//        ApplicationContext context = SpringContext.getApplicationContext();
//        SchedularService cronJobService = context.getBean (SchedularService.class);
//
//        try {
//            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//            scheduler.scheduleJob(ReportJobs, lc);
//
//            //scheduler.scheduleJob(OneTimeJobs, oneTime);
//            scheduler.start();
//        }catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
