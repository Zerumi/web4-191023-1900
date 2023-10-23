package io.github.web41910231900.model.response;

import java.util.Objects;

public class PostResponseDTO {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostResponseDTO that)) return false;
        return Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage());
    }

    @Override
    public String toString() {
        return "PostResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
