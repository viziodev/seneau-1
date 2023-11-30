package com.seneau.agentservice.web.dto;

import com.seneau.agentservice.web.dto.response.ApplicationAccessAgentPrivilegesDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicationAccessDto {
    private Long id;
    private ApplicationDto application;
    private List<ApplicationAccessAgentPrivilegesDto> applicationAccessAgentPrivileges;
}
