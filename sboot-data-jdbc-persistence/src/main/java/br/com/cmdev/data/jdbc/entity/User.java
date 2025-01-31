package br.com.cmdev.data.jdbc.entity;

import br.com.cmdev.data.jdbc.utils.Constants;
import br.com.cmdev.data.jdbc.utils.UserRole;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(Constants.TABLE_USERS)
public class User {

    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private Boolean isActive;
    private LocalDateTime creationDate;
    private LocalDateTime changeDate;

}
