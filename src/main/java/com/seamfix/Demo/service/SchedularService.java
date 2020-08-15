package com.seamfix.Demo.service;


import org.springframework.stereotype.Service;


public interface SchedularService {

   void keepCronJobEprsDetials(String username, String cronExpression, String cronExprDesc, String category);


    void deleteRunningJob(Long id);
//
//     String getCurrentExpression(String category);
}
