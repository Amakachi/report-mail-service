package com.seamfix.Demo.service.impl;

import com.seamfix.Demo.model.CronJobExpression;
import com.seamfix.Demo.model.Report;
import com.seamfix.Demo.repository.CronJobExpressionRepo;

import com.seamfix.Demo.service.MailService;
import com.seamfix.Demo.service.ReportService;
import com.seamfix.Demo.service.SchedularService;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service
public class SchedularServiceImpl implements SchedularService {
    @Autowired
    CronJobExpressionRepo cronJobExpressionRepo;

    @Autowired
    private ReportService reportService;
    @Autowired
    private MailService mailService;
    @Autowired
    private ScheduleTaskService scheduleTaskService;

    @Value("${document.path}")
    private String path;

    @Override
    public void keepCronJobEprsDetials(String username, String cronExpression, String cronExprDesc, String category){
        CronJobExpression cronJobExpression = new CronJobExpression();
        cronJobExpression.setUsername(username);
        cronJobExpression.setCreatedOn(new Date());
        cronJobExpression.setCronExpression(cronExpression);
        cronJobExpression.setCronExpressionDesc(cronExprDesc);
        cronJobExpression.setCategory(category);
        cronJobExpression.setReport(reportService.findReportByFileName(category));
        cronJobExpressionRepo.save(cronJobExpression);
        ScheduleTask scheduleTask = new ScheduleTask(cronJobExpression, mailService, path, cronJobExpressionRepo);
        scheduleTaskService.addTaskToScheduler(cronJobExpression.getId(), scheduleTask, cronJobExpression.getCronExpression());
    }
    @Override
    public void deleteRunningJob(Long id)  {
        if (id != null){
//            logger.info("about deleting");
            scheduleTaskService.removeTaskFromScheduler(id);
            cronJobExpressionRepo.deleteById(id);

        }
    }


}
