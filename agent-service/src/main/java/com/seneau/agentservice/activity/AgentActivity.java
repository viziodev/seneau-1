package com.seneau.agentservice.activity;



import com.seneau.agentservice.web.dto.AgentResponse;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;


@ActivityInterface
public interface
AgentActivity {
    @ActivityMethod
    AgentResponse getAgentByEmail(String email);
}
