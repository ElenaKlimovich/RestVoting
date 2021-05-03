package ru.rest.voting.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rest.voting.model.User;
import ru.rest.voting.model.Vote;
import ru.rest.voting.service.VoteService;

@RestController
public class VoteRestController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    @PostMapping(value = "/restaurants/{id}/vote", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> vote(@PathVariable int restId, @AuthenticationPrincipal User user) {
        int userId = user.getId();
        log.info("User {} vote for restaurant {}", userId, restId);
        Vote vote = service.vote(userId, restId);
        return ResponseEntity.ok().body(vote);
    }
}