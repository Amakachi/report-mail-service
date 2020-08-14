package com.seamfix.Demo.service;


import org.springframework.stereotype.Service;


public interface SchedularService {

   void keepCronJobEprsDetials(String username, String cronExpression, String cronExprDesc, String category);

    void deleteRunningJob(String category);
//
//     String getCurrentExpression(String category);
}
