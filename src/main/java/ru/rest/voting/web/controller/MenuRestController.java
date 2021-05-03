package ru.rest.voting.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.rest.voting.model.Menu;
import ru.rest.voting.service.MenuService;
import ru.rest.voting.util.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {

    static final String REST_URL = "/admin/restaurants/{restId}/menus";
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@PathVariable int restId, @Valid @RequestBody Menu menu) {
        log.info("Create menu {} for restaurant {}", restId, menu);
        ValidationUtil.checkNew(menu);
        Menu created = service.create(menu, restId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int restId, @Valid @RequestBody Menu menu) {
        log.info("Update menu {} for restaurant {}", menu, restId);
        ValidationUtil.assureIdConsistent(menu, restId);
        service.update(menu, restId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restId, @PathVariable int id) {
        log.info("Delete menu {} for restaurant {}", id, restId);
        service.delete(id, restId);
    }
}