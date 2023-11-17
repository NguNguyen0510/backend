package com.example.forums_backend.dto;

import com.example.forums_backend.entity.my_enum.ReportStatus;
import com.example.forums_backend.entity.my_enum.ReportType;

//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class ReportRequestDto {
//    private Post post;
//    private String detail;
//    private int type;
//    private Report.Status status; // Thêm trường status với kiểu dữ liệu enum tương tự trong entity
//
//    public Report.Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Report.Status status) {
//        this.status = status;
//    }
//}
// dto/ReportRequestDto.java
public class ReportRequestDto {
    private Long postId;
    private Long account;
    private ReportType reportType;
    private String reason;
    private ReportStatus reportStatus;
    public ReportRequestDto() {
        // Set giá trị mặc định cho reportStatus là PENDING khi tạo đối tượng
        this.reportStatus = ReportStatus.PENDING;
    }

    // Getters and setters
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
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }
}
