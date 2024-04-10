package com.expensetracker.Api;

import com.expensetracker.DTO.JWTResponse;
import com.expensetracker.DTO.LoginDTO;
import com.expensetracker.DTO.UserDTO;
import com.expensetracker.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class UserApi {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public UserDTO userDTO(@RequestBody UserDTO userDTO){
        return authenticationService.signUp(userDTO);
    }

    @PostMapping("/login")
    public JWTResponse login(@RequestBody LoginDTO loginDTO) throws Exception {
        return authenticationService.login(loginDTO);
    }
}
