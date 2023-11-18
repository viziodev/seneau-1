package com.seneau.agentservice.web.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseDto {
    private Long id;
    private String code;
    private String name;
    private RoleDto roleHierarchique;
}
