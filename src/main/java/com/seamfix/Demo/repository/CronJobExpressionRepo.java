package com.seamfix.Demo.repository;


import com.seamfix.Demo.model.CronJobExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CronJobExpressionRepo extends JpaRepository<CronJobExpression,Long> {
    CronJobExpression findLastByFlagAndCategory(String flag, String category);
    CronJobExpression findLastByFlag(String flag);
    CronJobExpression getCronJobExpressionByCategory(String category);

    List<CronJobExpression> findByScheduled(String scheduled);

    List<CronJobExpression> findByFlag(String flag);

}
