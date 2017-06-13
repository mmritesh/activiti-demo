package com.activiti.activitiDemo.activiti.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

/**
 * Created by ritesh on 12/6/17.
 */

@Service
public class StoreResume implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("Resume uploaded.");
    }
}
