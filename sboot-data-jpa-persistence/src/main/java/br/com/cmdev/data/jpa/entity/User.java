package br.com.cmdev.data.jpa.entity;

import br.com.cmdev.data.jpa.utils.DataBaseTables;
import br.com.cmdev.data.jpa.utils.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DataBaseTables.TABLE_USERS)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
    private Boolean active;
    private LocalDateTime creationDate;
    private LocalDateTime changeDate;

}
