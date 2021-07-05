package com.mycompany.clienttester;

import java.io.IOException;
import java.net.MalformedURLException;


public class ClientTester {

    public static void main(String[] args) throws MalformedURLException, IOException {
        DAO test = new DAO();
        
        // Arguments -->  Title of the Graph , Slices Number , Others Or Not , Json Path
        
        
        //TASK 5 --> Drawing pie chart for companies demands
        test.DrawPieChartFromJSON("Top Demanding Companies", 10,true, "http://localhost:8080/jobs/jobs_per_company");
        
        //Task 7 --> Drawing Bar Chart for Top Titles
        test.DrawBarChartFromJSON("Top Needed Titles", 20, false, "http://localhost:8080/jobs/jobs_popularity");
        
        //Task 8 --> Drawing Bar Chart for Top Areas
        test.DrawBarChartFromJSON("Top Areas", 20, false, "http://localhost:8080/jobs/area_popularity");

    }
}
