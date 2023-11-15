package com.seneau.agentservice.web.controller.implement;

import com.seneau.agentservice.services.AgentService;
import com.seneau.agentservice.services.utils.UploadService;
import com.seneau.agentservice.web.controller.AgentController;
import com.seneau.agentservice.web.dto.AgentRequest;
import com.seneau.agentservice.web.dto.AgentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agent")
public class AgentControllerImplement implements AgentController {
    private final AgentService agentService;
    private final UploadService uploadService;

    @Override
    public ResponseEntity<AgentResponse> createAgent(AgentRequest agentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agentService.createAgent(agentRequest));
    }

    @Override
    public ResponseEntity<Map<String, AgentResponse>> getAllAgent(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> upload(MultipartFile file, Integer numberOfSheet) throws IOException, ParseException {
        return ResponseEntity.ok(Collections.singletonMap("results", uploadService.getAgentsFromExcelFile(file, numberOfSheet)));
    }
}
