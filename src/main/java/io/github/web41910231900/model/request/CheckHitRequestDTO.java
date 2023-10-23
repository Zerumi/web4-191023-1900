package io.github.web41910231900.model.request;

import java.util.Objects;

public class CheckHitRequestDTO {
    private double x;
    private double y;
    private double r;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckHitRequestDTO that)) return false;
        return Double.compare(getX(), that.getX()) == 0 && Double.compare(getY(), that.getY()) == 0 && Double.compare(getR(), that.getR()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getR());
    }

    @Override
    public String toString() {
        return "CheckHitRequest{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                '}';
    }
}
