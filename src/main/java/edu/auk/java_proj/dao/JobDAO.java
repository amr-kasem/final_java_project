package edu.auk.java_proj.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import edu.auk.java_proj.pojo.Job;
// @Repository
public interface JobDAO extends Serializable{
    List<Job> getJobs();
    List<Job> findJobs(Job j);
    Boolean addJob(Job j);
    Boolean removeJob(Job j);
}
