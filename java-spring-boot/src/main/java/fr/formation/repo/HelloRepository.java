package fr.formation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Hello;

public interface HelloRepository extends JpaRepository<Hello, String> {
    
}
