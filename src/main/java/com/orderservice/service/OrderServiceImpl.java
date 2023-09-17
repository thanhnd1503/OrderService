package com.orderservice.service;

import com.orderservice.connection.AppConnection;
import com.orderservice.dto.ScalaOrderDto;
import com.orderservice.entity.Order;
import com.orderservice.mapper.OrderMapperImpl;
import com.orderservice.payload.request.OrderCreatingRequest;
import com.orderservice.payload.response.OrderCreatingResponse;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapperImpl orderMapperImpl;

    @Override
    public OrderCreatingResponse createOrder(OrderCreatingRequest request) {
        try {
            HttpURLConnection connection = AppConnection.connectToScalaPayAPI(request.getAuthorization());

            // Generate JSON data to send to ScalaPay API
            Order order = orderMapperImpl.convertDtoToEntity(request.getRequestBody());
            String orderJsonInput = orderMapperImpl.convertEntityToJson(order);

            // Write JSON data to the connection's output stream
            try (DataOutputStream os = new DataOutputStream(connection.getOutputStream())) {
                byte[] input = orderJsonInput.getBytes(CharsetUtil.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read the response from the connection
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), CharsetUtil.UTF_8))) {
                StringBuilder jsonResponse = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    jsonResponse.append(responseLine.trim());
                }

                ScalaOrderDto scalaOrderDto = orderMapperImpl.convertJsonToDto(String.valueOf(jsonResponse));
                return new OrderCreatingResponse("Place an order successfully!", scalaOrderDto);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new OrderCreatingResponse("Place an order failed!", null);
    }
}
