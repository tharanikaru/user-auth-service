package com.tharani.userauthservice.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInRequest {
    private String email;
    private String password;

}
