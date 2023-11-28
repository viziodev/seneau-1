package com.seneau.agentservice.web.controller.application;

import com.seneau.agentservice.web.dto.request.application.ApplicationRequest;
import com.seneau.agentservice.web.dto.response.ApplicationResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ApplicationController {
    @PostMapping
    ResponseEntity<ApplicationResponse> create(@Valid @RequestBody ApplicationRequest applicationRequest);
}
