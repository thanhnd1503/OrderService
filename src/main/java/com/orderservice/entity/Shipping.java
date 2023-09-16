package com.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shipping {
    private String countryCode;
    private String name;
    private String postcode;
    private String suburb;
    private String line1;
}
