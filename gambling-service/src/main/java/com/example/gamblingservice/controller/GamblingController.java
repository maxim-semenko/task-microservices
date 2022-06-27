package com.example.gamblingservice.controller;

import com.example.gamblingservice.dto.CreateBetRequestDTO;
import com.example.gamblingservice.dto.CreatedBetResponseDTO;
import com.example.gamblingservice.service.GamblingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The Gambling REST-controller.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@RestController
@RequestMapping("/gambling")
@RequiredArgsConstructor
public class GamblingController {

    /**
     * The GamblingService for working with gambling.
     */
    private final GamblingService gamblingService;

    /**
     * Method that creates bet.
     *
     * @param requestDTO the request dto {@link CreateBetRequestDTO}
     * @return the response entity {@link ResponseEntity<CreatedBetResponseDTO>}
     */
    @PostMapping("/")
    public ResponseEntity<CreatedBetResponseDTO> createBet(@RequestBody @Valid CreateBetRequestDTO requestDTO) {
        return new ResponseEntity<>(gamblingService.createBet(requestDTO), HttpStatus.CREATED);
    }
}
