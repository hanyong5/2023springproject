package com.study.springboot.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.request.CreateAndEditRestaurantRequest;
import com.study.springboot.api.request.RestaurantView;
import com.study.springboot.service.RestaurantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RestaurantApi {
	private final RestaurantService restaurantService;
	
	@Operation(
			summary="매장정보",
			description="restaurant information"
			)
//	@Parameter(name="name",description="이름")
	@GetMapping("/restaurants")
	public List<RestaurantView> getRestaurants(){
		return restaurantService.getAllRestaurants();
	}
	
	
	@Operation(summary="매장생성")
	@PostMapping("/restaurant")
	public void createRestaurant(
			@RequestBody CreateAndEditRestaurantRequest request
			) 
	{
		restaurantService.createRestaurant(request);
	}
}
