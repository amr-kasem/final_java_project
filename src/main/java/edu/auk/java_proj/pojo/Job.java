package edu.auk.java_proj.pojo;
// import java.io.Serializable;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope(value = "prototype")
public class Job implements Serializable{
// public class Job {
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    public String title;
    public String company;
    public String location;
    public String type;
    public String level;
    public String years_exp;
    public String country;
    public String skills;

    // public Job(){};
    public Job(String title, String company, String location, String type, String level, String years_exp,
            String country, String skills) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.type = type;
        this.level = level;
        this.years_exp = years_exp;
        this.country = country;
        this.skills = skills;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCompany() {
        return this.company;
    }

    public String getLocation() {
        return this.location;
    }

    public String getType() {
        return this.type;
    }

    public String getLevel() {
        return this.level;
    }

    public String getYears_exp() {
        return this.years_exp;
    }

    public String getCountry() {
        return this.country;
    }

    public String getSkills() {
        return this.skills;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setYears_exp(String years_exp) {
        this.years_exp = years_exp;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return title;
    }
}