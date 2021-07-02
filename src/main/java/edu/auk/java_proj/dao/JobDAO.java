package edu.auk.java_proj.dao;

import java.io.Serializable;

import org.apache.spark.sql.Dataset;

import edu.auk.java_proj.pojo.Job;


public interface JobDAO extends Serializable{
    Dataset<Job> findAll();
    Dataset<Job> find(Job j);
    Boolean addJob(Job j);
    Boolean removeJob(Job j);
}
