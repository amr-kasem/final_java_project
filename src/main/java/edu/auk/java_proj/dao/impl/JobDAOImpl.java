package edu.auk.java_proj.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;

import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.auk.java_proj.dao.JobDAO;
import edu.auk.java_proj.pojo.Job;

@Repository
public class JobDAOImpl implements JobDAO {
    private static Dataset<Job> jobs;
    @Autowired
    private SparkSession sparkSession;

    @PostConstruct
    private void init() {
        String fileName = "Wuzzuf_Jobs.csv";
        String f = getClass().getClassLoader().getResource(fileName).getFile();
        
        jobs =sparkSession.read().option("header","true").csv(f).as(Encoders.bean(Job.class)); 
        jobs.foreach(x->{System.out.println(x.title);});
    }

    @Override
    public Dataset<Job> getJobs() {
        System.out.println(jobs.count());

        return jobs;
    }

    @Override
    public Dataset<Job> findJobs(Job j) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean addJob(Job j) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean removeJob(Job j) {
        // TODO Auto-generated method stub
        return null;
    }

}
