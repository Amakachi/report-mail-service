package com.seamfix.Demo.repository;


import com.seamfix.Demo.model.CronJobExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CronJobExpressionRepo extends JpaRepository<CronJobExpression,Long> {
    CronJobExpression findLastByFlagAndCategory(String flag, String category);
    CronJobExpression findLastByFlag(String flag);
    CronJobExpression getCronJobExpressionByCategory(String category);
}
