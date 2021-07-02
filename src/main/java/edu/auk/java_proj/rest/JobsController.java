package edu.auk.java_proj.rest;

import edu.auk.java_proj.dao.JobDAO;
import edu.auk.java_proj.dao.impl.JobDAOImpl;
import edu.auk.java_proj.pojo.Greetings;
import edu.auk.java_proj.pojo.Job;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import static org.apache.spark.sql.functions.*;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.status.api.v1.JobData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobsController {
	@Autowired
	ApplicationContext applicationContext;
	private JobDAO jobDAO;
	@PostConstruct
	private void init(){
		jobDAO = applicationContext.getBean(JobDAOImpl.class);
		System.out.println(jobDAO);
	}	
	@GetMapping(path = "/title/{title}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Job> findTitle(@PathVariable String title) {
		Job j = new Job();
		j.setTitle(title);
		return jobDAO.find(j).collectAsList();
	}
	@GetMapping(path = "/country/{country}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Job> findCountry(@PathVariable String country) {
		Job j = new Job();
		j.setCountry(country);
		return jobDAO.find(j).collectAsList();
	}
	@GetMapping(path = "/", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<Job>> jobs() {
	
		return ResponseEntity.ok(jobDAO.findAll().collectAsList());
	}
	@GetMapping(path = "/summary", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<String>> summary() {
		jobDAO.findAll().summary().show();
		return ResponseEntity.ok(jobDAO.findAll().summary().toJSON().collectAsList());
	}
	@GetMapping(path = "/structure", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<StructType> structure() {
		return ResponseEntity.ok(jobDAO.findAll().schema());
	}
	@GetMapping(path = "/describe", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<String>> describe() {
		jobDAO.findAll().describe().show();
		return ResponseEntity.ok(jobDAO.findAll().describe().toJSON().collectAsList());
	}
	@GetMapping(path = "/clean", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<Job>> clean() {
		return ResponseEntity.ok(jobDAO.findAll().dropDuplicates().distinct().collectAsList());
	}
	@GetMapping(path = "/jobs_per_company", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<String>jobsPerCompany () {
		Dataset s = jobDAO.findAll().groupBy("company").agg(count("company").alias("count")).sort(col("count").desc());
		System.out.println();
		s.show();
		return ResponseEntity.ok(s.first().toString());
	}
}
