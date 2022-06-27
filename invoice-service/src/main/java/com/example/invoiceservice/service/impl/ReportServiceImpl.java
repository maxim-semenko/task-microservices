package com.example.invoiceservice.service.impl;

import com.example.invoiceservice.dto.UserResponseDTO;
import com.example.invoiceservice.entity.Invoice;
import com.example.invoiceservice.exception.ResourseNotFoundException;
import com.example.invoiceservice.feignclient.UserFeignClient;
import com.example.invoiceservice.repository.InvoiceRepository;
import com.example.invoiceservice.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final PdfGeneratorServiceImpl pdfGeneratorService;
    private final UserFeignClient userFeignClient;
    private final InvoiceRepository invoiceRepository;

    /**
     * Method that return bet history report by userId.
     *
     * @param userId needed user's id.
     * @return the response of file's bytes
     */
    @Override
    public ResponseEntity<byte[]> generateBetHistoryPdfReportByUserId(Long userId) {
        UserResponseDTO userResponseDTO;
        try {
            userResponseDTO = userFeignClient.getUserById(userId);
        } catch (Exception e) {
            throw new ResourseNotFoundException("User was not found!");
        }
        List<Invoice> invoicesHistory = invoiceRepository.findAllByUserId(userId);

        Map<String, Object> data = new HashMap<>();
        data.put("name", userResponseDTO.getTitle());
        data.put("invoiceList", invoicesHistory);

        byte[] fileBytes = pdfGeneratorService.generatePdfFile("BetHistoryReport", data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("pdf-report", "pdf-report");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }
}