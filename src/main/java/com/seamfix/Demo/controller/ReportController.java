package com.seamfix.Demo.controller;

import com.seamfix.Demo.dto.ReportDTO;
import com.seamfix.Demo.model.Report;
import com.seamfix.Demo.service.ReportService;
import com.seamfix.Demo.service.impl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    ReportService reportService;

    @ModelAttribute
    public void init(Model model) {
        List<Report> reports = reportService.getAllReports();
        model.addAttribute("reportlist", reports);
    }

    @GetMapping("/")
    public String dashBoard(Model model,  HttpServletResponse response){
        return "dashboard/manage-job";
    }

    @GetMapping("report/add")
    public String addReport(Model model){
        model.addAttribute("reportDTO", new ReportDTO());
        return "dashboard/add";
    }
    @PostMapping("report/create")
    public String create(@ModelAttribute("reportDTO") ReportDTO reportDTO, RedirectAttributes redirectAttributes){
        try {
            String message = reportService.addReport(reportDTO);
            redirectAttributes.addFlashAttribute("success",message);
            return "redirect:/report/listview";
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("failed",e.getMessage());
            return "/report/add";
        }

    }
    @GetMapping("report/listview")
    public String getList(Model model){
        return "dashboard/view";
    }

    @GetMapping(path = "report/all")
    public @ResponseBody List<Report> getReports()
    {
        return reportService.getAllReports();
    }

    @GetMapping(path = "report/all-reports")
    public String getAllReports(Model model)
    {
        return "dashboard/view-report";
    }

    @GetMapping("report/{id}/details")
    public String getReportById(@PathVariable Long id,Model model){
        return "";
    }


    @RequestMapping(value = "/doUpload", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public String saveImage(@RequestParam MultipartFile file){
        return null;
    }

}
