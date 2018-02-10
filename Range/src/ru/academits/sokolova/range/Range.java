package ru.academits.sokolova.range;

public class Range {
    private double from;
    private double to;


    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getCross(Range range) {
        if ((this.to < range.getFrom()) || (range.getTo() < this.from)) {
            return null;
        } else if (isInside(range.getFrom()) && isInside(range.getTo())) {
            return range;
        }
        return new Range(range.getFrom(), this.to);
    }

    public Range[] getUnion(Range range) {
        Range[] array;
        if (getCross(range) == null) {
            array = new Range[2];
            array[0] = new Range(from, to);
            array[1] = range;
        } else if (getCross(range) == range) {
            array = new Range[1];
            array[0] = range;
        } else {
            array = new Range[1];
            array[0] = new Range(range.getFrom(), to);
        }
        return array;
    }

    public Range[] getDifference(Range range) {
        Range[] array;
        if (getCross(range) == null) {
            array = new Range[1];
            array[0] = new Range(from, to);
        } else if (getCross(range) == range) {
            array = new Range[2];
            array[0] = new Range(from, range.getFrom());
            array[1] = new Range(range.getTo(), to);
        } else {
            array = new Range[1];
            array[0] = new Range(from, range.getFrom());
        }
        return array;
    }
}

