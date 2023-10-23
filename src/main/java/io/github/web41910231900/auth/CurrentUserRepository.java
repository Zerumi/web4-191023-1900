package io.github.web41910231900.auth;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Deprecated(forRemoval = true)
public class CurrentUserRepository {
    private static final HashMap<String, CurrentUser> users = new HashMap<>();

    @Deprecated
    public CurrentUser findUserByUsername(String username) {
        return users.get(username);
    }

    @PostConstruct
    @Deprecated
    public void setupUsers() {
        users.put("user1",
                CurrentUser.newUser("user1",
                        "$2a$10$4EvCE3wPMBPYEV/FA8B.3e1mrlCGaVuq.cO0x0fmrt198H61q/dFG")
        );
        users.put("user2",
                CurrentUser.newUser("user2",
                        "$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
        );
    }
}
