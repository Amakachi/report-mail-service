package com.seamfix.Demo.repository;

import com.seamfix.Demo.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {


}
