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
        if ((to < range.from) || (range.to < from)) {
            return null;
        }
        Range copy1 = new Range(from, to);
        Range copy2 = new Range(range.from, range.to);
        return new Range(Math.max(copy1.from, copy2.from), Math.min(copy1.to, copy2.to));
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
        Range copy1 = new Range(from, to);
        Range copy2 = new Range(range.from, range.to);
        return new Range[]{new Range(Math.min(copy1.from, copy2.from), Math.max(copy1.to, copy2.to))};
    }

    public Range[] getDifference(Range range) {

        if (from < range.from) {
            if (to >= range.from) {
                if (to > range.to) {
                    Range copy1 = new Range(from, to);
                    Range copy2 = new Range(range.from, range.to);
                    return new Range[]{new Range(copy1.from, copy2.from), new Range(copy2.to, copy1.to)};
                }
                Range copy1 = new Range(from, to);
                Range copy2 = new Range(range.from, range.to);
                return new Range[]{new Range(copy1.from, copy2.from)};
            }
        } else {
            if (from <= range.to) {
                if (to <= range.to) {
                    return new Range[0];
                }
                Range copy1 = new Range(from, to);
                Range copy2 = new Range(range.from, range.to);
                return new Range[]{new Range(copy2.to, copy1.to)};
            }
        }
        Range copy1 = new Range(from, to);
        return new Range[]{copy1};
    }
}




















