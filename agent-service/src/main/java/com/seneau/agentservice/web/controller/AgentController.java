package com.seneau.agentservice.web.controller;

import com.seneau.agentservice.web.controller.dto.AgentRequest;
import com.seneau.agentservice.web.controller.dto.AgentResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface AgentController {
    @PostMapping
    ResponseEntity<AgentResponse> createAgent(@Valid @RequestBody AgentRequest agentRequest);
    @PostMapping("/create_all")
    ResponseEntity<List<AgentResponse>> createAll(@RequestParam MultipartFile file, @RequestParam Integer numberOfSheet) throws IOException, ParseException;
    @GetMapping
    ResponseEntity<Map<String, Object>> getAllAgent(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size);
    @GetMapping("/{matricule}")
    ResponseEntity<AgentResponse> getAgentByMatricule(@PathVariable Integer matricule);
    @PostMapping("/listDtoAgentmatricules/all")
    ResponseEntity<List<AgentResponse>> getAllAgentByMatricules(@RequestBody List<Integer> matricules);
    @GetMapping("/n1/{matriculeManager}")
    ResponseEntity<List<AgentResponse>> getAgentsByMatriculeN1(@PathVariable Integer matriculeManager);
    @GetMapping("/directeur/{matriculeDirecteur}")
    ResponseEntity<List<AgentResponse>> getAgentsByMatriculeDirecteur(@PathVariable Integer matriculeDirecteur);
    @GetMapping("/etablissement/{etablissement}")
    ResponseEntity<List<AgentResponse>> getAgentsByEtablissement(@PathVariable Long etablissement);
    @GetMapping("/directions/{direction}")
    ResponseEntity<List<AgentResponse>> getAgentsByDirection(@PathVariable Long direction);
    @DeleteMapping("/{matricule}/disable")
    ResponseEntity<Object> disableAgent(@PathVariable Integer matricule);

}
