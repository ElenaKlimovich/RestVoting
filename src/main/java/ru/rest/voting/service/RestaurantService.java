package ru.rest.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.rest.voting.model.Restaurant;
import ru.rest.voting.repository.RestaurantRepository;
import ru.rest.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant with id = " + id + " not found"));
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    public List<Restaurant> getAllWithMenus(LocalDate date) {
        return repository.findAllWithMenus(date);
    }

    public Restaurant getWithMenus(int id, LocalDate date) {
        return repository.findWithMenus(id, date);
    }

    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public void update(Restaurant restaurant) {
        repository.save(restaurant);
    }

    public void delete(int id) {
        repository.delete(get(id));
    }
}