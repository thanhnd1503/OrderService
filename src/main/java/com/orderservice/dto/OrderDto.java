package com.orderservice.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    @NotEmpty(message = "Given Names is required")
    @Pattern(regexp = "[^0-9]*", message = "Given Names cannot contain numbers")
    String givenNames;
    @NotEmpty(message = "Surname is required")
    @Pattern(regexp = "[^0-9]*", message = "Surname cannot contain numbers")
    String surname;
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    String email;
    @NotEmpty(message = "Phone Number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone Number must be 10 digits")
    String phoneNumber;
    @NotEmpty(message = "Country Code is required")
    @Pattern(regexp = "^[a-zA-Z]{2}$", message = "Country Code must be 2 letters")
    String countryCode;
    @NotEmpty(message = "Country Name is required")
    String countryName;
    @NotEmpty(message = "Post Code is required")
    String postCode;
    @NotEmpty(message = "Suburb is required")
    String suburb;
    @NotEmpty(message = "Line1 is required")
    String line1;
    @NotEmpty(message = "Item Name is required")
    String itemName;
    @NotEmpty(message = "Quantity is required")
    @Pattern(regexp = "\\d+", message = "Quantity must be a number")
    String quantity;
    @NotEmpty(message = "Price is required")
    @Pattern(regexp = "\\d+", message = "Price must be a number")
    String price;
    @NotEmpty(message = "Category is required")
    String category;
    @NotEmpty(message = "SKU is required")
    String sku;
}
