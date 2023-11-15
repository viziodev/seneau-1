package com.seneau.agentservice.web.controller;

import com.seneau.agentservice.web.dto.AgentRequest;
import com.seneau.agentservice.web.dto.AgentResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public interface AgentController {
    @PostMapping
    ResponseEntity<AgentResponse> createAgent(@Valid @RequestBody AgentRequest agentRequest);
    @GetMapping
    ResponseEntity<Map<String, AgentResponse>> getAllAgent(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size);

    @PostMapping("/upload")
    ResponseEntity<Object> upload(@RequestParam MultipartFile file, @RequestParam Integer numberOfSheet) throws IOException, ParseException;
}
