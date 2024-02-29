package fr.formation.model;

import java.time.LocalDate;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "[user]")
@Getter @Setter
public class Utilisateur {
    @Id
    @UuidGenerator
    @Column(name = "usr_id")
    private String id;
    
    @Column(name = "usr_username")
    private String username;

    @Column(name = "usr_password")
    private String password;

    @Column(name = "usr_email")
    private String email;

    @Column(name = "usr_naissance")
    private LocalDate dateNaissance;
}
