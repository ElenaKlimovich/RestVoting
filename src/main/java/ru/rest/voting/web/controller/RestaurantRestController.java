package ru.rest.voting.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.rest.voting.model.Menu;
import ru.rest.voting.model.Restaurant;
import ru.rest.voting.service.RestaurantService;
import ru.rest.voting.to.RestaurantTo;
import ru.rest.voting.util.RestaurantUtil;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("Get all restaurants");
        return service.getAll();
    }

    @GetMapping("/votes")
    public List<RestaurantTo> getAllWithVotes() {
        log.info("Get all restaurants with votes");
        return RestaurantUtil.getAllRestaurantTosWithVotes(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public RestaurantTo get(@PathVariable int id) {
        log.info("Get restaurant with id: {} ", id);
        return RestaurantUtil.createRestaurantTo(service.get(id));
    }

    @GetMapping("/{id}/menus")
    public List<Menu> getMenus(@PathVariable int id) {
        log.info("Menu for restaurant with id: {} ", id);
        return service.getWithMenus(id, LocalDateTime.now()).getMenus();
    }
}
