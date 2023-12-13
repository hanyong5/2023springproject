package com.study.springboot.service;

import java.time.ZonedDateTime;
import java.util.List;

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
	
	private final RestaurantRepository restaurantRepository;
	private final MenuRepository menuRepository;
	
	public RestaurantEntity createRestaurant(
			CreateAndEditRestaurantRequest request
			) {
		RestaurantEntity restaurant = RestaurantEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
		restaurantRepository.save(restaurant);

        request.getMenus().forEach((menu) -> {
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurant.getId())
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .createdAt(ZonedDateTime.now())
                    .updatedAt(ZonedDateTime.now())
                    .build();

            menuRepository.save(menuEntity);
        });

        return restaurant;
	}

	public List<RestaurantView> getAllRestaurants() {
		List<RestaurantEntity> restaurants =restaurantRepository.findAll();
		
		return restaurants.stream().map((restaurant)-> RestaurantView.builder()
				.id(restaurant.getId())
				.name(restaurant.getName())
				.address(restaurant.getAddress())
				.createdAt(restaurant.getCreatedAt())
				.updatedAt(restaurant.getCreatedAt())
				.build()).toList();
	}

	public void editRestaurant(Long restaurantId, CreateAndEditRestaurantRequest request) {
		 RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("없는 레스토랑입니다"));
	        restaurant.changeNameAndAddress(request.getName(), request.getAddress());
	        restaurantRepository.save(restaurant);

	        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);
	        menuRepository.deleteAll(menus);

	        request.getMenus().forEach((menu) -> {
	            MenuEntity menuEntity = MenuEntity.builder()
	                    .restaurantId(restaurantId)
	                    .name(menu.getName())
	                    .price(menu.getPrice())
	                    .createdAt(ZonedDateTime.now())
	                    .updatedAt(ZonedDateTime.now())
	                    .build();

	            menuRepository.save(menuEntity);
	        });
		
	}


	
	
	
}
