package com.study.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.springboot.entity.MenuEntity;

import jakarta.transaction.Transactional;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
	public List<MenuEntity> findAllByRestaurantId(Long restaurantId);
	
	
	@Transactional
    @Modifying
    @Query("UPDATE MenuEntity m SET m.count = m.count + 1 WHERE m.id = :menuId")
    void incrementLikeCount(@Param("menuId") Long menuId);
}
