//package com.seamfix.Demo.service.impl;
//
//import com.seamfix.Demo.model.CronJobExpression;
//import com.seamfix.Demo.repository.CronJobExpressionRepo;
////import com.seamfix.Demo.service.EmailJob;
//import com.seamfix.Demo.service.SchedularService;
//
//import org.quartz.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.UUID;
//
//import static org.quartz.CronScheduleBuilder.cronSchedule;
//
//@Service
//public class SchedularServiceImpl implements SchedularService {
//    @Autowired
//    CronJobExpressionRepo cronJobExpressionRepo;
//    @Autowired
//    private Scheduler scheduler;
//
//    @Override
//    public void keepCronJobEprsDetials(String username, String cronExpression, String cronExprDesc, String category) throws SchedulerException, SchedulerException {
//        CronJobExpression cronJobExpression = new CronJobExpression();
//        cronJobExpression.setUsername(username);
//        cronJobExpression.setCreatedOn(new Date());
//        cronJobExpression.setFlag("Y");
//        cronJobExpression.setCronExpression(cronExpression);
//        cronJobExpression.setCronExpressionDesc(cronExprDesc);
//        cronJobExpression.setCategory(category);
//
////        JobDetail jobDetail = buildJobDetail(cronJobExpression);
////        Trigger trigger = buildJobTrigger(jobDetail, cronJobExpression);
////        scheduler.scheduleJob(jobDetail, trigger);
////
////        buildJobDetail(cronJobExpression);
////        cronJobExpressionRepo.save(cronJobExpression);
//    }
//    @Override
//    public void deleteRunningJob(String category)  {
//        CronJobExpression cronJobExpression = cronJobExpressionRepo.findLastByFlagAndCategory("Y",category);
//        System.out.println("the job to be deleted {}"+cronJobExpression);
//        if (cronJobExpression != null){
//
//            cronJobExpression.setFlag("N");
//            cronJobExpressionRepo.save(cronJobExpression);
//        }
//    }
//
//
//    public String getCurrentExpression(String category) {
//        CronJobExpression cronJobExpression = cronJobExpressionRepo.findLastByFlagAndCategory("Y",category);
//
//        return cronJobExpression.getCronExpression();
//       }
//
//
//
////    private JobDetail buildJobDetail(CronJobExpression cronJobExpression) {
////        JobDataMap jobDataMap = new JobDataMap();
////
////        jobDataMap.put("email", cronJobExpression.getUsername());
////        jobDataMap.put("subject", "");
////
////
////        return JobBuilder.newJob(EmailJob.class)
////                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
////                .withDescription("Send Email Job")
////                .usingJobData(jobDataMap)
////                .storeDurably()
////                .build();
////    }
//
//    private Trigger buildJobTrigger(JobDetail jobDetail, CronJobExpression cronJobExpression) {
//        return TriggerBuilder.newTrigger()
//                .forJob(jobDetail)
//                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
//                .withDescription("Send Email Trigger")
//                .withSchedule(cronSchedule(cronJobExpression.getCronExpression()))
//                .forJob("myJob", "group1")
//                .build();
//    }
//}
