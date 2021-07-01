package edu.auk.java_proj.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@Configuration
@PropertySource("classpath:application.properties")
public class SparkConfig {
 
    @Value("${spark.app.name:spark-sprint-boot}")
    private String appName;

    @Value("${spark.master.uri:local[2]}")
    private String masterUri;

    @Bean
    public SparkConf sparkConf() {
        SparkConf sparkConf = new SparkConf()
                .setAppName(appName)
                .setMaster(masterUri);

        return sparkConf;
    }

    @Bean
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(sparkConf());
    }
    // @Bean
    // public SparkSession sparkSession() {
    //     return SparkSession
    //             .builder()
    //             .sparkContext(javaSparkContext().sc())
    //             .appName("Integrating Spring-boot with Apache Spark")
    //             .getOrCreate();
    // }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }   


}
