package br.com.cmdev.jdbc.api.entity;

import br.com.cmdev.jdbc.api.utils.UserRole;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private Boolean active;
    private LocalDateTime creationDate;
    private LocalDateTime changeDate;

}
