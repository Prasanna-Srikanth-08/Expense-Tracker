package com.expensetracker.DTO;

import lombok.Data;

@Data
public class JWTResponse {
    private String token;
    private String refreshToken;
}
