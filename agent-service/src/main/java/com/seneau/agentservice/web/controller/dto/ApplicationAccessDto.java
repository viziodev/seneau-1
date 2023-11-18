package com.seneau.agentservice.web.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationAccessDto {
    private Long id;
    private ApplicationDto application;
    private RoleDto role;
}
