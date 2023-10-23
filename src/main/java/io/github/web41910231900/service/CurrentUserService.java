package io.github.web41910231900.service;

import io.github.web41910231900.auth.CurrentUser;
import io.github.web41910231900.model.entity.UserEntity;
import io.github.web41910231900.model.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CurrentUserService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public CurrentUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity foundUser = repository.findByUsername(username);

        if (foundUser == null)
            throw new UsernameNotFoundException("Cannot find user with name " + username);

        return CurrentUser.fromEntity(foundUser);
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
