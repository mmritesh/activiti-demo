package com.activiti.activitiDemo.controller;

import com.activiti.activitiDemo.model.Candidate;
import com.activiti.activitiDemo.repository.CandidateRepo;
import com.activiti.activitiDemo.service.ActivitiService;
import com.activiti.activitiDemo.util.ApplicationConstants;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    CandidateRepo candidateRepo;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String home() throws Exception {
        Map<String, Object> variables = new HashMap<>();
        ProcessInstance processInstance =  activitiService.launchProcess(ApplicationConstants.DEMO_TEST_PROCESS, variables);

        if(Objects.isNull(processInstance))
            throw new Exception();

        System.out.println("Execution started....");

        return "Execution Started. Id = " + processInstance.getId();
    }

    @RequestMapping(value = "/hire", method = RequestMethod.POST)
    public String hire(@RequestBody Candidate candidate) throws Exception {
        try{
            candidate = candidateRepo.save(candidate);

            Map<String, Object> variables = new HashMap<>();
            variables.put(ApplicationConstants.CANDIDATE_ID, candidate.getId());
            ProcessInstance processInstance =  activitiService.launchProcess(ApplicationConstants.HIRING_PROCESS, variables);

            if(Objects.isNull(processInstance))
                throw new Exception();

            System.out.println("Hiring process started for: " + candidate.getName());

            return "Hiring process started for = " + candidate.getId();
        }catch (MySQLIntegrityConstraintViolationException ex){
            return "Duplicate email Id";
        }catch (Exception e){
            LOG.error("Exception: ",e);
            return "Unknown exception";
        }

    }

    @RequestMapping(value = "telephone-interview/{candidateId}/{isPassed}", method = RequestMethod.POST)
    public String telephoneInterview(@PathVariable("candidateId") Long candidateId,
                                     @PathVariable("isPassed") Boolean isPassed){
        Map<String, Object> params = new HashMap<>();
        params.put(ApplicationConstants.TELEPHONE_INTERVIEW_PASSED, isPassed);
        activitiService.claimAndCompleteByCandidateId(candidateId, params);
        return "Telephonic round completed";
    }

    @RequestMapping("/complete-user-task/{taskId}")
    public String completeUserTask(@PathVariable("taskId") String taskId){
        activitiService.claimAndCompleteTaskById(taskId);
        return "complete";
    }

    @ExceptionHandler(value = MySQLIntegrityConstraintViolationException.class)
    public String mysqlIntegrityException(MySQLIntegrityConstraintViolationException ex){
        return "Duplicate email Id";
    }
}
