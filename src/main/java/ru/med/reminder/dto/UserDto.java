package ru.med.reminder.dto;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.med.reminder.enums.UserStatus;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserDto extends BaseDto {

    private UserStatus userStatus;
    private String login;
    private String email;
    private String password;
    private List<RoleDto> roles;
}
