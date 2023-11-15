package com.seneau.agentservice.services;

import com.seneau.agentservice.data.model.Agent;
import com.seneau.agentservice.web.dto.AgentRequest;
import com.seneau.agentservice.web.dto.AgentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AgentService {
    AgentResponse createAgent(AgentRequest agentRequest);
    List<AgentResponse> getAllAgent();
    Page<Agent> getAllAgent(Pageable pageable);
}
