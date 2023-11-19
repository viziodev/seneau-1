package com.seneau.communs.activity.agent;



import com.seneau.communs.activity.agent.dto.AgentResponse;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;


@ActivityInterface
public interface
AgentActivity {
    @ActivityMethod
    AgentResponse getAgentByEmail(String email);
}
