package com.seamfix.Demo.model;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import java.util.Date;


@Entity
@Data
public class CronJobExpression {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cronExpression;
    private Date createdOn;
    private String username;
    private String flag="N";
    private String scheduled="N";
    private String cronExpressionDesc;
    private String category;
    @OneToOne
    private Report report;

}
