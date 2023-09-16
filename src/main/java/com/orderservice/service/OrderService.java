package com.orderservice.service;

import com.orderservice.dto.OrderDto;

public interface OrderService {
    String createOrder(OrderDto orderDto, String authToken);
}
