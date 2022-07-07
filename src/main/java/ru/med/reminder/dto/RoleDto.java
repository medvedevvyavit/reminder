package ru.med.reminder.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.med.reminder.enums.RoleName;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoleDto extends BaseDto {

    private RoleName name;
}
