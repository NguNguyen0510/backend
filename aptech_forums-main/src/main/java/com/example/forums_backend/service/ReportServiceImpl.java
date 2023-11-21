package com.example.forums_backend.service;

import com.example.forums_backend.dto.ReportRequestDto;
import com.example.forums_backend.dto.ReportResDto;
import com.example.forums_backend.entity.Account;
import com.example.forums_backend.entity.Report;
import com.example.forums_backend.entity.my_enum.ReportStatus;
import com.example.forums_backend.exception.ReportNotFoundException;
import com.example.forums_backend.repository.ReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report createReport(ReportRequestDto reportRequestDto) {
        System.out.println("Debug - reportRequestDto: " + reportRequestDto.toString());

        Report reportEntity = convertToEntity(reportRequestDto);

        // Verify that the Account in the Report entity is not null if needed
//        if (reportEntity.getAccount() == null) {
//            throw new IllegalArgumentException("Account in Report entity cannot be null");
//        }

        return reportRepository.save(reportEntity);
    }

    private Report convertToEntity(ReportRequestDto reportRequestDto) {
        ModelMapper modelMapper = new ModelMapper();

        // Custom converter for mapping account_id to Account entity
        modelMapper.addConverter(ctx -> {
            Long accountId = (Long) ctx.getSource();
            if (accountId != null) {
                Account account = new Account();
                account.setId(accountId);
                return account;
            } else {
                return null; // Handle the case when account_id is null
            }
        }, Long.class, Account.class);

        return modelMapper.map(reportRequestDto, Report.class);
    }




    @Override
    public void deleteReport(Long reportId) {
        reportRepository.deleteById(reportId);
    }

    @Override
    public List<ReportResDto> getAllReports() {
        List<Report> reportEntities = reportRepository.findAll();
        List<ReportResDto> reportDTOs = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();


        for (Report reportEntity : reportEntities) {
            ReportResDto reportDTO = modelMapper.map(reportEntity, ReportResDto.class);
            reportDTOs.add(reportDTO);
        }

        return reportDTOs;
    }


    @Override
    public List<ReportResDto> getResolvedReports() {
        try {
            List<Report> resolvedReports = reportRepository.findByReportStatus(ReportStatus.RESOLVED);

            if (resolvedReports.isEmpty()) {
                throw new ReportNotFoundException("No resolved reports found");
            }

            List<ReportResDto> resolvedReportDTOs = new ArrayList<>();
            for (Report reportEntity : resolvedReports) {
                ReportResDto reportDTO = new ReportResDto();
                BeanUtils.copyProperties(reportEntity, reportDTO);
                resolvedReportDTOs.add(reportDTO);
            }

            return resolvedReportDTOs;
        } catch (Exception exception) {
            // Ném lại ReportNotFoundException với thông báo lỗi
            throw new ReportNotFoundException("Error while fetching resolved reports: " + exception.getMessage());
        }
    }
    @Override
    public List<ReportResDto> getPendingReports() {
        try {
            List<Report> pendingReports = reportRepository.findByReportStatus(ReportStatus.PENDING);

            if (pendingReports.isEmpty()) {
                throw new ReportNotFoundException("No pending reports found");
            }

            List<ReportResDto> pendingReportDTOs = new ArrayList<>();
            for (Report reportEntity : pendingReports) {
                ReportResDto reportDTO = new ReportResDto();
                BeanUtils.copyProperties(reportEntity, reportDTO);
                pendingReportDTOs.add(reportDTO);
            }

            return pendingReportDTOs;
        } catch (Exception exception) {
            // Ném lại ReportNotFoundException với thông báo lỗi
            throw new ReportNotFoundException("Error while fetching pending reports: " + exception.getMessage());
        }
    }

    @Override
    public ReportResDto updateReport(Long reportId, ReportRequestDto reportRequestDto) {
        // Kiểm tra xem report có tồn tại không
        Report existingReport = reportRepository.findById(reportId)
                .orElseThrow(() -> new ReportNotFoundException("Report not found"));

        // Cập nhật thông tin từ reportRequestDto vào existingReport
        existingReport.setReportType(reportRequestDto.getReportType());
        existingReport.setReason(reportRequestDto.getReason());
        existingReport.setReportStatus(reportRequestDto.getReportStatus());

        // Lưu cập nhật vào repository
        Report updatedReport = reportRepository.save(existingReport);

        // Chuyển đổi từ entity đã cập nhật thành đối tượng DTO và trả về
        return convertToDto(updatedReport);
    }





    private ReportResDto convertToDto(Report reportEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reportEntity, ReportResDto.class);
    }

    @Override
    public Report getReport(Long reportId) {
        // Thực hiện lấy report từ repository
        return reportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Report not found"));
    }
//    private ReportResDto convertToDto(Report reportEntity) {
//
//        ReportResDto reportDTO = modelMapper.map(reportEntity, ReportResDto.class);
//
//        // Thêm ánh xạ cho trường account
//        if (reportEntity.getAccount() != null) {
//            reportDTO.setAccountId(reportEntity.getAccount().getId());
//        }
//
//        return reportDTO;
//    }

}
