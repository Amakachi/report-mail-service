//package com.seamfix.Demo.service;
//
//import org.springframework.scheduling.Trigger;
//import org.springframework.scheduling.TriggerContext;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//@EnableScheduling
//public class CronJobUtils implements SchedulingConfigurer {
//
//private String cronConfig() {
//return "*/30 *";
//}
//@Override
//public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//taskRegistrar.addTriggerTask(new Runnable() {
//@Override
//public void run() {
//// System.out.println("Cron job running");
//}
//}, new Trigger() {
//@Override
//public Date nextExecutionTime(TriggerContext triggerContext) {
//String cron = cronConfig();
//
//CronTrigger trigger = new CronTrigger(cron);
//Date nextExec = trigger.nextExecutionTime(triggerContext);
//
//// System.out.println("the expression is "+trigger.getExpression());
//return nextExec;
//}
//});
//}
//
//
//}