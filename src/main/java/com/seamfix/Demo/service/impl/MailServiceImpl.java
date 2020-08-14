package com.seamfix.Demo.service.impl;



import com.seamfix.Demo.dto.Mail;
import com.seamfix.Demo.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.MailException;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException {
        System.out.println("----Start mail-----");

      MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("noreply@baeldung.com");
        helper.setTo(to);
       helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Report", file);

        emailSender.send(message);

        System.out.println("----End mail-----");
        // ...
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("sylvicruza@gmail.com");
        mailSender.setPassword("centifeanyi");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
