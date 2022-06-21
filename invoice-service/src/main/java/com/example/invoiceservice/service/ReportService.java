package com.example.invoiceservice.service;

import org.springframework.http.ResponseEntity;

public interface ReportService {
    ResponseEntity<byte[]> generateBetHistoryPdfReportByUserId(Long userId);
}
