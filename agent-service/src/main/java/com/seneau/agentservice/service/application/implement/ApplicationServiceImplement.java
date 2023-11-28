package com.seneau.agentservice.service.application.implement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seneau.agentservice.data.model.Application;
import com.seneau.agentservice.data.repository.ApplicationRepository;
import com.seneau.agentservice.service.application.ApplicationService;
import com.seneau.agentservice.web.dto.request.application.ApplicationRequest;
import com.seneau.agentservice.web.dto.response.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImplement implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ObjectMapper objectMapper;
    @Override
    public ApplicationResponse createApplication(ApplicationRequest applicationRequest) {
        Application application = objectMapper.convertValue(applicationRequest, Application.class);
        return objectMapper.convertValue(applicationRepository.save(application), ApplicationResponse.class);
    }
}
