package ru.rest.voting.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.rest.voting.model.User;
import ru.rest.voting.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private static Logger log = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers() {
        log.info("Get all users");
        return service.getAll();
    }

    @GetMapping("/by")
    public User getByMail(@RequestParam String email) {
        return service.getByEmail(email);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user) {
        log.info("User was updated");
        service.update(user);
    }
}
