package fr.formation.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity // Permet de persister les instances
@Table(name = "hello")
@Getter @Setter
public class Hello {
    // @Id // Clé primaire
    // @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incrément
    // private int id;
    
    @Id // Clé primaire
    @UuidGenerator // UUID
    @Column(name = "hel_id")
    private String id;

    private String name;

    private int age;

    private String email;
}
