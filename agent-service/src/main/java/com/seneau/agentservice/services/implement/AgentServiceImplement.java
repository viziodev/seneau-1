package com.seneau.agentservice.services.implement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seneau.agentservice.data.model.Agent;
import com.seneau.agentservice.data.repository.AgentRepository;
import com.seneau.agentservice.services.AgentService;
import com.seneau.agentservice.web.dto.AgentRequest;
import com.seneau.agentservice.web.dto.AgentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentServiceImplement implements AgentService {
    private final AgentRepository agentRepository;
    private final ObjectMapper objectMapper;
    @Override
    public AgentResponse createAgent(AgentRequest agentRequest) {
        Agent agent = objectMapper.convertValue(agentRequest, Agent.class);
        return objectMapper.convertValue(agentRepository.save(agent), AgentResponse.class);
    }

    @Override
    public List<AgentResponse> getAllAgent() {
        List<Agent> agents = agentRepository.findAllByActiveTrue();
        return agents
                .stream()
                .map(agent -> objectMapper.convertValue(agent, AgentResponse.class))
                .toList();
    }

    @Override
    public Page<Agent> getAllAgent(Pageable pageable) {
        return agentRepository.findAllByActiveTrue(pageable);
    }
}
