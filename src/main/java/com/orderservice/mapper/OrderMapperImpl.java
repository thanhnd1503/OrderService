package com.orderservice.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderservice.dto.OrderDto;
import com.orderservice.dto.ScalaOrderDto;
import com.orderservice.entity.*;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {
    @Override
    public String convertEntityToJson(Order order) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(order);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "Conversion failed!!";
        }
    }

    @Override
    public ScalaOrderDto convertJsonToDto(String json) {
        ScalaOrderDto scalaOrderDto = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            scalaOrderDto = mapper.readValue(json, ScalaOrderDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scalaOrderDto;
    }

    @Override
    public Order convertDtoToEntity(OrderDto orderDto){
        Consumer consumer = getConsumer(orderDto);
        Item item = getItem(orderDto);
        Shipping shipping = getShipping(orderDto);
        TotalAmount totalAmount = getTotalAmount(orderDto);
        Merchant merchant = getMerchant();
        Order result = new Order();
        result.setConsumer(consumer);
        result.setItem(item);
        result.setMerchant(merchant);
        result.setTotalAmount(totalAmount);
        result.setShipping(shipping);
        return result;
    }

    @Override
    public Merchant getMerchant() {
        Merchant merchant = new Merchant();
        merchant.setRedirectCancelUrl("https://shareprogramming.net/huong-dan-su-dung-resttemplate-trong-spring-boot/");
        merchant.setRedirectConfirmUrl("https://www.chidoanh.com/blog/seo/trang-bao-loi-404-la-gi-va-cach-giai-quyet");
        return merchant;
    }

    @Override
    public TotalAmount getTotalAmount(OrderDto orderDto) {
        TotalAmount totalAmount = new TotalAmount();
        totalAmount.setAmount("" + Integer.parseInt(orderDto.getPrice())*Integer.parseInt(orderDto.getQuantity()));
        totalAmount.setCurrency("EUR");
        return totalAmount;
    }

    @Override
    public Shipping getShipping(OrderDto orderDto) {
        Shipping shipping = new Shipping();
        shipping.setName(orderDto.getCountryName());
        shipping.setSuburb(orderDto.getSuburb());
        shipping.setCountryCode(orderDto.getCountryCode());
        shipping.setPostcode(orderDto.getPostCode());
        shipping.setLine1(orderDto.getLine1());
        return shipping;
    }

    @Override
    public Item getItem(OrderDto orderDto){
        Item item = new Item();
        item.setName(orderDto.getItemName());
        item.setSku(orderDto.getSku());
        item.setQuantity(orderDto.getQuantity());
        item.setCategory(orderDto.getCategory());
        Price price = new Price();
        price.setAmount(orderDto.getPrice());
        item.setPrice(price);
        return item;
    }

    @Override
    public Consumer getConsumer(OrderDto orderDto){
        Consumer consumer =new Consumer();
        consumer.setGivenNames(orderDto.getGivenNames());
        consumer.setSurname(orderDto.getSurname());
        consumer.setPhoneNumber(orderDto.getPhoneNumber());
        consumer.setEmail(orderDto.getEmail());
        return consumer;
    }
}
