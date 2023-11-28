package com.seneau.agentservice.web.dto;

import com.seneau.communs.data.dto.role.PrivilegeResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicationAccessDto {
    private Long id;
    private ApplicationDto application;
    private List<PrivilegeResponseDto> privileges;
}
