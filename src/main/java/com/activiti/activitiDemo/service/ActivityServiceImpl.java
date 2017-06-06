package com.activiti.activitiDemo.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Ritesh on 06-06-2017.
 */

@Service
public class ActivityServiceImpl implements ActivitiService {

    @Autowired
    RuntimeService runtimeService;

    @Override
    public ProcessInstance launchProcess(String processName, Map variables) {
        return runtimeService.startProcessInstanceByKey(processName, variables);
    }
}
