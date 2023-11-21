package com.example.forums_backend.dto;

import com.example.forums_backend.entity.Account;
import com.example.forums_backend.entity.Post;
import com.example.forums_backend.entity.my_enum.ReportStatus;
import com.example.forums_backend.entity.my_enum.ReportType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReportResDto {
        private Long id;
        private Account account;
//        private Long accountId;
        private Post postId;
        private ReportType reportType;
        private ReportStatus status;
        private String reason;
        private String username;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;


    }