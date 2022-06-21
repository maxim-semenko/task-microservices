package com.example.invoiceservice.service;

import java.util.Map;

public interface PdfGeneratorService {

    public byte[] generatePdfFile(String templateName, Map<String, Object> data);

}
