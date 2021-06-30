package edu.auk.java_proj.dao;

import java.util.List;

import edu.auk.java_proj.pojo.Job;

public interface JobDAO {
    List<Job> getJobs();
    List<Job> findJobs(Job j);
    Boolean addJob(Job j);
    Boolean removeJob(Job j);
}
