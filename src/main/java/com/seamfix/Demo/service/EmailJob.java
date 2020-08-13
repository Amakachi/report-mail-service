//package com.seamfix.Demo.service;
//
//import org.quartz.JobDataMap;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.mail.MailProperties;
//
//
//import org.springframework.scheduling.quartz.QuartzJobBean;
//import org.springframework.scheduling.quartz.SpringBeanJobFactory;
//import org.springframework.stereotype.Component;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.nio.charset.StandardCharsets;
//
//@Component
//public class EmailJob extends QuartzJobBean {
//    private static final Logger logger = LoggerFactory.getLogger(EmailJob.class);
//
//
//    @Autowired
//    MailService mailService;
//
//    @Override
//    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey());
//
//        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
//        String subject = jobDataMap.getString("subject");
//        String body = jobDataMap.getString("body");
//        String recipientEmail = jobDataMap.getString("email");
//
//        try {
//            mailService.sendMessageWithAttachment(recipientEmail,subject,body,null);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
//
// }