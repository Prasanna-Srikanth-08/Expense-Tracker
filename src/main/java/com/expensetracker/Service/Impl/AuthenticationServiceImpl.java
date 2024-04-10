package com.expensetracker.Service.Impl;

import com.expensetracker.DTO.JWTResponse;
import com.expensetracker.DTO.LoginDTO;
import com.expensetracker.DTO.UserDTO;
import com.expensetracker.Entity.User;
import com.expensetracker.Enum.Role;
import com.expensetracker.Repository.UserRepository;
import com.expensetracker.Service.AuthenticationService;
import com.expensetracker.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public UserDTO signUp(UserDTO userDTO) {
        User user = new User();
        user.setUserId(user.getUserId());
        user.setName(userDTO.getName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUserName(userDTO.getUserName());
        user.setRole(Role.USER);
        User persistedUser = userRepository.save(user);
        return User.prepareUserDTO(persistedUser);
    }

    public JWTResponse login(LoginDTO loginDTO) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserName(),loginDTO.getPassword()));
        Optional<User> user = Optional.of(userRepository.findByUserName(loginDTO.getUserName()).orElseThrow(() -> new Exception("User name not found")));
        String jwt = jwtService.generateToken(user.get());
        String refreshToken = jwtService.refreshToken(new HashMap<>(),user.get());
        JWTResponse jwtResponse = new JWTResponse();
        jwtResponse.setToken(jwt);
        jwtResponse.setRefreshToken(refreshToken);
        return jwtResponse;
    }
}
