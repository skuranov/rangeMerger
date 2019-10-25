package com.company;

import java.util.*;
import java.util.stream.Collectors;

import static com.company.Range.*;

/**
 * Simple sequential merger that checks if it's necessary to merge new Range to existing List on adding
 * and if it is, it performs merge.
 * @author Sergei Kuranov
 * @version 1.0
 * @since 1.0
 */

public class RangeMerger {

    private static final List<Range> INITIAL_LIST;

    static {
        INITIAL_LIST = new ArrayList<>();
        INITIAL_LIST.add(new Range(94133, 94133));
        INITIAL_LIST.add(new Range(94200, 94299));
        INITIAL_LIST.add(new Range(94226, 94399));
    }

    public static void main(String[] args) {
        RangeMerger merger = new RangeMerger();
        System.out.println(merger.processRanges(INITIAL_LIST));
    }

    public List<Range> processRanges(List<Range> ranges) {
        List<Range> rangesOutput = new ArrayList<>();
        for (Range range : ranges) {
            combineRanges(rangesOutput, range);
        }
        return rangesOutput;
    }

    private List<Range> combineRanges(List<Range> ranges, Range newRange) {
        List<Range> intersectedRanges = ranges.stream().filter(x -> areRangesIntersected(newRange, x)).collect(Collectors.toList());
        if (intersectedRanges.size() > 0) {
            ranges.removeAll(intersectedRanges);
            intersectedRanges.add(newRange);
            Range combinedRange = mergeListOfRanges(intersectedRanges);
            ranges.add(combinedRange);
        } else {
            ranges.add(newRange);
        }
        return ranges;
    }
}
