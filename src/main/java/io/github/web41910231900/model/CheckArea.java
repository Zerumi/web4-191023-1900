package io.github.web41910231900.model;

import io.github.web41910231900.model.request.CheckHitRequest;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class CheckArea implements Serializable {
    private CheckHitRequest request;
    private boolean result;
    private LocalDateTime executedAt;
    private long executionTime;

    public CheckArea() {
        super();
    }

    public CheckHitRequest getRequest() {
        return request;
    }

    public void setRequest(CheckHitRequest request) {
        this.request = request;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public LocalDateTime getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = executedAt;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckArea checkArea)) return false;
        return isResult() == checkArea.isResult() && getExecutionTime() == checkArea.getExecutionTime() && Objects.equals(getRequest(), checkArea.getRequest()) && Objects.equals(getExecutedAt(), checkArea.getExecutedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequest(), isResult(), getExecutedAt(), getExecutionTime());
    }

    @Override
    public String toString() {
        return "CheckArea{" +
                "request=" + request +
                ", result=" + result +
                ", executedAt=" + executedAt +
                ", executionTime=" + executionTime +
                '}';
    }
}