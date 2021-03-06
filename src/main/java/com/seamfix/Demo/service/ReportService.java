package com.seamfix.Demo.service;

import com.seamfix.Demo.dto.ReportDTO;
import com.seamfix.Demo.model.CronJobExpression;
import com.seamfix.Demo.model.Report;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReportService {

    List<Report> getAllReports();

    List<CronJobExpression> getAllScheduledReports();

    Report findReportByFileName(String fileName);

    String saveScheduledReport(String scheduledDate, String email, String title, String description, List recurringDays );

    String uploadFile(MultipartFile file);

    String addReport(ReportDTO reportDTO);

}
