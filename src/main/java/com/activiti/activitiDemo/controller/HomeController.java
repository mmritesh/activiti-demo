package com.activiti.activitiDemo.controller;

import com.activiti.activitiDemo.service.ActivitiService;
import com.activiti.activitiDemo.util.ApplicationConstants;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Ritesh on 06-06-2017.
 */

@RestController
public class HomeController {

    @Autowired
    ActivitiService activitiService;

    @RequestMapping("/")
    public String home() throws Exception {
        Map<String, Object> variables = new HashMap<>();
        ProcessInstance processInstance =  activitiService.launchProcess(ApplicationConstants.DEMO_TEST_PROCESS, variables);

        if(Objects.isNull(processInstance))
            throw new Exception();

        System.out.println("Execution started....");

        return "Execution Started";
    }
}
