package com.expensetracker.Service;

import com.expensetracker.Entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface JwtService {
    String generateToken(User user);
    String extractUserName(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
    String refreshToken(Map<String,Object> claims, User user);
}
