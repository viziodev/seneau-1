package com.seneau.agentservice.web.controller.dto;

import lombok.Data;

@Data
public class ApplicationAccessDto {
    private ApplicationDto application;
    private RoleDto role;
}
