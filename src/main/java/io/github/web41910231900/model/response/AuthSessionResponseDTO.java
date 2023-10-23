package io.github.web41910231900.model.response;

import java.util.Objects;

public class AuthSessionResponseDTO {
    private String sessionID;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthSessionResponseDTO that)) return false;
        return Objects.equals(getSessionID(), that.getSessionID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSessionID());
    }

    @Override
    public String toString() {
        return "AuthSessionResponseDTO{" +
                "sessionID='" + sessionID + '\'' +
                '}';
    }
}
