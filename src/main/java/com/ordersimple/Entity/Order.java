package com.ordersimple.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    TotalAmount totalAmount;
    Consumer consumer;
    Shipping shipping;
    Item item;
    Merchant merchant;
}
