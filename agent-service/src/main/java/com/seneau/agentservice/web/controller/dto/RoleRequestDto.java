package com.seneau.agentservice.web.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleRequestDto {
    private String name;
    private String code;
    private List<Long> roleHierarchique;
    private List<Long> roleFonctionnel;
}
