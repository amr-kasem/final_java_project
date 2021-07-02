package edu.auk.java_proj.dao.impl;

import javax.annotation.PostConstruct;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;

import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.auk.java_proj.dao.JobDAO;
import edu.auk.java_proj.pojo.Job;
import scala.reflect.internal.Trees.Return;

@Repository
public class JobDAOImpl implements JobDAO {
    private static Dataset<Job> jobs;
    @Autowired
    private SparkSession sparkSession;

    @PostConstruct
    private void init() {
        String fileName = "Wuzzuf_Jobs.csv";
        String f = getClass().getClassLoader().getResource(fileName).getFile();

        jobs = sparkSession.read().option("header", "true").csv(f).as(Encoders.bean(Job.class));
    }

    @Override
    public Dataset<Job> findAll() {
        return jobs;
    }

    @Override
    public Dataset<Job> find(Job j) {
        // TODO Auto-generated method stub
        Dataset<Job> r = sparkSession.emptyDataset(Encoders.bean(Job.class));
        if (j.title != null)
            r = jobs.filter("title ='" + j.title + "'");
        if (j.country != null)
            r = jobs.filter("country ='" + j.country + "'");
        return r;
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
