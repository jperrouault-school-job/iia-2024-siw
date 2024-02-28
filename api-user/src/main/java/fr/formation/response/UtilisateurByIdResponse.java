package fr.formation.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class UtilisateurByIdResponse {
    private String id;
    private String name;
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateNaissance;
}
