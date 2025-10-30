package com.example.vtucgpa.model;
import java.util.List;

public class GoodCGPASemesterRequest {
    private int semestersCompleted;
    private List<Double> sgpaList;

    public int getSemestersCompleted() {
        return semestersCompleted;
    }
    public void setSemestersCompleted(int semestersCompleted) {
        this.semestersCompleted = semestersCompleted;
    }
    public List<Double> getSgpaList() {
        return sgpaList;
    }
    public void setSgpaList(List<Double> sgpaList) {
        this.sgpaList = sgpaList;
    }
}

