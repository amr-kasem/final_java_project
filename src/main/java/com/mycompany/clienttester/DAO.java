package com.mycompany.clienttester;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DAO {
    private Gson gson = new Gson();
    private OkHttpClient client = new OkHttpClient();
    private Type listType = new TypeToken<List<JsonObject>>() {
    }.getType();

    private void displayChart(Chart c) {
        JFrame frame = new SwingWrapper(c).displayChart();
        javax.swing.SwingUtilities.invokeLater(() -> frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE));
    }
    private static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }
    public List<JsonObject> getData(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return gson.fromJson(response.body().string(), listType);
        }
        
    }

    public void DrawPieChartFromJSON(String title, int Slice_No, boolean Others, String col1, String col2,
            String JsonPath)

            throws MalformedURLException, IOException {
        List<JsonObject> Elements = getData(JsonPath);

        List<String> ElementsNames = Elements.stream().map(e -> e.get(col1).getAsString()).collect(Collectors.toList());
        List<Integer> Count = Elements.stream().map(e -> e.get(col2).getAsInt()).collect(Collectors.toList());

        PieChart pieChart = new PieChartBuilder().width(1024 * 2).height(768 * 2).title(title).build();
        // adding sets to chart
        for (int i = 0; i < Slice_No; i++) {
            pieChart.addSeries(ElementsNames.get(i), Count.get(i));
        }

        // Adding Others
        if (Others) {
            pieChart.addSeries("Others",
                    Count.subList(Slice_No + 1, Count.size()).stream().collect(Collectors.summingInt(m -> m)));
        }

        displayChart(pieChart);
        // display chart

    }

    public void DrawBarChartFromJSON(String title, int Slice_No, boolean Others, String col1, String col2,
            String JsonPath) throws MalformedURLException, IOException {
        // Connect to the URL using java's native library
        List<JsonObject> Elements = getData(JsonPath);

        List<String> ElementsNames = Elements.subList(0, Slice_No).stream().map(e -> e.get(col1).getAsString())
                .collect(Collectors.toList());
        List<Integer> Count = Elements.stream().map(e -> e.get(col2).getAsInt()).collect(Collectors.toList());
        Integer other_count = Count.subList(Slice_No + 1, Count.size()).stream().collect(Collectors.summingInt(m -> m));
        Count = Count.subList(0, Slice_No);
        if (Others) {
            ElementsNames.add("Others");
            Count.add(other_count);
        }

        // Creating The Bar Chart
        CategoryChart chart = new CategoryChartBuilder().width(1024 * 2).height(768 * 2).title(title).xAxisTitle(col1)
                .yAxisTitle(col2).build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true); // numbers inside blocks
        // adding the sets to chart
        chart.addSeries(title, ElementsNames, Count);
        // Display
        chart.getStyler().setXAxisLabelRotation(45);
        displayChart(chart);
    }
    public void printJson(String JsonPath,int Slice_No) throws IOException{
        List<JsonObject> data = getData(JsonPath);
        if (Slice_No != -1) data = data.subList(0, Slice_No);
        data.stream().forEach(o->{
            System.out.println(o.toString());
        });
        System.out.print("Press any key to Continue...");
        System.in.read();
        System.out.println();
    }
    void printTable( String JsonPath,int Slice_No) throws IOException {
        List<JsonObject> data = getData(JsonPath);
        if (Slice_No != -1) data = data.subList(0, Slice_No);
        data.get(0).keySet().stream().forEach(s->{
            System.out.print(fixedLengthString(s, 12));
            System.out.print('\t');
        });
        System.out.println();
        data.stream().forEach(o->{
            o.entrySet().stream().forEach(s->{
                String v;
                v = s.getValue().getAsString();
                System.out.print(fixedLengthString(v.substring(0,v.length()>12?12:v.length()), 12));
                System.out.print('\t');
            });
            System.out.println();
        });
        System.out.println();
        System.out.println("Press Enter key to Continue...");
        System.in.read();
        System.out.println();
        System.out.println();
    }
}
