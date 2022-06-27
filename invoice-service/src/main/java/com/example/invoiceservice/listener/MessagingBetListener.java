package com.example.invoiceservice.listener;

import com.example.invoiceservice.config.MessagingConfig;
import com.example.invoiceservice.dto.CreatedBetResponseDTO;
import com.example.invoiceservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessagingBetListener {

    private final InvoiceService invoiceService;

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(CreatedBetResponseDTO responseDTO) {
        try {
            log.info("Message Received from queue: " + responseDTO.toString());
            invoiceService.save(responseDTO);
        } catch (Exception e) {
            log.error("Can't handle message!");
        }
    }

}
