package com.tharani.userauthservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreateRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
