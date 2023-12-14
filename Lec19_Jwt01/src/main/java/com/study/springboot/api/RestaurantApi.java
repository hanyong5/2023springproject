package com.study.springboot.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.request.CreateAndEditRestaurantRequest;
import com.study.springboot.api.request.RestaurantView;
import com.study.springboot.api.response.RestaurantDetailView;
import com.study.springboot.service.RestaurantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RestaurantApi {
	private final RestaurantService restaurantService;
	
	@Operation(summary="매장수정")
	@PutMapping("/restaurant/{restaurantId}")
	public void editRestaurant(
			@Parameter(name = "restaurantId", description = "레스토랑 ID", in = ParameterIn.PATH, schema = @Schema(type = "integer"))
			@PathVariable Long restaurantId,
			@RequestBody CreateAndEditRestaurantRequest request
			)
	{
		restaurantService.editRestaurant(restaurantId, request);
	}
	
	@Operation(
			summary="매장정보",
			description="restaurant information"
			)
//	@Parameter(name="name",description="이름q")
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
	
	@Operation(summary="매장삭제")
	@DeleteMapping("/restaurant/{restaurantId}")
	public void deletRestaurant(
			@Parameter(name = "restaurantId", description = "레스토랑 ID", in = ParameterIn.PATH, schema = @Schema(type = "integer"))
			@PathVariable Long restaurantId
			) {
		restaurantService.deleteRestaurant(restaurantId);
	}
	
	@Operation(summary="매장/메뉴정보")
	@GetMapping("/restaurant/{restaurantId}")
    public RestaurantDetailView getRestaurant(
    		@Parameter(name = "restaurantId", description = "레스토랑 ID", in = ParameterIn.PATH, schema = @Schema(type = "integer"))
            @PathVariable Long restaurantId
    ) {
        return restaurantService.getRestaurantDetail(restaurantId);
    }
	
	
}
