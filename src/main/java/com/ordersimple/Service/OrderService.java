package com.ordersimple.Service;

import com.ordersimple.Dto.OrderDto;

public interface OrderService {
    String createOrderDto(OrderDto orderDto, String authToken);
}
