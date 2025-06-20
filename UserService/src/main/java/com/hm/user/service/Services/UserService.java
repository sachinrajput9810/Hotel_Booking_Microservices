package com.hm.user.service.Services;

import com.hm.user.service.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    // user oeprations

    User saveUser(User user) ;
    List<User> getAllUsers() ;
    User getUser(String userId) ;

}
