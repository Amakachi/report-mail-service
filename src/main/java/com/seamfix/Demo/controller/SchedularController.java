package com.seamfix.Demo.controller;


import com.seamfix.Demo.service.SchedularService;
import com.seamfix.Demo.utils.CronJobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class SchedularController {

    @Autowired
    SchedularService schedularService;

//    @PostMapping("/manage/job")
//    public String getCronExpression(WebRequest webRequest, RedirectAttributes redirectAttributes) throws SchedulerException {
//        String username= webRequest.getParameter("email");;
//        String category= webRequest.getParameter("category");
//        String schedule  = webRequest.getParameter("scheduler");
//        System.out.println("schedule {} and username {} category {}"+schedule+username+category);
//        if((schedule != null)&&(!schedule.equalsIgnoreCase(""))){
//            String cronExpression = CronJobUtils.getCronExpression(schedule, webRequest).get("value");
//            String cronExprValue = CronJobUtils.getCronExpression(schedule, webRequest).get("desc");
//            System.out.println("expression value {} and description {}"+cronExpression+cronExprValue);
//            if(!cronExpression.equalsIgnoreCase("")) {
//                schedularService.deleteRunningJob(category);
//                schedularService.keepCronJobEprsDetials(username, cronExpression,cronExprValue,category);
//                redirectAttributes.addFlashAttribute("message", "job.update.success");
//                return "/";
//            }
//        }
//        redirectAttributes.addFlashAttribute("message","job.update.failed");
//        return "redirect:/dashboard/job";
//    }

    @PostMapping("/manage/job")
    public String getCronExpression(WebRequest webRequest, RedirectAttributes redirectAttributes)  {
        String username= webRequest.getParameter("email");;
        String category= webRequest.getParameter("category");
        String schedule  = webRequest.getParameter("scheduler");
        System.out.println("schedule {} and username {} category {}"+schedule+username+category);
        if((schedule != null)&&(!schedule.equalsIgnoreCase(""))){
            String cronExpression = CronJobUtils.getCronExpression(schedule, webRequest).get("value");
            String cronExprValue = CronJobUtils.getCronExpression(schedule, webRequest).get("desc");
            System.out.println("expression value {} and description {}"+cronExpression+cronExprValue);
            if(!cronExpression.equalsIgnoreCase("")) {
                schedularService.deleteRunningJob(category);
                schedularService.keepCronJobEprsDetials(username, cronExpression,cronExprValue,category);
                redirectAttributes.addFlashAttribute("message", "job.update.success");
                return "redirect:/report/all-reports";

            }
        }
        redirectAttributes.addFlashAttribute("message","job.update.failed");
        return "redirect:/";
    }

    @GetMapping(path = "report/all-reports")
    public String getAllReports(Model model)
    {
        return "dashboard/view-report";
    }


}
