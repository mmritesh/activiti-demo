package com.activiti.activitiDemo.repository;

import com.activiti.activitiDemo.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ritesh on 12/6/17.
 */

@Repository
public interface CandidateRepo extends CrudRepository<Candidate, Long> {
}
