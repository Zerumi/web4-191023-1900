package io.github.web41910231900.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="result_lab4", schema = "s367837")
public class CheckAreaEntity implements Serializable {
    private long id;
    private UserEntity ownerID;
    private double x;
    private double y;
    private double r;
    private boolean result;
    private LocalDateTime executedAt;
    private long executionTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id")
    public UserEntity getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(UserEntity ownerID) {
        this.ownerID = ownerID;
    }

    @Column
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Column
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Column
    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Column
    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Column(name = "executed_at")
    public LocalDateTime getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = executedAt;
    }

    @Column(name = "execution_time")
    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckAreaEntity that)) return false;
        return getId() == that.getId() && getOwnerID() == that.getOwnerID() && Double.compare(getX(), that.getX()) == 0 && Double.compare(getY(), that.getY()) == 0 && Double.compare(getR(), that.getR()) == 0 && isResult() == that.isResult() && getExecutionTime() == that.getExecutionTime() && Objects.equals(getExecutedAt(), that.getExecutedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOwnerID(), getX(), getY(), getR(), isResult(), getExecutedAt(), getExecutionTime());
    }

    @Override
    public String toString() {
        return "CheckAreaEntity{" +
                "id=" + id +
                ", ownerID=" + ownerID +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", result=" + result +
                ", executedAt=" + executedAt +
                ", executionTime=" + executionTime +
                '}';
    }
}
