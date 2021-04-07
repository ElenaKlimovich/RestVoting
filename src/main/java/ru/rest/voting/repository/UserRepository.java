package ru.rest.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rest.voting.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getByEmail(String email);
}
