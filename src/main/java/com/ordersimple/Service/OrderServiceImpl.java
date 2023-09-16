package com.ordersimple.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordersimple.Dto.OrderDto;
import com.ordersimple.Entity.*;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class OrderServiceImpl implements OrderService{
    @Override
    public String createOrderDto(OrderDto orderDto, String authToken) {
        try {
            HttpURLConnection connection = connection(authToken);
            // Generate JSON data to send
            Order order = createOrder(orderDto);
            String jsonInputString = convertOrderDtoToJson(order);
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
    private String convertOrderDtoToJson(Order order) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(order);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private HttpURLConnection connection(String authToken) throws IOException {
        URL url = new URL("https://integration.api.scalapay.com/v2/orders");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", authToken);
        connection.setDoOutput(true);
        return connection;
    }
    private Order createOrder(OrderDto orderDto){
        Consumer consumer = getConsumer(orderDto);
        Item item = getItem(orderDto);
        Shipping shipping = getShipping(orderDto);
        TotalAmount totalAmount = getTotalAmount(orderDto);
        Merchant merchant = getMerchant();
        Order result = new Order();
        result.setConsumer(consumer);
        result.setItem(item);
        result.setMerchant(merchant);
        result.setTotalAmount(totalAmount);
        result.setShipping(shipping);

        return result;
    }
    private Merchant getMerchant() {
        Merchant merchant = new Merchant();
        merchant.setRedirectCancelUrl("https://shareprogramming.net/huong-dan-su-dung-resttemplate-trong-spring-boot/");
        merchant.setRedirectConfirmUrl("https://www.chidoanh.com/blog/seo/trang-bao-loi-404-la-gi-va-cach-giai-quyet");
        return merchant;
    }
    private TotalAmount getTotalAmount(OrderDto orderDto) {
        TotalAmount totalAmount = new TotalAmount();
        totalAmount.setAmount("" + Integer.parseInt(orderDto.getPrice())*Integer.parseInt(orderDto.getQuantity()));
        totalAmount.setCurrency("EUR");
        return totalAmount;
    }
    private Shipping getShipping(OrderDto orderDto) {
        Shipping shipping = new Shipping();
        shipping.setName(orderDto.getCountryName());
        shipping.setSuburb(orderDto.getSuburb());
        shipping.setCountryCode(orderDto.getCountryCode());
        shipping.setPostcode(orderDto.getPostCode());
        shipping.setLine1(orderDto.getLine1());
        return shipping;
    }
    private Item getItem(OrderDto orderDto){
        Item item = new Item();
        item.setName(orderDto.getItemName());
        item.setSku(orderDto.getSku());
        item.setQuantity(orderDto.getQuantity());
        item.setCategory(orderDto.getCategory());
        Price price = new Price();
        price.setAmount(orderDto.getPrice());
        item.setPrice(price);
        return item;
    }
    private Consumer getConsumer(OrderDto orderDto){
        Consumer consumer =new Consumer();
        consumer.setGivenNames(orderDto.getGivenNames());
        consumer.setSurname(orderDto.getSurname());
        consumer.setPhoneNumber(orderDto.getPhoneNumber());
        consumer.setEmail(orderDto.getEmail());
        return consumer;
    }
}
