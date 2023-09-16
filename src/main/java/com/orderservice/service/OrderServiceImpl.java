package com.orderservice.service;

import com.orderservice.connection.ScalaPayConnection;
import com.orderservice.dto.OrderDto;
import com.orderservice.entity.*;
import com.orderservice.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Override
    public String createOrder(OrderDto orderDto, String authToken) {
        try {
            HttpURLConnection connection = ScalaPayConnection.getScalaPayConnection(authToken);
            // Generate JSON data to send
            Order order = orderMapper.convertDtoToEntity(orderDto);
            String jsonInputString = orderMapper.convertDtoToJson(order);
            // Write JSON data to the connection's output stream
            try (DataOutputStream os = new DataOutputStream(connection.getOutputStream())) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            // Read the response from the connection
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return response.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "Error reading HTTP response.";
            } finally {
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending HTTP request.";
        }

    }
}
