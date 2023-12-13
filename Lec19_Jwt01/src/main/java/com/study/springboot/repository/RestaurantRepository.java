package com.study.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.springboot.entity.RestaurantEntity;


@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

}
