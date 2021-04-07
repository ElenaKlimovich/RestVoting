package ru.rest.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.rest.voting.model.Menu;
import ru.rest.voting.model.Restaurant;
import ru.rest.voting.repository.RestaurantRepository;
import ru.rest.voting.util.exception.NotFoundException;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private final RestaurantRepository repository;


    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public void update(Restaurant restaurant) {
        repository.save(restaurant);
    }

    public void delete(int id) {
        repository.delete(getById(id));
    }

    public Restaurant getById(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND + " id = " + id));
    }

    public List<Menu> getMenus(int id) {
        return getById(id).getMenus();
    }
}
