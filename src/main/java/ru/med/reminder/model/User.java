package ru.med.reminder.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.med.reminder.enums.UserStatus;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(nullable = false, unique = true)
    private String login;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "users_roles",
                joinColumns =  @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    List<Role> roles;
}
