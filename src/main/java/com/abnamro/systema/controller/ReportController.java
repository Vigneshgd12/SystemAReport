package com.abnamro.systema.controller;

import com.abnamro.systema.model.Report;
import com.abnamro.systema.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    private final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportService reportService;

    @PostMapping("/daily")
    public List<Report> getDailyReport(@RequestParam("file") MultipartFile inputFile) throws IOException {
        return reportService.getDailyReport(inputFile);
    }

    @PostMapping("/final")
    public List<Report> getFinalReport(@RequestParam("file") MultipartFile inputFile) throws IOException {
        return reportService.getFinalReport(inputFile);

    }

    @PostMapping("/daily/download")
    public ResponseEntity getDailyReportFile(@RequestParam("file") MultipartFile inputFile) throws IOException {
        String fileName = "dailyReport.csv";
        File file = reportService.getDailyReportFile(inputFile, fileName);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename="+fileName)
                .contentLength(file.length())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(new FileSystemResource(file));
    }


    @PostMapping("/final/download")
    public ResponseEntity getFinalReportFile(@RequestParam("file") MultipartFile inputFile) throws IOException {
        String fileName = "finalReport.csv";
        File file = reportService.getFinalReportFile(inputFile,fileName);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename="+fileName)
                .contentLength(file.length())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(new FileSystemResource(file));
    }

}
