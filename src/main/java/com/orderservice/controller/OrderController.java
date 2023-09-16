package com.orderservice.controller;

import com.orderservice.dto.OrderDto;
import com.orderservice.payload.OrderResponseDto;
import com.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto, BindingResult bindingResult,
                                      @RequestHeader("Authorization") final String authToken) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>("Creating order failed!!", HttpStatus.BAD_REQUEST);
        }
        String dataResponse = orderService.createOrder(orderDto, authToken);
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setStatus("201");
        orderResponseDto.setMessage("Order created!!");
        orderResponseDto.setOrderDto(orderDto);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
