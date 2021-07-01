package edu.auk.java_proj.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.spark.sql.Dataset;

import edu.auk.java_proj.pojo.Job;


public interface JobDAO extends Serializable{
    Dataset<Job> getJobs();
    Dataset<Job> findJobs(Job j);
    Boolean addJob(Job j);
    Boolean removeJob(Job j);
}
