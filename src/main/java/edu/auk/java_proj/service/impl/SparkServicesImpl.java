package edu.auk.java_proj.service.impl;



import org.springframework.stereotype.Service;

import edu.auk.java_proj.service.SparkServices;
@Service
public class SparkServicesImpl implements SparkServices{
    public String test(){
        return "hello";
    }
}
