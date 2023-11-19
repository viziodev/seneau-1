package com.seneau.communs.activity.application;

import com.seneau.communs.activity.agent.dto.ApplicationDto;
import io.temporal.activity.ActivityInterface;

import java.util.List;

@ActivityInterface
public interface ApplicationActivity {
    List<ApplicationDto> getApplicationsByUserConnect(List<String> applicationNames);
}
