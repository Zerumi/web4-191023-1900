package io.github.web41910231900.service;

import io.github.web41910231900.auth.CurrentUser;
import io.github.web41910231900.auth.CurrentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CurrentUserService implements UserDetailsService {

    private final CurrentUserRepository repository;

    @Autowired
    public CurrentUserService(CurrentUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        final CurrentUser foundUser = repository.findUserByUsername(username);

        if (foundUser == null)
            throw new UsernameNotFoundException("Cannot find user with name " + username);

        return foundUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrentUserService that)) return false;
        return Objects.equals(repository, that.repository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repository);
    }

    @Override
    public String toString() {
        return "CurrentUserService{" +
                "repository=" + repository +
                '}';
    }
}
