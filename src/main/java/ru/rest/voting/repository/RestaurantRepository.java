package ru.rest.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rest.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT r from Restaurant r LEFT JOIN FETCH r.menus m WHERE r.id=:id AND m.date=:date")
    Restaurant findWithMenus(@Param("id") int id, @Param("date") LocalDate dateTime);

    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.menus m WHERE m.date=:date")
    List<Restaurant> findAllWithMenus(@Param("date") LocalDate date);
}