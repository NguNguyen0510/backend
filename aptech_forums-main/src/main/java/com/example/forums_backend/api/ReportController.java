package com.example.forums_backend.api;
//
//import com.example.forums_backend.dto.ReportRequestDto;

import com.example.forums_backend.dto.ReportRequestDto;
import com.example.forums_backend.dto.ReportResDto;
import com.example.forums_backend.entity.Report;
import com.example.forums_backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

//    @PostMapping("/create")
//    public ResponseEntity<String> createReport(@RequestBody ReportRequestDto reportRequestDto) {
//        // Xử lý yêu cầu tạo mới báo cáo ở đây
//        // reportService.createReport(reportRequestDto);
//        return new ResponseEntity<>("Report created successfully", HttpStatus.CREATED);
//    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<String> deleteReport(@PathVariable Long reportId) {
        reportService.deleteReport(reportId);
        return new ResponseEntity<>("Report deleted successfully", HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<ReportResDto>> getAllReports() {
        List<ReportResDto> reportEntities = reportService.getAllReports();
        List<ReportResDto> responseDtos = convertToDtoList(reportEntities);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
    @GetMapping("/resolved")
    public ResponseEntity<List<ReportResDto>> getResolvedReports() {
        List<ReportResDto> reportResDtos = reportService.getResolvedReports();
        List<ReportResDto> responseDtos = convertToDtoList(reportResDtos);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
    @GetMapping("/pending")
    public ResponseEntity<List<ReportResDto>> getPendingReports() {
        List<ReportResDto> reportResDtos = reportService.getPendingReports();
        List<ReportResDto> responseDtos = convertToDtoList(reportResDtos);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createReport(@RequestBody ReportRequestDto reportRequestDto) {
        System.out.println("Debug - createReport - reportRequestDto: " + reportRequestDto.toString());
        reportService.createReport(reportRequestDto);
        return new ResponseEntity<>("Report created successfully", HttpStatus.CREATED);
    }
    @PutMapping("/{reportId}")
    public ResponseEntity<ReportResDto> updateReport(@PathVariable Long reportId,
                                                     @RequestBody ReportRequestDto reportRequestDto) {
        ReportResDto updatedReport = reportService.updateReport(reportId, reportRequestDto);
        return new ResponseEntity<>(updatedReport, HttpStatus.OK);
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<ReportResDto> getReport(@PathVariable Long reportId) {
        Report reportEntity = reportService.getReport(reportId);
        ReportResDto responseDto = convertToDto(reportEntity);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    private ReportResDto convertToDto(Report reportEntity) {
        // Thực hiện chuyển đổi từ ReportEntity sang ReportResponseDto
        // Ví dụ:
        ReportResDto responseDto = new ReportResDto();
        responseDto.setId(reportEntity.getId());
        responseDto.setReportType(reportEntity.getReportType());
        responseDto.setReason(reportEntity.getReason());
        responseDto.setAccount(reportEntity.getAccount());
        // Các trường khác...

        return responseDto;
    }

    private List<ReportResDto> convertToDtoList(List<ReportResDto> reportResDtos) {
        return reportResDtos;
    }

}
