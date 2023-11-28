package com.seneau.communs.data.dto.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegeResponseDto {
    private Long id;
    private String name;
    private String code;
}
