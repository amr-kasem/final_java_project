package edu.auk.java_proj.rest;

import edu.auk.java_proj.dao.JobDAO;
import edu.auk.java_proj.dao.impl.JobDAOImpl;
import edu.auk.java_proj.pojo.Greetings;

import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobsController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	@Autowired
	ApplicationContext applicationContext;
	private JobDAO jobDAO;
	@PostConstruct
	private void init(){
		jobDAO = applicationContext.getBean(JobDAOImpl.class);
		System.out.println(jobDAO);
	}	
	@GetMapping("/greeting")
	public Greetings greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greetings(counter.incrementAndGet(), String.format(template, name));
	}
	@GetMapping("/jobs")
	public String jobs() {
		// System.out.println(jobDAO.getJobs().first().getTitle());
		// jobDAO.getJobs().;
		// jobDAO.getJobs().;
		return "done";
	}
}
