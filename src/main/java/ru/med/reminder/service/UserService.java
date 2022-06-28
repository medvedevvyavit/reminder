package ru.med.reminder.service;

import java.util.List;
import java.util.UUID;
import ru.med.reminder.dto.UserDto;
import ru.med.reminder.model.User;

public interface UserService {

    UserDto register(UserDto user);
    List<UserDto> getAll();
    UserDto findByLogin(String login);
    UserDto deleteById(UUID id);
}
