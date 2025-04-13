package br.com.cmdev.data.jpa.entity;


import br.com.cmdev.data.jpa.utils.DataBaseTables;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DataBaseTables.TABLE_ADDRESS, schema = "cmdev_tests")
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1979550165915730094L;

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "streat")
    private String streat;

    @Column(name = "number")
    private String number;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
