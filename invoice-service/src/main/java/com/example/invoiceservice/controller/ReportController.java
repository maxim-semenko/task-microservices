package com.example.invoiceservice.controller;

import com.example.invoiceservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService pdfGenerateService;

    @Autowired
    public ReportController(ReportService pdfGenerateService) {
        this.pdfGenerateService = pdfGenerateService;
    }

    @GetMapping("/bet-history/{userId}")
    public ResponseEntity<byte[]> generateBetHistoryPdfReportByUserId(@PathVariable Long userId) {
        return pdfGenerateService.generateBetHistoryPdfReportByUserId(userId);
    }
}
