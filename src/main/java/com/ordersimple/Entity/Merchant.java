package com.ordersimple.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Merchant {
    private String redirectCancelUrl;
    private String redirectConfirmUrl;
}
