package com.seneau.agentservice.web.dto.request.parametrage;

import com.seneau.agentservice.web.dto.request.role.ApplicationAccessFonctionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FonctionRequestDto {
    private Long id;
    private String name;
    private String code;
    List<ApplicationAccessFonctionDto> applicationAccessFonctions = new ArrayList<>();
}
