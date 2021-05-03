package ru.rest.voting.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rest.voting.model.Menu;
import ru.rest.voting.service.RestaurantService;
import ru.rest.voting.to.RestaurantTo;
import ru.rest.voting.util.RestaurantUtil;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantRestController {

    private static Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    @Autowired
    private final RestaurantService service;

    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("Get all restaurants");
        return RestaurantUtil.getAllRestaurantTosWithVotes(service.getAll());
    }

    @GetMapping("{id}")
    public RestaurantTo get(@PathVariable("id") int id) {
        log.info("Get restaurant with id: {} ", id);
        return RestaurantUtil.createRestaurantTo(service.get(id));
    }

//    @GetMapping("meals/{id}")
//    public List<Menu> getMenus(@PathVariable("id") int id) {
//        log.info("Menu for restaurant with id: {} ", id);
//        return service.getWithMenus(id, LocalDateTime.now());
//    }
}
