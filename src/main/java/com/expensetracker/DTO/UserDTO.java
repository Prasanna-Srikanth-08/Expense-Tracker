package com.expensetracker.DTO;

import com.expensetracker.Entity.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String userName;
    private String password;
    private String name;

    public static User prepareUser(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        user.setName(userDTO.getName());
        user.setPassword(user.getPassword());
        return user;
    }
}
