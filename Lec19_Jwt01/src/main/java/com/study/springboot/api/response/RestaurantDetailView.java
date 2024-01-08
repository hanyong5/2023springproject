package com.study.springboot.api.response;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class RestaurantDetailView {
	private Long id;
	private String name;
	private String address;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
	private List<Menu> menus;
	
	@Getter
	@Builder
	@AllArgsConstructor
	public static class Menu{
		private Long id;
		private String name;
		private Integer price;
		private int count;
		private ZonedDateTime createdAt;
		private ZonedDateTime updatedAt;
	}
}
