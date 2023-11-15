package com.seneau.agentservice.web.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleDto {
    private String name;
    private List<RoleDto> rolesHierarchique;
}
