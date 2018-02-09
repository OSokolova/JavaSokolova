package ru.academits.sokolova.range;

public class Range {
    private double from;
    private double to;


    public Range() {
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getFrom() {
        return this.from;
    }

    public double getTo() {
        return this.to;
    }

    public double getLength() {
        return getTo() - getFrom();
    }

    public boolean IsInside(double number) {
        return number >= getFrom() && number <= getTo();
    }
}