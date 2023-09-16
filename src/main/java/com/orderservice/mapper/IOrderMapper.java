package com.orderservice.mapper;


import com.orderservice.dto.OrderDto;
import com.orderservice.entity.*;

public interface IOrderMapper {
     String convertDtoToJson(Order order);
     Order convertDtoToEntity(OrderDto orderDto);
     Merchant getMerchant() ;
     TotalAmount getTotalAmount(OrderDto orderDto) ;
     Shipping getShipping(OrderDto orderDto);
     Item getItem(OrderDto orderDto);
     Consumer getConsumer(OrderDto orderDto);
}
