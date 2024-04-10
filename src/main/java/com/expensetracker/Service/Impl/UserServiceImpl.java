package com.expensetracker.Service.Impl;

import com.expensetracker.Repository.UserRepository;
import com.expensetracker.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                try {
                    return userRepository.findByUserName(username)
                            .orElseThrow(() -> new Exception("User Not Found"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
