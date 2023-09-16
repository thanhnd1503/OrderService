package com.orderservice.connection;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
@Component
public class ScalaPayConnection {
    public static HttpURLConnection getScalaPayConnection(String authToken) throws IOException {
        URL url = new URL("https://integration.api.scalapay.com/v2/orders");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", authToken);
        connection.setDoOutput(true);
        return connection;
    }
}
