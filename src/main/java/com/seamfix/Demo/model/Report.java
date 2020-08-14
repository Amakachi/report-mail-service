package com.seamfix.Demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileName;
    private String title;
    private Date date;

}
