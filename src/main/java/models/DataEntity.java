package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="dataEntities")
public class DataEntity implements Serializable {
    @Id
    @Column(nullable = false, unique = true, name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, name = "x")
    private Double x;

    @Column(nullable = false, name = "y")
    private Double y;

    @Column(nullable = false, name = "r")
    private Double r;

    @Column(nullable = false, name = "result")
    private boolean result;

    @Column(nullable = false, name = "currentTime")
    private String currentTime;

    @Column(nullable = false, name = "runningTime")
    private long runningTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public long getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(long runningTime) {
        this.runningTime = runningTime;
    }
}
