package com.topcualperen.business;

import com.topcualperen.dto.UserDto;
import com.topcualperen.entities.User;


public interface UserService {

    UserDto add(UserDto user);

    UserDto findByEmail(Long id);

    UserDto deleteUser(Long id);


    User getOneUserByUserName(String userName);
}
