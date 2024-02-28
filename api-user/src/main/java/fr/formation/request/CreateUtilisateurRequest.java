package fr.formation.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUtilisateurRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String password;
    
    @NotBlank
    @Email
    private String email;
    
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateNaissance;
}
