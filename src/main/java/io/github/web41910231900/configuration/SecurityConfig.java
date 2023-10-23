package io.github.web41910231900.configuration;

import io.github.web41910231900.auth.session.SessionFilter;
import io.github.web41910231900.configuration.dsl.CustomDsl;
import io.github.web41910231900.service.CurrentUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

import java.util.Objects;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CurrentUserService userService;
    private final SessionFilter sessionFilter;
    private final PasswordEncoder passwordEncoder;
    private final MvcRequestMatcher.Builder mvc;
    private final CustomDsl dsl;

    @Autowired
    public SecurityConfig(CurrentUserService userService,
                          SessionFilter sessionFilter,
                          PasswordEncoder passwordEncoder,
                          MvcRequestMatcher.Builder mvc,
                          CustomDsl dsl) {
        this.userService = userService;
        this.sessionFilter = sessionFilter;
        this.passwordEncoder = passwordEncoder;
        this.mvc = mvc;
        this.dsl = dsl;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable);
        http.exceptionHandling(eh -> eh.authenticationEntryPoint(
                (rq, rs, ex) -> rs.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                        ex.getLocalizedMessage())
        ));
        http.authorizeHttpRequests((ar) -> {
            ar.requestMatchers(mvc.pattern("/login")).permitAll();
            ar.anyRequest().authenticated();
        }).httpBasic(withDefaults());
        http.apply(dsl);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SecurityConfig that)) return false;
        return Objects.equals(userService, that.userService) && Objects.equals(sessionFilter, that.sessionFilter) && Objects.equals(passwordEncoder, that.passwordEncoder) && Objects.equals(mvc, that.mvc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userService, sessionFilter, passwordEncoder, mvc);
    }

    @Override
    public String toString() {
        return "SecurityConfig{" +
                "userService=" + userService +
                ", sessionFilter=" + sessionFilter +
                ", passwordEncoder=" + passwordEncoder +
                ", mvc=" + mvc +
                '}';
    }
}
