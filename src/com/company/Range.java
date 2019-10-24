package com.company;

import java.util.List;

public class Range implements Comparable {

    private int start;
    private int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "[" + start + "..." + end + "]";
    }

    public static boolean isRangeFallsIntoOther(Range r1, Range r2) {
        return !(r1.start > r2.end || r2.start > r1.end);
    }

    public static Range mergeRange(Range r1, Range r2) {
        return new Range(Math.min(r1.start, r2.start), Math.max(r1.end, r2.end));
    }

    @Override
    public int compareTo(Object o) {
        if (isRangeFallsIntoOther(this, (Range) o)) {
            return 0;
        } else {
            return computeGap(this, (Range) o);
        }
    }

    private int computeGap(Range r1, Range r2) {
        return r1.getEnd() - r2.getStart();
    }

    public static Range mergeListOfRanges(List<Range> rangeList) {
        if (rangeList.size() == 0) {
            return null;
        } else if (rangeList.size() == 1) {
            return rangeList.get(0);
        } else {
            return Range.mergeRange(rangeList.get(0), mergeListOfRanges(rangeList.subList(1, rangeList.size())));
        }
    }
}