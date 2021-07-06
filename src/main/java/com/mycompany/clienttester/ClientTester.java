package com.mycompany.clienttester;

import java.io.IOException;
import java.net.MalformedURLException;


public class ClientTester {

    public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException{
        CLI app = new CLI();
        app.run();
    }
}
