package com.example.gamblingservice.controller;

import com.example.gamblingservice.controller.dto.CreateBetRequestDTO;
import com.example.gamblingservice.controller.dto.CreatedBetResponseDTO;
import com.example.gamblingservice.service.GamblingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/gambling")
public class GamblingController {

    private final GamblingService gamblingService;

    @Autowired
    public GamblingController(GamblingService gamblingService) {
        this.gamblingService = gamblingService;
    }

    @PostMapping("/")
    public ResponseEntity<CreatedBetResponseDTO> createBet(@RequestBody @Valid CreateBetRequestDTO requestDTO) {
        return new ResponseEntity<>(gamblingService.createBet(requestDTO), HttpStatus.CREATED);
    }
}
