package com.seneau.agentservice.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FonctionRequestDto {
    private Long id;
    private String name;
    private String code;
}
