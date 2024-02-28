package fr.formation.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class UtilisateurResponse {
    private String id;
    private String name;
}
