package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OptimizedRangeMerger {

    private static final List<Range> INITIAL_LIST;

    static {
        INITIAL_LIST = new ArrayList<>();
        INITIAL_LIST.add(new Range(94133, 94133));
        INITIAL_LIST.add(new Range(94200, 94299));
        INITIAL_LIST.add(new Range(94226, 94399));
    }

    public static void main(String[] args) {
        OptimizedRangeMerger merger = new OptimizedRangeMerger();
        System.out.println(merger.quickSortAndMerge(INITIAL_LIST));
    }

    public List<Range> quickSortAndMerge(List<Range> initialList) {
        if (initialList.size() <= 1) {
            return initialList;
        } else {
            Range pivot = initialList.get(initialList.size() / 2);
            List<Range> result = new ArrayList<>();
            Range pivotMergedWithAllPossibleRanges = computeMergedRange(pivot, initialList);
            result.addAll(quickSortAndMerge(initialList.stream().filter(x -> pivotMergedWithAllPossibleRanges.compareTo(x) > 0).collect(Collectors.toList())));
            result.add(pivotMergedWithAllPossibleRanges);
            result.addAll(quickSortAndMerge(initialList.stream().filter(x -> pivotMergedWithAllPossibleRanges.compareTo(x) < 0).collect(Collectors.toList())));
            return result;
        }
    }

    private Range computeMergedRange(Range pivot, List<Range> ranges) {
        Range outputRange;
        List<Range> outputRangeList = new ArrayList<>();
        outputRangeList.add(pivot);
        do {
            outputRange = Range.mergeListOfRanges(outputRangeList);
            outputRangeList.clear();
            outputRangeList.add(outputRange);
            for (Range range : ranges) {
                if (outputRange.compareTo(range) == 0) {
                    outputRangeList.add(range);
                }
            }
            ranges.removeAll(outputRangeList);
        } while (outputRangeList.size() > 1);
        return outputRange;
    }
}
