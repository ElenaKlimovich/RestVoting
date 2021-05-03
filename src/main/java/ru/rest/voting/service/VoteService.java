package ru.rest.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rest.voting.model.Vote;
import ru.rest.voting.repository.RestaurantRepository;
import ru.rest.voting.repository.UserRepository;
import ru.rest.voting.repository.VoteRepository;
import ru.rest.voting.util.exception.VotingException;

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

    private void save(int userId, int restId) {
        Vote vote = new Vote();
        vote.setUser(userRepository.getOne(userId));
        vote.setRestaurant(restaurantRepository.getOne(restId));
        voteRepository.save(vote);
    }

    private void update(Vote vote, int userId, int restId) {
        vote.setUser(userRepository.getOne(userId));
        vote.setRestaurant(restaurantRepository.getOne(restId));
        voteRepository.save(vote);
    }

    public List<Vote> getByUser(int userId) {
        return voteRepository.findByUser(userId);
    }

    public List<Vote> getByRestaurant(int restId) {
        return voteRepository.findByRestaurant(restId);
    }

    @Transactional
    public void vote(int userId, int restId) throws VotingException {
        Vote vote = voteRepository.findByUserIdAndDateTime(userId, LocalDateTime.now());
        if (vote == null) {
            save(userId, restId);
        } else {
            if (LocalTime.now().isAfter(VOTE_DEADLINE))
                throw new VotingException(VOTE_DEADLINE);
            else
                update(vote, userId, restId);
        }
    }
}