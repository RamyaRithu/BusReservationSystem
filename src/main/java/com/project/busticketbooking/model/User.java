package com.project.busticketbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user",uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="first_name")
    private  String firstName;
    @Column(name="last_name")
    private  String lastName;

    private  String password;

    @Column(name = "email")
    private  String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    @ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Collection<Role> roles;

    //constructor for all above entity field.
    public User(String firstName, String lastName, String password, String email, Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
}
