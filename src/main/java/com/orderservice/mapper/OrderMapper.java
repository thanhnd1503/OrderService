package com.orderservice.mapper;


import com.orderservice.dto.OrderDto;
import com.orderservice.dto.ScalaOrderDto;
import com.orderservice.entity.*;

public interface OrderMapper {
     String convertEntityToJson(Order order);
     ScalaOrderDto convertJsonToDto(String json);
     Order convertDtoToEntity(OrderDto orderDto);
     Merchant getMerchant() ;
     TotalAmount getTotalAmount(OrderDto orderDto) ;
     Shipping getShipping(OrderDto orderDto);
     Item getItem(OrderDto orderDto);
     Consumer getConsumer(OrderDto orderDto);
}
