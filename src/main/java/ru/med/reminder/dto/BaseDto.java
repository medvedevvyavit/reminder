package ru.med.reminder.dto;

import java.util.Date;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.med.reminder.enums.UserStatus;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BaseDto {

    private UUID id;
    private Date createdDate;
    private Date updatedDate;
    private UserStatus userStatus;
}
