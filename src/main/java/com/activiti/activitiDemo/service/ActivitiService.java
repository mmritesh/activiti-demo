package com.activiti.activitiDemo.service;

import org.activiti.engine.runtime.ProcessInstance;

import java.util.Map;

/**
 * Created by Ritesh on 06-06-2017.
 */
public interface ActivitiService {
    ProcessInstance launchProcess(String processName, Map variables);
    void claimAndCompleteAllTasks();
    void claimAndCompleteTaskById(String taskId);
    void claimAndCompleteByCandidateId(Long candidateId, Map<String, Object> params);
}
