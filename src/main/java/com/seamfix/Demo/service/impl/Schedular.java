package com.seamfix.Demo.service.impl;

import com.seamfix.Demo.model.CronJobExpression;
import com.seamfix.Demo.model.Report;
import com.seamfix.Demo.repository.CronJobExpressionRepo;
import com.seamfix.Demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Component
public class Schedular implements Runnable {

    @Autowired
    CronJobExpressionRepo cronJobExpressionRepo;
    private String cronEx;
    @SuppressWarnings("rawtypes")
    ScheduledFuture scheduledFuture;
    @Autowired
    MailService mailService;
    TaskScheduler taskScheduler ;

    @Value("${image.path}")
    private String path;

    //if you want on application to read data on startup from db and schedule the schduler use following method

    @Scheduled(cron = "${schedule.cron}")
    public void initializeScheduler() {
        //method will be called after creating all beans in application context
        // read user config map from db
        // get cron expression created
        System.out.println("-----Initializing Task------");
        List<CronJobExpression> cronJobExpressions = cronJobExpressionRepo.findByScheduled("N");

        for ( CronJobExpression cronJobExpression: cronJobExpressions) {
            cronEx = cronJobExpression.getCronExpression();
            this.reSchedule(cronEx);
            cronJobExpression.setScheduled("Y");
            cronJobExpressionRepo.save(cronJobExpression);
        }
    }

    public List<CronJobExpression> fetchJobFromDb(){
        List<CronJobExpression> cronJobExpressions = cronJobExpressionRepo.findByScheduled("N");
        return cronJobExpressions;
    }
    //this method will kill previous scheduler if exists and will create a new scheduler with given cron expression
    public  void reSchedule(String cronExpressionStr){
        System.out.println("-----Rescheduling Task------");
        if(taskScheduler== null){
            this.taskScheduler = new ConcurrentTaskScheduler();
        }
//        if (this.scheduledFuture() != null) {
//            this.scheduledFuture().cancel(true);
//        }
        this.scheduledFuture = this.taskScheduler.schedule(this, new CronTrigger(cronExpressionStr));
    }

    @Override
    public  void run(){
// task to be performed
        System.out.println("-----About to run task------");
        String filepath = "";
        List<CronJobExpression> cronJobExpressions = cronJobExpressionRepo.findByFlag("N");

        for (CronJobExpression cronJobExpression: cronJobExpressions) {
            filepath = path + cronJobExpression.getCategory();
            try {
                mailService.sendMessageWithAttachment(cronJobExpression.getUsername(), cronJobExpression.getReport().getTitle(), "", filepath);
                cronJobExpression.setFlag("Y");
                cronJobExpressionRepo.save(cronJobExpression);
            } catch (MailException | MessagingException ex){
                ex.printStackTrace();
        }
            System.out.println("-----Running Task------");

    }
}
}
