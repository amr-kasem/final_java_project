package edu.auk.java_proj.rest;

import edu.auk.java_proj.dao.JobDAO;
import edu.auk.java_proj.dao.impl.JobDAOImpl;
import edu.auk.java_proj.pojo.Job;
import scala.Function1;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import static org.apache.spark.sql.functions.*;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobsController {
	@Autowired
	ApplicationContext applicationContext;
	private JobDAO jobDAO;

	@PostConstruct
	private void init() {
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
	public ResponseEntity<List<JsonObject>> summary() {
		Dataset<Row> s = jobDAO.findAll().summary();
		return ResponseEntity.ok(rowToJson(s));
	}

	@GetMapping(path = "/structure", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<StructType> structure() {
		return ResponseEntity.ok(jobDAO.findAll().schema());
	}

	@GetMapping(path = "/describe", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<JsonObject>> describe() {
		Dataset<Row> s = jobDAO.findAll().describe();

		return ResponseEntity.ok(rowToJson(s));
	}

	@GetMapping(path = "/clean", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<Job>> clean() {
		return ResponseEntity.ok(jobDAO.findAll().dropDuplicates().distinct().collectAsList());
	}

	@GetMapping(path = "/jobs_per_company", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<JsonObject>> jobsPerCompany() {
		Dataset<Row> s = jobDAO.findAll().groupBy("company").agg(count("company").alias("count"))
				.sort(col("count").desc());
		return ResponseEntity.ok(rowToJson(s));
	}
	@GetMapping(path = "/jobs_popularity", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<JsonObject>> jobsPopularity() {
		Dataset<Row> s = jobDAO.findAll().groupBy("title").agg(count("title").alias("count"))
				.sort(col("count").desc());
		return ResponseEntity.ok(rowToJson(s));
	}
	@GetMapping(path = "/area_popularity", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<JsonObject>> areaPopularity() {
		Dataset<Row> s = jobDAO.findAll().groupBy("location").agg(count("location").alias("count"))
				.sort(col("count").desc());
		return ResponseEntity.ok(rowToJson(s));
	}
	@GetMapping(path = "/skills", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<JsonObject>> skills() {
		Dataset<Row> s = jobDAO.findAll().selectExpr("explode(split(Skills,',')) as skill").withColumn("skill", trim(lower(col("skill")))).groupBy("skill").count().alias("count").sort(col("count").desc());
		return ResponseEntity.ok(rowToJson(s));
	}
	private Gson gson = new Gson();
	private List<JsonObject> rowToJson(Dataset<Row> s){
		return s.toJSON().collectAsList().stream().map(m->gson.fromJson(m,JsonObject.class)).collect(Collectors.toList());
	}
}
