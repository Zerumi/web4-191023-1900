package io.github.web41910231900.controller;

import io.github.web41910231900.auth.session.SessionHandler;
import io.github.web41910231900.configuration.AppConfig;
import io.github.web41910231900.model.entity.UserEntity;
import io.github.web41910231900.model.entity.repository.UserRepository;
import io.github.web41910231900.model.request.UserRequestDTO;
import io.github.web41910231900.model.response.AuthSessionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping
public class AuthController {
    private final AuthenticationManager manager;
    private final SessionHandler handler;
    private final UserRepository repository;
    private final AppConfig config;

    @Autowired
    public AuthController(final AuthenticationManager manager,
                          final SessionHandler handler,
                          final UserRepository repository,
                          final AppConfig config) {
        this.manager = manager;
        this.handler = handler;
        this.repository = repository;
        this.config = config;
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<AuthSessionResponseDTO> login(@RequestBody final UserRequestDTO dto) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getPassword()
        ));

        final String sessionID = handler.register(dto.getUsername());
        final AuthSessionResponseDTO responseDTO = new AuthSessionResponseDTO();
        responseDTO.setSessionID(sessionID);
        return ResponseEntity.ok(responseDTO);
    }

    @CrossOrigin
    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication) {
        handler.invalidate(authentication);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<AuthSessionResponseDTO> register(@RequestBody final UserRequestDTO dto) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(config.passwordEncoder().encode(dto.getPassword()));
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);
        try {
            repository.save(newUser);
        } catch (Throwable e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot register user with that username (" + dto.getUsername() + ") " +
                            "( / highly likely it already exists).");
        }
        return login(dto);
    }
}
