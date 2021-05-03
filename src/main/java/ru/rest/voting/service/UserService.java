package ru.rest.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.rest.voting.model.User;
import ru.rest.voting.repository.UserRepository;
import ru.rest.voting.util.exception.NotFoundException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User get(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND + " id = " + id));
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be NULL");
        return repository.getByEmail(email);
    }

    public User create(User user) {
        Assert.notNull(user, "User must not be NULL");
        return repository.save(user);
    }

    public void update(User user) {
        Assert.notNull(user, "User must not be NULL");
        repository.save(user);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
