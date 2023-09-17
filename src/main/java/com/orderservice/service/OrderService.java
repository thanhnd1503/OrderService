package com.orderservice.service;

import com.orderservice.payload.request.OrderCreatingRequest;
import com.orderservice.payload.response.OrderCreatingResponse;

public interface OrderService {
    OrderCreatingResponse createOrder(OrderCreatingRequest orderCreatingRequest);
}
