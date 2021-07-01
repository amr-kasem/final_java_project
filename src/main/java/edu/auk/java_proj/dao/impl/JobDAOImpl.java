package edu.auk.java_proj.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.auk.java_proj.dao.JobDAO;
import edu.auk.java_proj.pojo.Job;
// import scala.Function8;
@Repository
public class JobDAOImpl implements JobDAO {
    // @Autowired
    private JavaRDD<String> data;
    @Autowired
    private  JavaSparkContext javaSparkContext;

    @PostConstruct
    private void init(){
        String fileName = "Wuzzuf_Jobs.csv";
        String f = getClass().getClassLoader().getResource(fileName).getFile();
        data = javaSparkContext.textFile(f);

        System.out.println(data.first());
    }


    @Override
    public List<Job> getJobs() {
        List<Job> jobs = new ArrayList<Job>();
        data.foreach(l -> {
            String[] vStrings = l.split(",");
            // jobFactory.apply(vStrings[0], vStrings[1], vStrings[2], vStrings[3],
            // vStrings[4], vStrings[5], vStrings[6],vStrings[7]);
            Job j = new Job(vStrings[0], vStrings[1], vStrings[2], vStrings[3], vStrings[4], vStrings[5], vStrings[6],
            vStrings[7]);
            jobs.add(j);
            
            System.out.println(jobs.size());
        });
        

        // jobs.stream().forEach(System.out::println);
        System.out.println(jobs.size());
        return null;
    }

    @Override
    public List<Job> findJobs(Job j) {
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
