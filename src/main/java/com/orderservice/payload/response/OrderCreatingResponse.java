package com.orderservice.payload.response;

import com.orderservice.dto.OrderDto;
import com.orderservice.dto.ScalaOrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderCreatingResponse {
    private String message;
    private ScalaOrderDto scalaOrderDto;
}
