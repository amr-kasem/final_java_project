package com.mycompany.clienttester;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

public class DAO {

    public void DrawPieChartFromJSON(String title, int Slice_No, boolean Others, String JsonPath) throws MalformedURLException, IOException {
        // Connect to the URL using java's native library
        URL url = new URL(JsonPath);
        URLConnection request = url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonArray rootobj = root.getAsJsonArray();

        List<String> Elements = rootobj.get(0).getAsJsonObject().keySet().stream().collect(Collectors.toList());

        List<String> ElementsNames = new ArrayList<>();
        List<Integer> Count = new ArrayList<>();

        for (int i = 0; i < Slice_No; i++) {
            ElementsNames.add(rootobj.get(i).getAsJsonObject().get(Elements.get(0)).getAsString());
            Count.add(rootobj.get(i).getAsJsonObject().get(Elements.get(1)).getAsInt());
        }

        PieChart pieChart = new PieChartBuilder().width(1024*2).height(768*2).title(title).build();
        //adding sets to chart
        for (int i = 0; i < Slice_No; i++) {
            pieChart.addSeries(ElementsNames.get(i), Count.get(i));
        }

        //Adding Others
        if (Others) {

            int Others_Count = 0;
            for (int i = Slice_No; i < rootobj.size(); i++) {
                Others_Count += rootobj.get(i).getAsJsonObject().get(Elements.get(1)).getAsInt();
            }
            pieChart.addSeries("Others", Others_Count);
        }

        //display chart
        new SwingWrapper(pieChart).displayChart();

    }

    public void DrawBarChartFromJSON(String title, int Slice_No, boolean Others, String JsonPath) throws MalformedURLException, IOException {
        // Connect to the URL using java's native library
        URL url = new URL(JsonPath);
        URLConnection request = url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonArray rootobj = root.getAsJsonArray();

        //Getting Elements Names of JSON
        List<String> Elements = rootobj.get(0).getAsJsonObject().keySet().stream().collect(Collectors.toList());

        //Splitting Elements Names  
        List<String> ElementsNames = new ArrayList<>();
        List<Integer> Count = new ArrayList<>();

        for (int i = 0; i < Slice_No; i++) {
            ElementsNames.add(rootobj.get(i).getAsJsonObject().get(Elements.get(0)).getAsString());
            Count.add(rootobj.get(i).getAsJsonObject().get(Elements.get(1)).getAsInt());
        }
        if (Others) {
            int Others_Count = 0;
            for (int i = Slice_No; i < rootobj.size(); i++) {
                Others_Count += rootobj.get(i).getAsJsonObject().get(Elements.get(1)).getAsInt();
            }
            ElementsNames.add("Others");
            Count.add(Others_Count);
        }

        //Creating The Bar Chart
        CategoryChart chart = new CategoryChartBuilder().width(1024*2).height(768*2).title(title).xAxisTitle(Elements.get(0)).yAxisTitle(Elements.get(1)).build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);  //numbers inside blocks
        //adding the sets to chart
        chart.addSeries(title, ElementsNames, Count);
        //Display
        chart.getStyler().setXAxisLabelRotation(45);
        new SwingWrapper(chart).displayChart();

    }
}
