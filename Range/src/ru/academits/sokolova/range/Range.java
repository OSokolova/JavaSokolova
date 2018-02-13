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
        if ((to <= range.from) || (range.to <= from)) {
            return null;
        }
        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    public Range[] getUnion(Range range) {

        if (to < range.from) {
            Range copy1 = new Range(from, to);
            Range copy2 = new Range(range.from, range.to);
            return new Range[]{copy1, copy2};
        } else if (range.to < from) {
            Range copy1 = new Range(from, to);
            Range copy2 = new Range(range.from, range.to);
            return new Range[]{copy2, copy1};
        }
        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {

        if (from < range.from) {
            if (to >= range.from) {
                if (to > range.to) {
                    return new Range[]{new Range(from, range.from), new Range(range.to, to)};
                }
                return new Range[]{new Range(from, range.from)};
            }
        } else {
            if (from <= range.to) {
                if (to <= range.to) {
                    return new Range[0];
                }
                return new Range[]{new Range(range.to, to)};
            }
        }
        return new Range[]{new Range(from, to)};
    }
}




















