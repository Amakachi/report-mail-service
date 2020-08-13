package com.seamfix.Demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
@Data
public class ReportDTO {
    private String title;
    private Date date;
    private String fileName;
    private MultipartFile file;

}
