package com.study.springboot.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.request.CreateAndEditRestaurantRequest;
import com.study.springboot.service.RestaurantService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RestaurantApi {
	private final RestaurantService restaurantService;
	
	@PostMapping("/restaurant")
	public void createRestaurant(
			@RequestBody CreateAndEditRestaurantRequest request
			) 
	{
		restaurantService.createRestaurant(request);
	}
}
