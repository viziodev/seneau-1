package com.seneau.agentservice.web.dto.response;

import com.seneau.agentservice.web.dto.request.role.ApplicationAccessFonctionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FonctionResponseDto {
    private Long id;
    private String name;
    private String code;
    private boolean active;
    List<ApplicationAccessFonctionDto> applicationAccessFonctions;
}
