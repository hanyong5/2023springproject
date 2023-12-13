package com.study.springboot.service;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.api.request.CreateAndEditRestaurantRequest;
import com.study.springboot.api.request.RestaurantView;
import com.study.springboot.entity.MenuEntity;
import com.study.springboot.entity.RestaurantEntity;
import com.study.springboot.repository.MenuRepository;
import com.study.springboot.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RestaurantService {
	
	private final RestaurantRepository restaurantRepositry;
	private final MenuRepository menuRepositroy;
	
	public RestaurantEntity createRestaurant(
			CreateAndEditRestaurantRequest request
			) {
		RestaurantEntity restaurant = RestaurantEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
		restaurantRepositry.save(restaurant);

        request.getMenus().forEach((menu) -> {
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurant.getId())
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .createdAt(ZonedDateTime.now())
                    .updatedAt(ZonedDateTime.now())
                    .build();

            menuRepositroy.save(menuEntity);
        });

        return restaurant;
	}

	public List<RestaurantView> getAllRestaurants() {
		List<RestaurantEntity> restaurants =restaurantRepositry.findAll();
		
		return restaurants.stream().map((restaurant)-> RestaurantView.builder()
				.id(restaurant.getId())
				.name(restaurant.getName())
				.address(restaurant.getAddress())
				.createdAt(restaurant.getCreatedAt())
				.updatedAt(restaurant.getCreatedAt())
				.build()).toList();
	}
	
	
	
}
