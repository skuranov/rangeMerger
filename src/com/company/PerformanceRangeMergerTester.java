package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PerformanceRangeMergerTester {

    private final static int testDataSize = 1000;
    private final static int maxRange = 100000;
    private final static int maxRangeGap = 1000;

    public static void main(String[] args) {
        PerformanceRangeMergerTester tester = new PerformanceRangeMergerTester();
        RangeMerger merger = new RangeMerger();
        List<Range> testData = tester.generateTestData();

        double startTime1 = System.nanoTime();
        List<Range> simpleProcessedRanges = merger.processRanges(testData);
        double estimatedTime1 = System.nanoTime() - startTime1;

        Collections.sort(simpleProcessedRanges);
        System.out.println(simpleProcessedRanges);
        System.out.println("Simple sequential algorithm: " + estimatedTime1 + " nanoseconds");

        OptimizedRangeMerger optimizedMerger = new OptimizedRangeMerger();

        long startTime2 = System.nanoTime();
        System.out.println(optimizedMerger.quickSortAndMerge(testData));
        long estimatedTime2 = System.nanoTime() - startTime2;
        System.out.println("Advanced quick-sort based algorithm: " + estimatedTime2 + " nanoseconds");
        System.out.println("Overall performance boost: " + (estimatedTime1 / estimatedTime2) * 100 + " percents");
    }

    private List<Range> generateTestData() {
        Random random = new Random();
        List<Range> outputList = new ArrayList<>();
        for (int i = 0; i < testDataSize; i++) {
            int a = random.nextInt(maxRange);
            int b = a + random.nextInt(maxRangeGap);
            outputList.add(new Range(a, b));
        }
        return outputList;
    }
}
