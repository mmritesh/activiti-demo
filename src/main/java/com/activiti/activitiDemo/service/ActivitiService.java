package com.activiti.activitiDemo.service;

import org.activiti.engine.runtime.ProcessInstance;

import java.util.Map;

/**
 * Created by Ritesh on 06-06-2017.
 */
public interface ActivitiService {
    ProcessInstance launchProcess(String processName, Map variables);
}
