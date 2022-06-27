package com.example.invoiceservice.service.impl;

import com.example.invoiceservice.service.PdfGeneratorService;
import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class PdfGeneratorServiceImpl implements PdfGeneratorService {

    private final TemplateEngine templateEngine;

    @Override
    public byte[] generatePdfFile(String templateName, Map<String, Object> data) {
        Context context = new Context();
        context.setVariables(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String htmlContent = templateEngine.process(templateName, context);
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream, false);
            renderer.finishPDF();
        } catch (DocumentException e) {
            log.error(e.getMessage(), e);
        }

        return outputStream.toByteArray();
    }
}
