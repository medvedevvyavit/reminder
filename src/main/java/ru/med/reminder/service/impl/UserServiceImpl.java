package ru.med.reminder.service.impl;

import java.util.List;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.med.reminder.dto.UserDto;
import ru.med.reminder.mapper.UserMapper;
import ru.med.reminder.repository.RoleRepository;
import ru.med.reminder.repository.UserRepository;
import ru.med.reminder.service.UserService;
import static java.lang.String.format;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_USER_ROLE = "ROLE-USER";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;

    @Override
    public UserDto register(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword()));
//        if (user.getRoles() == null) {
//            Role role = roleRepository
//                    .findByName(DEFAULT_USER_ROLE)
//                    .orElseThrow(() -> new EntityNotFoundException(format("{} not found", DEFAULT_USER_ROLE)));
//            role.getUsers().add(user);
//            user.setRoles(List.of(role));
//        }
        var savedUser = userRepository.save(mapper.toEntity(userDto));
        log.info("The user with the login {} has been successfully registered", savedUser.getLogin());
        return mapper.toDto(savedUser);
    }

    @Override
    public List<UserDto> getAll() {
        var users = mapper.toDtos(userRepository.findAll());
        log.info("{} users found", users.size());
        return users;
    }

    @Override
    public UserDto findByLogin(String login) {
        UserDto userDto = mapper.toDto(userRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException(format("user by login: {} not found", login))));
        log.info("user: {} found by login: {}", userDto, login);
        return userDto;
    }

    @Override
    public UserDto deleteById(UUID id) {
        UserDto userDto = mapper.toDto(userRepository.deleteUserById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("user by id: {} not found", id))));
        log.info("user: {} deleting by id: {}", userDto, id);
        return userDto;
    }
}
