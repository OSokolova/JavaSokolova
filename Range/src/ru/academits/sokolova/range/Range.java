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
        if (this.from < range.from) {
            if (isInside(range.from)) {
                if (isInside(range.to)) {
                    return range;
                }
                return new Range(range.from, this.to);
            }
            return null;
        } else {
            if (range.isInside(from)) {
                if (range.isInside(to)) {
                    return this;
                }
                return new Range(from, range.to);
            }
        }
        return null;
    }

    public Range[] getUnion(Range range) {
        if (this.from < range.from) {
            if (isInside(range.from)) {
                if (isInside(range.to)) {
                    return new Range[]{this};
                }
                return new Range[]{new Range(from, range.to)};
            }
            return new Range[]{this, range};
        } else {
            if (range.isInside(from)) {
                if (range.isInside(to)) {
                    return new Range[]{range};
                }
                return new Range[]{new Range(range.from, to)};
            }
        }
        return new Range[]{range, this};
    }

    public Range[] getDifference(Range range) {
        if (this.from < range.from) {
            if (isInside(range.from)) {
                if (isInside(range.to)) {
                    return new Range[]{new Range(from, range.from), new Range(range.to, to)};
                }
                return new Range[]{new Range(from, range.from)};
            }
        } else {
            if (range.isInside(from)) {
                if (range.isInside(to)) {
                    return null;
                }
                return new Range[]{new Range(range.to, to)};
            }
        }
        return new Range[]{this};
    }
}


















