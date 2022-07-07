package ru.med.reminder.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import ru.med.reminder.dto.RoleDto;
import ru.med.reminder.model.Role;

@Mapper
public interface RoleMapper {

    RoleDto toDto(Role role);
    List<RoleDto> toDtos(List<Role> roles);
    Role toEntity(RoleDto role);
}
