package com.seamfix.Demo.service;

import javax.mail.MessagingException;
import java.util.Map;

public interface MailService {
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException;
}
