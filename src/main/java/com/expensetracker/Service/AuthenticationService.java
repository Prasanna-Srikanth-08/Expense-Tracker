package com.expensetracker.Service;

import com.expensetracker.DTO.JWTResponse;
import com.expensetracker.DTO.LoginDTO;
import com.expensetracker.DTO.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    UserDTO signUp(UserDTO userDTO);
    JWTResponse login(LoginDTO loginDTO) throws Exception;
}
