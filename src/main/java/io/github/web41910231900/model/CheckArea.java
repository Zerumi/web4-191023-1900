package io.github.web41910231900.model;

import io.github.web41910231900.model.entity.CheckAreaEntity;
import io.github.web41910231900.model.request.CheckHitRequestDTO;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;

public class CheckArea implements Serializable {
    private CheckHitRequestDTO request;
    private boolean result;
    private Instant executedAt;
    private long executionTime;

    public CheckArea() {
        super();
    }

    public CheckHitRequestDTO getRequest() {
        return request;
    }

    public void setRequest(CheckHitRequestDTO request) {
        this.request = request;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Instant getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(Instant executedAt) {
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

    public static CheckArea fromEntity(CheckAreaEntity entity) {
        final CheckArea result = new CheckArea();
        final CheckHitRequestDTO requestDTO = new CheckHitRequestDTO();
        requestDTO.setX(entity.getX());
        requestDTO.setY(entity.getY());
        requestDTO.setR(entity.getR());
        result.setRequest(requestDTO);
        result.setResult(entity.isResult());
        result.setExecutedAt(entity.getExecutedAt().atZone(ZoneId.systemDefault()).toInstant());
        result.setExecutionTime(entity.getExecutionTime());
        return result;
    }
}
