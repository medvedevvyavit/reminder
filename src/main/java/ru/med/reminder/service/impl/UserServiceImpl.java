package ru.med.reminder.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.med.reminder.dto.UserDto;
import ru.med.reminder.enums.RoleName;
import ru.med.reminder.enums.UserStatus;
import ru.med.reminder.mapper.RoleMapper;
import ru.med.reminder.mapper.UserMapper;
import ru.med.reminder.repository.RoleRepository;
import ru.med.reminder.repository.UserRepository;
import ru.med.reminder.service.UserService;
import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Override
    public String register(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword()));

        if (Optional.of(userDto.getRoles()).isEmpty()) {
            userDto.setRoles(List.of(roleMapper.toDto(roleRepository
                    .findByName(RoleName.USER_ROLE.name())
                    .orElseThrow(() -> new EntityNotFoundException(format("default user role '{}' not found", RoleName.USER_ROLE.name()))))));
        }

        userDto.setUserStatus(UserStatus.ACTIVE);
        var login = userRepository.save(userMapper.toEntity(userDto)).getLogin();
        log.info("The user with the login {} has been successfully registered", login);
        return login;
    }

    @Override
    public List<UserDto> getAll() {
        var users = userMapper.toDtos(userRepository.findAll());
        log.info("{} users found", users.size());
        return users;
    }

    @Override
    public UserDto findByLogin(String login) {
        UserDto userDto = userMapper.toDto(userRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException(format("user by login: {} not found", login))));
        log.info("user: {} found by login: {}", userDto, login);
        return userDto;
    }

    @Override
    public UserDto deleteById(UUID id) {
        UserDto userDto = userMapper.toDto(userRepository.deleteUserById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("user by id: {} not found", id))));
        log.info("user: {} deleting by id: {}", userDto, id);
        return userDto;
    }
}
