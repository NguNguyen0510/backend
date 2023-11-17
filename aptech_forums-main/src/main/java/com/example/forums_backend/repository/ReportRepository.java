package com.example.forums_backend.repository;

import com.example.forums_backend.dto.ReportResDto;
import com.example.forums_backend.entity.Report;
import com.example.forums_backend.entity.my_enum.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByReportStatus(ReportStatus reportStatus);

    @Query("SELECT r FROM Report r ORDER BY r.createdAt DESC")
    List<ReportResDto> findAllReports();
    @Query("SELECT r FROM Report r WHERE r.reportStatus = 'PENDING' ORDER BY r.createdAt DESC")
    List<ReportResDto> findPendingReports();
    List<Report> findById(Report id);

}
