package com.tms.springapp.service.userService;

import com.tms.springapp.service.IService;

public interface IUserService<T> extends IService<T> {
    boolean findUserByUsername(String username);

    boolean findUserByEmail(String email);


}
