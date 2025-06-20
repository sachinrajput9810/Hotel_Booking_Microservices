package com.hm.user.service.Services.impl;


import com.hm.user.service.Entities.Hotel;
import com.hm.user.service.Exception.ResourceNotFoundException;
import com.hm.user.service.External.Services.HotelService;
import com.hm.user.service.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hm.user.service.Entities.Rating;
import com.hm.user.service.Entities.User;
import com.hm.user.service.Services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class) ;

	@Autowired
	private RestTemplate restTemplate ;

    @Autowired
    private HotelService hotelService ;

    @Autowired
    private UserRepository userRepository ;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
    	System.out.println("Get user called");
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on server with given ID :: " + userId ))  ;
        Rating[] userRatings =  restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + userId, Rating[].class) ;
        logger.info("" + userRatings);

        List<Rating> ratings = Arrays.stream(userRatings).toList() ;

        List<Rating> ratingList =  ratings.stream().map(rating -> {
            // Make API call to hotel service to get hotel  ----> set hotel to rating ---> return rating
//            ResponseEntity<Hotel> HotelEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId()  , Hotel.class) ;
            Hotel hotel = hotelService.getHotel(rating.getHotelId()) ;
            logger.info("Hotel details : {}" , hotel);
            // setting the hotel in the rating object
            rating.setHotel(hotel);
            return rating ;
        }).collect(Collectors.toList()) ;
        
        user.setRatings(ratingList) ;
        return user ;
    }

	
}
