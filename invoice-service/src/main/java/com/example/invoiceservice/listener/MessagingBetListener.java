package com.example.invoiceservice.listener;

import com.example.invoiceservice.config.MessagingConfig;
import com.example.invoiceservice.controller.dto.CreatedBetResponseDTO;
import com.example.invoiceservice.service.InvoiceService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessagingBetListener {

    private final InvoiceService invoiceService;

    public MessagingBetListener(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(CreatedBetResponseDTO responseDTO) {
        System.out.println("Message Received from queue: " + responseDTO.toString());
        invoiceService.save(responseDTO);
    }

}
