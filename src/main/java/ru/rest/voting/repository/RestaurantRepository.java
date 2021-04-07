package ru.rest.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.rest.voting.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
