package com.example.invoiceservice.controller;

import com.example.invoiceservice.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService pdfGenerateService;

    @GetMapping("/bet-history/{userId}")
    public ResponseEntity<byte[]> generateBetHistoryPdfReportByUserId(@PathVariable Long userId) {
        return pdfGenerateService.generateBetHistoryPdfReportByUserId(userId);
    }
}
