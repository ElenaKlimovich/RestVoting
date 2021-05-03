package ru.rest.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rest.voting.model.Vote;
import ru.rest.voting.repository.RestaurantRepository;
import ru.rest.voting.repository.UserRepository;
import ru.rest.voting.repository.VoteRepository;
import ru.rest.voting.util.exception.VotingException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class VoteService {

    public static final LocalTime VOTE_DEADLINE = LocalTime.of(11, 00);

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    private Vote create(int userId, int restId) {
        Vote vote = new Vote();
        vote.setUser(userRepository.getOne(userId));
        vote.setRestaurant(restaurantRepository.getOne(restId));
        return voteRepository.save(vote);
    }

    private Vote update(Vote vote, int userId, int restId) {
        vote.setDate(LocalDate.now());
        vote.setUser(userRepository.getOne(userId));
        vote.setRestaurant(restaurantRepository.getOne(restId));
        return voteRepository.save(vote);
    }

    @Transactional
    public Vote vote(int userId, int restId) throws VotingException {
        Vote vote = voteRepository.findByUserIdAndDate(userId, LocalDate.now());
        if (vote == null) {
            vote = create(userId, restId);
        } else {
            if (LocalTime.now().isAfter(VOTE_DEADLINE))
                throw new VotingException();
            else
                vote = update(vote, userId, restId);
        }
        return vote;
    }
}