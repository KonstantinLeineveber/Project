package com.example.servingwebcontent.service.userService;

import com.example.servingwebcontent.service.IService;

public interface IUserService<T> extends IService<T> {
    boolean findUserByUsername(String username);

    boolean findUserByEmail(String email);


}
