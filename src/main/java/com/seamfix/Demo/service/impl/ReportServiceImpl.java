package com.seamfix.Demo.service.impl;

import com.seamfix.Demo.dto.ReportDTO;
import com.seamfix.Demo.exception.StorageException;
import com.seamfix.Demo.model.CronJobExpression;
import com.seamfix.Demo.model.Report;
import com.seamfix.Demo.repository.CronJobExpressionRepo;
import com.seamfix.Demo.repository.ReportRepository;
import com.seamfix.Demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ReportServiceImpl implements ReportService {

    @Value("${document.path}")
    private String path;

    @Autowired
    CronJobExpressionRepo cronJobExpressionRepo;

    @Autowired
    ReportRepository reportRepository;

    @Override
    public List<Report> getAllReports() {
        List<Report> reportList = reportRepository.findAll();
        return reportList;
    }
    @Override
    public List<CronJobExpression> getAllScheduledReports(){
        List<CronJobExpression> cronJobExpressionList = cronJobExpressionRepo.findAll();
        return cronJobExpressionList;
    }

    @Override
    public Report findReportByFileName(String fileName){
       return reportRepository.findByFileName(fileName);
    }

    @Override
    public String saveScheduledReport(String scheduledDate, String email, String title, String description, List recurringDays ) {
        Report report = new Report();
        report.setTitle(title);
        reportRepository.save(report);
        return null;
    }

    public String uploadFile(MultipartFile file) {
        String uploadedFileName = "";
        File tempFile = new File(path);
        if (!tempFile.exists()) tempFile.mkdirs();

        if (file.isEmpty()) {
            System.out.println("file is empty");
        }

        try {
            byte[] bytes = file.getBytes();
            Path filePath = Paths.get(path  + file.getOriginalFilename());
            System.out.println("creating file in the directory {} with file name {}" + path + file.getOriginalFilename());


            File f = new File(filePath.toString());
            if (f.exists()) {
                System.out.println("file already exist");
            } else {
                System.out.println("file does not exist");
            }

            Files.write(filePath , bytes);
            System.out.println("file uploaded successfully to directory" + filePath);
            uploadedFileName = file.getOriginalFilename();


        } catch (IOException e) {
            System.out.println("exception " + e.getMessage());
            System.out.println("failed to upload file");
        }

        System.out.println("uploadFileName " + uploadedFileName);
        if (StringUtils.isEmpty(uploadedFileName)) {
            System.out.println("No file added, Please select a file to upload");

        } else {
            System.out.println("You successfully uploaded " + uploadedFileName);

        }
        return uploadedFileName;
    }


    private Report convertDTOToEntity(ReportDTO reportDTO){
        Report report= new Report();
        report.setFileName(reportDTO.getFileName());
        report.setTitle(reportDTO.getTitle());
        report.setDate(new Date());
        return report;
    }

    @Override
    public String addReport(ReportDTO reportDTO) {
        try {
            String filename = uploadFile(reportDTO.getFile());
            Report report = convertDTOToEntity(reportDTO);
            report.setFileName(filename);
            reportRepository.save(report);
            return "Successfully added";
        }catch (Exception e) {
           e.printStackTrace();
        }
        return "fail to add report";
    }

}
