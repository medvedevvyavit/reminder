package ru.med.reminder.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import ru.med.reminder.dto.UserDto;
import ru.med.reminder.model.User;

@Mapper
public interface UserMapper {

    UserDto toDto(User user);
    List<UserDto> toDtos(List<User> users);
    User toEntity(UserDto user);
}
