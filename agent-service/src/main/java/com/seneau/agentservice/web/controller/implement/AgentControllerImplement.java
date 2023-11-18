package com.seneau.agentservice.web.controller.implement;

import com.seneau.agentservice.services.AgentService;
import com.seneau.agentservice.web.controller.AgentController;
import com.seneau.agentservice.web.controller.dto.AgentRequest;
import com.seneau.agentservice.web.controller.dto.AgentResponse;
import com.seneau.agentservice.web.controller.dto.FilterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agents")
public class AgentControllerImplement implements AgentController {
    private final AgentService agentService;

    @Override
    public ResponseEntity<AgentResponse> createAgent(AgentRequest agentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agentService.createAgent(agentRequest));
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAllAgent(int page, int size) {
        return ResponseEntity.ok(agentService.getAllAgent(page, size));
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAllAgentFiltered(FilterDto filterDto) {
        return ResponseEntity.ok(agentService.getAllAgentByFilterDto(filterDto));
    }

    @Override
    public ResponseEntity<AgentResponse> getAgentByMatricule(Integer matricule) {
        return ResponseEntity.ok(agentService.getAgentByMatriculeAndActiveTrue(matricule));
    }

    @Override
    public ResponseEntity<List<AgentResponse>> getAllAgentByMatricules(List<Integer> matricules) {
        return ResponseEntity.ok(agentService.getAllAgentByMatriculeIn(matricules));
    }

    @Override
    public ResponseEntity<List<AgentResponse>> getAgentsByMatriculeN1(Integer matriculeManager) {
        return ResponseEntity.ok(agentService.getAllAgentByMatriculeChef(matriculeManager));
    }

    @Override
    public ResponseEntity<List<AgentResponse>> getAgentsByMatriculeDirecteur(Integer matriculeDirecteur) {
        return ResponseEntity.ok(agentService.getAllAgentByMatriculeDirecteur(matriculeDirecteur));
    }

    @Override
    public ResponseEntity<List<AgentResponse>> getAgentsByEtablissement(Long etablissement) {
        return ResponseEntity.ok(agentService.getAllAgentByEtablissement(etablissement));
    }

    @Override
    public ResponseEntity<List<AgentResponse>> getAgentsByDirection(Long direction) {
        return ResponseEntity.ok(agentService.getAllAgentByDirection(direction));
    }

    @Override
    public ResponseEntity<Object> disableAgent(Integer matricule) {
        if (agentService.disableAgent(matricule) == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("response", false));
        return ResponseEntity.ok(Collections.singletonMap("response", true));
    }

    @Override
    public ResponseEntity<List<AgentResponse>> createAll(MultipartFile file, Integer numberOfSheet) throws IOException, ParseException {
        return ResponseEntity.ok(agentService.createAgentFromFileData(file, numberOfSheet));
    }

    @Override
    public ResponseEntity<AgentResponse> updateAgent(Long id, AgentRequest agentRequest) {
        return ResponseEntity.ok(agentService.updateAgent(id, agentRequest));
    }
}
