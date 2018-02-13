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

        Range copy1 = new Range(getFrom(), getTo());
        copy1.setFrom(this.from);
        copy1.setTo(this.to);
        Range copy2 = new Range(getFrom(), getTo());
        copy2.setFrom(range.from);
        copy2.setTo(range.to);

        if ((copy1.to < copy2.from) || (copy2.to < copy1.from)) {
            return null;
        }
        return new Range(Math.max(copy1.from, copy2.from), Math.min(copy1.to, copy2.to));
    }

    public Range[] getUnion(Range range) {

        Range copy1 = new Range(getFrom(), getTo());
        copy1.setFrom(this.from);
        copy1.setTo(this.to);
        Range copy2 = new Range(getFrom(), getTo());
        copy2.setFrom(range.from);
        copy2.setTo(range.to);

        if (copy1.to < copy2.from) {
            return new Range[]{copy1, copy2};
        } else if (copy2.to < copy1.from) {
            return new Range[]{copy2, copy1};
        }
        return new Range[]{new Range(Math.min(copy1.from, copy2.from), Math.max(copy1.to, copy2.to))};
    }

    public Range[] getDifference(Range range) {

        Range copy1 = new Range(getFrom(), getTo());
        copy1.setFrom(this.from);
        copy1.setTo(this.to);
        Range copy2 = new Range(getFrom(), getTo());
        copy2.setFrom(range.from);
        copy2.setTo(range.to);

        if (copy1.from < copy2.from) {
            if (copy1.to >= copy2.from) {
                if (copy1.to > copy2.to) {
                    return new Range[]{new Range(copy1.from, copy2.from), new Range(copy2.to, copy1.to)};
                }
                return new Range[]{new Range(copy1.from, copy2.from)};
            }
        } else {
            if (copy1.from <= copy2.to) {
                if (copy1.to <= copy2.to) {
                    return new Range[0];
                }
                return new Range[]{new Range(copy2.to, copy1.to)};
            }
        }
        return new Range[]{copy1};
    }
}




















