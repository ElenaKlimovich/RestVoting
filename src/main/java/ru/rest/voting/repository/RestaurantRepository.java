package ru.rest.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rest.voting.model.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT r from Restaurant r LEFT JOIN FETCH r.menus m WHERE r.id=:id AND m.dateTime=:dateTime")
    Restaurant findWithMenus(@Param("id") int id, @Param("dateTime") LocalDateTime dateTime);

    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.menus m WHERE m.dateTime=:dateTime")
    List<Restaurant> findAllWithMenus(@Param("dateTime") LocalDateTime dateTime);
}