package com.seneau.agentservice.web.dto.request.role;

import com.seneau.agentservice.web.dto.ApplicationDto;
import com.seneau.communs.data.dto.role.PrivilegeResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApplicationAccessFonctionDto {
    private ApplicationDto application;
    private List<ApplicationAccessFonctionPrivilegeDto> applicationAccessFonctionPrivileges = new ArrayList<>();
}
