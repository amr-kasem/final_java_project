package edu.auk.java_proj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import edu.auk.java_proj.dao.JobDAO;
import edu.auk.java_proj.dao.impl.JobDAOImpl;
import edu.auk.java_proj.pojo.Job;
// import edu.auk.java_proj.repository.JobRepo;
import scala.Function8;

@Configuration
public class JobsConfig {
    // @Bean
    // public JobRepo jobRepository() {
    //     return new JobRepo();
    // }

    // @Bean
    // public JobDAO jobDAO() {
    //     return new JobDAOImpl();
    // }

    // @Bean
    // @Scope(value = "prototype")
    // public Job job(String title, String company, String location, String type, String level, String years_exp,
    //         String country, String skills) {
    //     return new Job(title, company, location, type, level, years_exp, country, skills);
    // }
    // @Bean
    // public Function8<String,String,String,String,String,String,String,String,Job> jobFactory(){
    //     return (title, company, location, type, level, years_exp, country, skills) -> new Job(title, company, location, type, level, years_exp, country, skills);
    // }
}