package ru.rest.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.rest.voting.model.Menu;
import ru.rest.voting.repository.MenuRepository;
import ru.rest.voting.repository.RestaurantRepository;
import ru.rest.voting.util.exception.NotFoundException;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Menu get(int id, int restId) throws NotFoundException {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new NotFoundException("Menu with id = " + id + " not found"));
        return (menu.getRestaurant().getId() == restId) ? menu : null;
    }

    @CacheEvict(value = "menus", allEntries = true)
    public Menu create(Menu menu, int restId) {
        Assert.notNull(menu, "Menu must not be null");
        menu.setRestaurant(restaurantRepository.getOne(restId));
        return menuRepository.save(menu);
    }

    @CacheEvict(value = "menus", allEntries = true)
    public void update(Menu menu, int restId) throws NotFoundException {
        menu.setRestaurant(restaurantRepository.getOne(restId));
        Assert.notNull(menu, "Menu must not be null");
        menuRepository.save(menu);
    }

    @CacheEvict(value = "menus", allEntries = true)
    public void delete(int id, int restId) {
        menuRepository.delete(get(id, restId));
    }
}