package com.seneau.demandeservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AGENT-SERVICE")
public interface AgentRestClient {
 @GetMapping("/api/v1/agent/{id}")
 ResponseEntity<AgentResponse> getAgentById(@PathVariable Long id);
}
