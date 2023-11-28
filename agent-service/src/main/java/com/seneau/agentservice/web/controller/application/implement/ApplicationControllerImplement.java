package com.seneau.agentservice.web.controller.application.implement;

import com.seneau.agentservice.service.application.ApplicationService;
import com.seneau.agentservice.web.controller.application.ApplicationController;
import com.seneau.agentservice.web.dto.request.application.ApplicationRequest;
import com.seneau.agentservice.web.dto.response.ApplicationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationControllerImplement implements ApplicationController {
    private final ApplicationService applicationService;

    @Override
    public ResponseEntity<ApplicationResponse> create(@Valid @RequestBody ApplicationRequest applicationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationService.createApplication(applicationRequest));
    }
}

