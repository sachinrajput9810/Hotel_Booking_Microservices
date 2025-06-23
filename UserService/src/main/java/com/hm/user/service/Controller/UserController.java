package com.hm.user.service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hm.user.service.Entities.User;
import com.hm.user.service.Services.UserService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/users")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user) ;
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }


    //  Implementing circuit breaker , retry and rate limiter
    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker" , fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService" , fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter" , fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        log.info("Get single user handler :: Controller");
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Rating Hotel fallback for circuit breaker pattern.......
    public ResponseEntity<User> ratingHotelFallback(String userId , Exception ex){
        log.info("Fallback is executed because service is down : " , ex.getMessage());
        User user = User.builder()
                                .email("dummy@gmail.com")
                                .name("Dummy User")
                                .about("I am dummy user")
                                .userId("000000")
                                .build() ;
        return new ResponseEntity<>(user , HttpStatus.TOO_MANY_REQUESTS) ;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList = userService.getAllUsers() ;
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
