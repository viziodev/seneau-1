package com.seneau.communs.activity.agent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectionDto implements Serializable {
    private Long id;
    private boolean active = true;
    private String name;
    private String code;
}
