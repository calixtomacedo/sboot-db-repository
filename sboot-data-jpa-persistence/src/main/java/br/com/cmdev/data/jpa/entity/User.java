package br.com.cmdev.data.jpa.entity;

import br.com.cmdev.data.jpa.utils.DataBaseTables;
import br.com.cmdev.data.jpa.utils.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DataBaseTables.TABLE_USERS, schema = "cmdev_tests")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -4289711237009392040L;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "changeDate")
    private LocalDateTime changeDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

}
