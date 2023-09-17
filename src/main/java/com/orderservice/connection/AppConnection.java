package com.orderservice.connection;

import com.orderservice.constant.AppConstant;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class AppConnection {
    public static HttpURLConnection connectToScalaPayAPI(String authToken) throws IOException {
        URL url = new URL(AppConstant.SCALA_PAY_ORDER_API);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", authToken);
        connection.setDoOutput(true);
        return connection;
    }
}
