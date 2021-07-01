package edu.auk.java_proj;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JavaProjApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(JavaProjApplication.class, args);
		// SparkConf conf = new SparkConf().setAppName("wordCounts").setMaster("local[1]");
		// JavaSparkContext sparkContext = new JavaSparkContext(conf);
		// String f = "/home/auk/disk/CoursesMaterial/Docs/AI/ITI/07_Java/Projects/final_proj/java_proj/src/main/resources/Wuzzuf_Jobs.csv";
		// SparkSession ss = new SparkSession.Builder().sparkContext(sparkContext.sc())
		// 		.appName("Integrating Spring-boot with Apache Spark").getOrCreate();
		// Dataset<Row> ds = ss.read().csv(f);
		// ds.foreach(x -> {
		// 	System.out.println(x.getString(0));
		// });
		// ss.close();
		// String[] beanNames = applicationContext.getBeanDefinitionNames();
		// Arrays.sort(beanNames);
		// for (String beanName : beanNames)
		// System.out.println(beanName);
	}

};
