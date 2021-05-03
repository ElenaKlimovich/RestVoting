package ru.rest.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rest.voting.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    List<Vote> findByUser(int userId);

    List<Vote> findByRestaurant(int restId);

    Vote findByUserIdAndDateTime(int userId, LocalDateTime dateTime);
}
