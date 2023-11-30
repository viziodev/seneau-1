package com.seneau.agentservice.service.parametrage.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seneau.agentservice.data.model.ApplicationAccessFonction;
import com.seneau.agentservice.data.model.ApplicationAccessFonctionPrivilege;
import com.seneau.agentservice.data.model.Fonction;
import com.seneau.agentservice.data.repository.ApplicationAccessFonctionPrivilegeRepository;
import com.seneau.agentservice.data.repository.ApplicationAccessFonctionRepository;
import com.seneau.agentservice.data.repository.FonctionRepository;
import com.seneau.agentservice.service.parametrage.FonctionService;
import com.seneau.agentservice.web.dto.request.parametrage.FonctionRequestDto;
import com.seneau.agentservice.web.dto.response.FonctionResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FonctionServiceImplement implements FonctionService {
    private final ApplicationAccessFonctionRepository applicationAccessFonctionRepository;
    private final FonctionRepository fonctionRepository;
    private final ApplicationAccessFonctionPrivilegeRepository applicationAccessFonctionPrivilegeRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    @Override
    public FonctionResponseDto createFonctionWithPrivilege(FonctionRequestDto fonctionRequestDto) {
        Fonction fonction = objectMapper.convertValue(fonctionRequestDto, Fonction.class);
        fonctionRepository.save(fonction);
        if (fonctionRequestDto.getApplicationAccessFonctions() != null) {
            List<ApplicationAccessFonction> applicationAccessFonctions = fonctionRequestDto.getApplicationAccessFonctions()
                    .stream().map(af -> objectMapper.convertValue(af, ApplicationAccessFonction.class)).toList();
            applicationAccessFonctions.forEach(af -> af.setFonction(fonction));
            applicationAccessFonctionRepository.saveAll(applicationAccessFonctions);

            for (ApplicationAccessFonction accessFonction : applicationAccessFonctions) {
                List<ApplicationAccessFonctionPrivilege> accessFonctionPrivileges = accessFonction.getApplicationAccessFonctionPrivileges();
                accessFonctionPrivileges.forEach(af -> af.setApplicationAccessFonction(accessFonction));
                applicationAccessFonctionPrivilegeRepository.saveAll(accessFonctionPrivileges);
            }
        }
        return objectMapper.convertValue(fonction, FonctionResponseDto.class);
    }
}
