package br.com.cmdev.data.jpa.entity;


import br.com.cmdev.data.jpa.utils.DataBaseTables;
import jakarta.persistence.*;

@Entity
@Table(name = DataBaseTables.TABLE_ADDRESS, schema = "cmdev-db")
public class Address {

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

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

}
