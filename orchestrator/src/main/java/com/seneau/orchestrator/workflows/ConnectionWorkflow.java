package com.seneau.orchestrator.workflows;


import com.seneau.communs.activity.agent.dto.AgentResponse;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.List;

@WorkflowInterface
public interface ConnectionWorkflow {
      @WorkflowMethod
      AgentResponse getAgentByEmail(String email,List<String> applicationNames);


}
