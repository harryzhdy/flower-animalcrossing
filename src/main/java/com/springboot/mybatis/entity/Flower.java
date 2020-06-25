package com.springboot.mybatis.entity;

public class Flower {
    private double gailv;
    private String color;

    public Flower(double gailv, String color) {
        this.gailv = gailv;
        this.color = color;
    }

    public Flower() {

    }

    public double getGailv() {
        return gailv;
    }

    public void setGailv(double gailv) {
        this.gailv = gailv;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
