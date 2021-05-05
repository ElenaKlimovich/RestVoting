package ru.rest.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.rest.voting.model.Menu;
import ru.rest.voting.repository.MenuRepository;
import ru.rest.voting.repository.RestaurantRepository;
import ru.rest.voting.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

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
        Menu menu = menuRepository.getOne(id);
        return (menu != null && menu.getRestaurant().getId() == restId) ? menu : null;
    }

    @Cacheable(value = "menus")
    public List<Menu> getAll(int restId, LocalDateTime dateTime) {
        return menuRepository.findAllByRestaurantAndDateTime(restId, dateTime);
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Transactional
    public Menu create(Menu menu, int restId) {
        Assert.notNull(menu, "Menu must not be null");
        menu.setRestaurant(restaurantRepository.getOne(restId));
        return menuRepository.save(menu);
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Transactional
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