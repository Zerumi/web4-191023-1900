package io.github.web41910231900.configuration.dsl;

import io.github.web41910231900.auth.session.SessionFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

    private final SessionFilter sessionFilter;

    @Autowired
    public CustomDsl(SessionFilter sessionFilter) {
        this.sessionFilter = sessionFilter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(sessionFilter, UsernamePasswordAuthenticationFilter.class);
    }
}