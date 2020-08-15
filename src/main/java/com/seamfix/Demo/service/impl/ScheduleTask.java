package com.seamfix.Demo.service.impl;

import com.seamfix.Demo.model.CronJobExpression;
import com.seamfix.Demo.repository.CronJobExpressionRepo;
import com.seamfix.Demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;

import javax.mail.MessagingException;

public class ScheduleTask implements Runnable{

    private CronJobExpression cronJobExpression;

    private MailService mailService;

    private String path;

    private CronJobExpressionRepo cronJobExpressionRepo;

    public ScheduleTask(CronJobExpression cronJobExpression, MailService mailService, String path, CronJobExpressionRepo cronJobExpressionRepo) {
        this.cronJobExpression = cronJobExpression;
        this.mailService = mailService;
        this.path = path;
        this.cronJobExpressionRepo = cronJobExpressionRepo;

    }

    @Override
    public void run() {
        System.out.println("-------Sending mail-------- " + cronJobExpression.getId());
        String filepath = "";
        filepath = path + cronJobExpression.getCategory();
        try {
            mailService.sendMessageWithAttachment(cronJobExpression.getUsername(), cronJobExpression.getReport().getTitle(), "", filepath);
            cronJobExpression.setFlag("Y");
            cronJobExpressionRepo.save(cronJobExpression);
        } catch (MailException | MessagingException ex){
            ex.printStackTrace();
        }
        System.out.println("-------Mail sent-------- " + cronJobExpression.getId());
    }
}
