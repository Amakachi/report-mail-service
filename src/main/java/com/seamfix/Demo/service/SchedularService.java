package com.seamfix.Demo.service;

import org.quartz.SchedulerException;

public interface SchedularService {
    void keepCronJobEprsDetials(String username, String cronExpression, String cronExprDesc, String category) throws SchedulerException;

    void deleteRunningJob(String category);

//   String getCurrentExpression(String category);
}
