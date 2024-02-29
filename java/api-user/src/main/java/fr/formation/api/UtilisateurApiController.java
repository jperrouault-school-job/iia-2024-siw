package fr.formation.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.exception.UtilisateurNotFoundException;
import fr.formation.model.Utilisateur;
import fr.formation.repo.UtilisateurRepository;
import fr.formation.request.AuthRequest;
import fr.formation.request.CreateUtilisateurRequest;
import fr.formation.response.UtilisateurAuthenticatedResponse;
import fr.formation.response.UtilisateurByIdResponse;
import fr.formation.response.UtilisateurResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/utilisateur")
@Log4j2
public class UtilisateurApiController {
    private final UtilisateurRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public List<UtilisateurResponse> findAll() {
        log.debug("Looking for all users ...");

        return this.repository.findAll()
            .stream()
            .map(u -> UtilisateurResponse.builder()
                .id(u.getId())
                .name(u.getUsername())
                .build()
            )
            .toList()
        ;
    }

    @GetMapping("/{id}")
    public UtilisateurByIdResponse findById(@PathVariable String id) {
        log.debug("Finding user by id {} ...", id);

        Utilisateur utilisateur = this.repository.findById(id).orElseThrow(UtilisateurNotFoundException::new);

        return UtilisateurByIdResponse.builder()
            .id(id)
            .name(utilisateur.getUsername())
            .email(utilisateur.getEmail())
            .dateNaissance(utilisateur.getDateNaissance())
            .build()
        ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Valid @RequestBody CreateUtilisateurRequest request) {
        log.debug("Creating a new user ...");
        
        if (this.repository.findByUsername(request.getName()).isPresent()) {
            log.debug("User with username already exists! Returning fake id ...");

            return UUID.randomUUID().toString();
        }

        Utilisateur utilisateur = new Utilisateur();

        BeanUtils.copyProperties(request, utilisateur);

        utilisateur.setUsername(request.getName());
        utilisateur.setPassword(this.passwordEncoder.encode(request.getPassword()));

        return this.repository.save(utilisateur).getId();
    }

    @PostMapping("/auth")
    public UtilisateurAuthenticatedResponse auth(@Valid @RequestBody AuthRequest request) {
        try {
            log.debug("Trying to authenticate ...");
    
            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
    
            log.debug("Successfuly authenticated!");
    
            return UtilisateurAuthenticatedResponse.builder()
                .success(true)
                .build();
        }
        
        catch (Exception ex) {
            log.error("Can't authenticate : bad credentials.");
    
            return UtilisateurAuthenticatedResponse.builder()
                .success(false)
                .build();
        }
    }

    @PutMapping("/{id}")
    public String editById(@PathVariable String id, @Valid @RequestBody CreateUtilisateurRequest request) {
        log.debug("Updating user {} ...", id);

        Utilisateur utilisateur = this.repository.findById(id).orElseThrow(UtilisateurNotFoundException::new);

        BeanUtils.copyProperties(request, utilisateur);

        utilisateur.setUsername(request.getName());
        utilisateur.setPassword(this.passwordEncoder.encode(request.getPassword()));

        return this.repository.save(utilisateur).getId();
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable String id) {
        try {
            log.debug("Deleting user {} ...", id);

            this.repository.deleteById(id);
            return true;
        }

        catch (Exception ex) {
            return false;
        }
    }
}
