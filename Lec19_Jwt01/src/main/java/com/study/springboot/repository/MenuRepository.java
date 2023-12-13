package com.study.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.springboot.entity.MenuEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
	public List<MenuEntity> findAllByRestaurantId(Long restaurantId);
}
