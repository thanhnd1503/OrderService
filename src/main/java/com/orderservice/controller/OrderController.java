package com.orderservice.controller;

import com.orderservice.dto.OrderDto;
import com.orderservice.payload.request.OrderCreatingRequest;
import com.orderservice.payload.response.OrderCreatingResponse;
import com.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto,
                                         @RequestHeader("Authorization") String authToken) {
        OrderCreatingRequest request = new OrderCreatingRequest();
        request.setAuthorization(authToken);
        request.setRequestBody(orderDto);

        OrderCreatingResponse response = orderService.createOrder(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
