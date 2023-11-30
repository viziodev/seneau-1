package com.seneau.agentservice.web.dto;

import com.seneau.agentservice.web.dto.request.role.ApplicationAccessFonctionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FonctionDto {
    private Long id;
    private boolean active = true;
    private String name;
    private String code;
    List<ApplicationAccessFonctionDto> applicationAccessFonctions;
}
