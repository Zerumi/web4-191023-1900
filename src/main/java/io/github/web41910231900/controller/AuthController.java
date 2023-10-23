package io.github.web41910231900.controller;

import io.github.web41910231900.auth.session.SessionHandler;
import io.github.web41910231900.model.request.UserRequestDTO;
import io.github.web41910231900.model.response.AuthSessionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping
public class AuthController {

    private final AuthenticationManager manager;

    private final SessionHandler handler;
    @Autowired
    public AuthController(final AuthenticationManager manager,
                          final SessionHandler handler) {
        this.manager = manager;
        this.handler = handler;
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
}
