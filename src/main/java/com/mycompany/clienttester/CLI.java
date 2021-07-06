package com.mycompany.clienttester;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.function.Function;

public class CLI {
    private DAO dao = new DAO();
    private abstract class Task {
        private String message = "";

        public abstract boolean action() throws MalformedURLException, IOException;

        public Task(String msg) {
            message = msg;
        };

        public String getMessage() {
            return message;
        }
    }

    private Task[] tasks = new Task[] { new Task("Show First 10") {
        @Override
        public boolean action() {
            
            return false;
        }
    }, new Task("Show Structure") {
        @Override
        public boolean action() {
            return false;
        }
    }, new Task("Show Summary") {
        @Override
        public boolean action() {
            return false;
        }
    }, new Task("Show Jobs Per Company") {
        @Override
        public boolean action() {
            return false;
        }
    }, new Task("Draw Jobs Per Company Pie Chart") {
        @Override
        public boolean action() throws MalformedURLException, IOException {
            dao.DrawPieChartFromJSON("Top Demanding Companies", 10,true, "http://localhost:8080/jobs/jobs_per_company");
            return false;
        }
    }, new Task("Show Jobs Counts") {
        @Override
        public boolean action() {
            return false;
        }
    }, new Task("Draw Jobs Histo on Bar Chart") {
        @Override
        public boolean action() throws MalformedURLException, IOException {
            dao.DrawBarChartFromJSON("Top Needed Titles", 20, false, "http://localhost:8080/jobs/jobs_popularity");
            return false;
        }
    }, new Task("Show Area Counts") {
        @Override
        public boolean action() {
            return false;
        }
    }, new Task("Draw Area Histo on Bar Chart") {
        @Override
        public boolean action() throws MalformedURLException, IOException {
            dao.DrawBarChartFromJSON("Top Areas", 20, false, "http://localhost:8080/jobs/area_popularity");
            return false;
        }
    }, new Task("Show Area Counts") {
        @Override
        public boolean action() {
            return false;
        }
    }, new Task("Show Skills Counts") {
        @Override
        public boolean action() {
            return false;
        }
    }, new Task("Show Jobs with Factorized YearExp") {
        @Override
        public boolean action() {
            return false;
        }
    }, new Task("Exit") {
        @Override
        public boolean action() {
            return true;
        }
    } };
    private Scanner input = new Scanner(System.in);

    public void run() throws MalformedURLException, IOException {
        while (true) {
            System.out.println("########################################");
            for (int i = 0; i < tasks.length; i++)
                System.out.println(Integer.toString(i) + ") " + tasks[i].getMessage());
            System.out.println("Input Command Number:");
            int input_value = input.nextInt();
            if(tasks[input_value].action())
                break;
        }
        System.out.println("########################################");
    }
}
