package com.seneau.agentservice.web.controller.implement;

import com.seneau.agentservice.services.AgentService;
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
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agent")
public class AgentControllerImplement implements AgentController {
    private final AgentService agentService;

    @Override
    public ResponseEntity<AgentResponse> createAgent(AgentRequest agentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agentService.createAgent(agentRequest));
    }

    @Override
    public ResponseEntity<Map<String, AgentResponse>> getAllAgent(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<List<AgentResponse>> upload(MultipartFile file, Integer numberOfSheet) throws IOException, ParseException {
        return ResponseEntity.ok(agentService.createAgentFromFileData(file, numberOfSheet));
    }
}
