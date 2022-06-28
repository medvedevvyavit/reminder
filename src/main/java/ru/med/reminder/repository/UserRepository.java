package ru.med.reminder.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.med.reminder.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByLogin(String login);
    Optional<User> deleteUserById(UUID id);
}
