package com.activiti.activitiDemo.service;

import com.activiti.activitiDemo.util.ApplicationConstants;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ritesh on 06-06-2017.
 */

@Service
public class ActivityServiceImpl implements ActivitiService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Override
    public ProcessInstance launchProcess(String processName, Map variables) {
        return runtimeService.startProcessInstanceByKey(processName, variables);
    }

    @Override
    public void claimAndCompleteAllTasks(){
        List<Task> tasks = taskService.createTaskQuery().active().list();
        tasks.forEach(task -> {
            System.out.println("Claiming the task by Ritesh: "+ task.getId().toString());
            taskService.claim(task.getId(),"Ritesh");
        });

        tasks = taskService.createTaskQuery().taskAssignee("Ritesh").list();
        tasks.forEach(task -> {
            System.out.println("Completing all tasks for Ritesh");
            taskService.complete(task.getId());
        });

        System.out.println("No of tasks for Ritesh: " + taskService.createTaskQuery().taskAssignee("Ritesh").count());

    }

    @Override
    public void claimAndCompleteTaskById(String taskId){
        //Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        System.out.println("Claiming & completing task: " + taskId);
        //taskService.claim(taskId, "Ritesh");
        taskService.complete(taskId);
        System.out.println("No of tasks for Ritesh: " + taskService.createTaskQuery().taskAssignee("Ritesh").count());
    }

    @Override
    public void claimAndCompleteByCandidateId(Long candidateId, Map<String, Object> params){
        Task task = taskService.createTaskQuery().processVariableValueEquals(ApplicationConstants.CANDIDATE_ID, candidateId).singleResult();
        taskService.complete(task.getId(), params);
        System.out.println("User task completed.");
        //taskService.
    }


}
