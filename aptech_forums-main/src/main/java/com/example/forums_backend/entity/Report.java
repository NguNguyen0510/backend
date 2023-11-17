package com.example.forums_backend.entity;

import com.example.forums_backend.entity.my_enum.ReportStatus;
import com.example.forums_backend.entity.my_enum.ReportType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id",nullable = false)
    private Account account;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "post_id")
    private Post postId;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_type")
    private ReportType reportType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReportStatus reportStatus;

    private String reason;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Report() {
        this.reportStatus = ReportStatus.PENDING; // Đặt trạng thái mặc định là "Pending"
    }
    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getCreated_At() {
        return createdAt;
    }

    public LocalDateTime getUpdated_At() {
        return updatedAt;
    }
}
