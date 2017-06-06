package com.activiti.activitiDemo;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SpringBootApplication
public class ActivitiDemoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ActivitiDemoApplication.class, args);
	}

}
