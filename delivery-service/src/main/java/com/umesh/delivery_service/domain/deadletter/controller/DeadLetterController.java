package com.umesh.delivery_service.domain.deadletter.controller;

import com.umesh.delivery_service.domain.deadletter.dto.response.DeadLetterStatisticsResponse;
import com.umesh.delivery_service.domain.deadletter.service.DeadLetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dead-letters")
@RequiredArgsConstructor
public class DeadLetterController {

    private final DeadLetterService deadLetterService;

    @GetMapping("/statistics")
    public ResponseEntity<DeadLetterStatisticsResponse> getStatistics() {

        return ResponseEntity.ok(
                deadLetterService.getStatistics());

    }

}