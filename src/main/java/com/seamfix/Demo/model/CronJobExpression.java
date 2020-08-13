package com.seamfix.Demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;


@Entity
public class CronJobExpression {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cronExpression;
    private Date createdOn;
    private String username;
    private String flag;
    private String cronExpressionDesc;
    private String category;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCronExpressionDesc() {
        return cronExpressionDesc;
    }

    public void setCronExpressionDesc(String cronExpressionDesc) {
        this.cronExpressionDesc = cronExpressionDesc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CronJobExpression{" +
                "cronExpression='" + cronExpression + '\'' +
                ", createdOn=" + createdOn +
                ", username='" + username + '\'' +
                ", flag='" + flag + '\'' +
                ", cronExpressionDesc='" + cronExpressionDesc + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
