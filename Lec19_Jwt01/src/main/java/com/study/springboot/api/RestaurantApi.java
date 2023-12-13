package com.study.springboot.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.request.CreateAndEditRestaurantRequest;
import com.study.springboot.api.request.RestaurantView;
import com.study.springboot.service.RestaurantService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RestaurantApi {
	private final RestaurantService restaurantService;
	
	
	@GetMapping("/restaurants")
	public List<RestaurantView> getRestaurants(){
		return restaurantService.getAllRestaurants();
	}
	
	
	
	@PostMapping("/restaurant")
	public void createRestaurant(
			@RequestBody CreateAndEditRestaurantRequest request
			) 
	{
		restaurantService.createRestaurant(request);
	}
}
