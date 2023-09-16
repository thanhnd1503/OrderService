package com.ordersimple.Controller;

import com.ordersimple.Dto.OrderDto;
import com.ordersimple.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderControler {
    @Autowired
    OrderService orderService;

    @PostMapping("/sendRequest")
    public String sendHttpRequest(@Valid @RequestBody OrderDto orderDto, BindingResult bindingResult,
                                  @RequestHeader("Authorization") final String authToken) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return "You have entered the wrong format!!";
        }
        return orderService.createOrderDto(orderDto, authToken);
    }
}
