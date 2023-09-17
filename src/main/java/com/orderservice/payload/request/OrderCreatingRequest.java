package com.orderservice.payload.request;

import com.orderservice.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderCreatingRequest {

    private String authorization;
    private OrderDto requestBody;
}
