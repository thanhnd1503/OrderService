package com.ordersimple.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Consumer {

    private String phoneNumber ;
    private String givenNames;
    private String surname;
    private String email;
}
