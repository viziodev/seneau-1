package com.seneau.agentservice.web.dto.response;

import com.seneau.communs.data.dto.role.PrivilegeResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationAccessAgentPrivilegesDto {
    private PrivilegeResponseDto privilege;
}
