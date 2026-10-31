package com.example.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Calculates the values required by the number-statistics challenge.
 */
public final class NumberStatisticsChallenge {
    private NumberStatisticsChallenge() {}

    public record Result(int mean, int median, int mode, int oddSum, int evenSum,
                         int[] digitFrequencies, String finalNumber) {}

    public static Result calculate(int... values) {
        if (values == null || values.length == 0)
            throw new IllegalArgumentException("At least one number is required");

        int[] sorted = Arrays.copyOf(values, values.length);
        Arrays.sort(sorted);

        long total = 0;
        for (int value : values) {
            if (value < 0)
                throw new IllegalArgumentException("Numbers must be positive whole numbers");
            total += value;
        }

        int mean = round(total, values.length);
        int median = values.length % 2 == 1
                ? sorted[values.length / 2]
                : round((long) sorted[values.length / 2 - 1] + sorted[values.length / 2], 2);

        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int value : values)
            frequencies.merge(value, 1, Integer::sum);

        int highestFrequency = frequencies.values().stream().mapToInt(Integer::intValue).max().orElse(0);
        int mode = highestFrequency == 1
                ? 0
                : round(frequencies.entrySet().stream()
                        .filter(entry -> entry.getValue() == highestFrequency)
                        .mapToLong(Map.Entry::getKey)
                        .sum(),
                (int) frequencies.values().stream()
                        .filter(frequency -> frequency == highestFrequency)
                        .count());

        int[] digitFrequencies = new int[10];
        for (int value : values)
            countDigits(value, digitFrequencies);

        int oddSum = digitFrequencies[1] + digitFrequencies[3] + digitFrequencies[5]
                + digitFrequencies[7] + digitFrequencies[9];
        int evenSum = digitFrequencies[0] + digitFrequencies[2] + digitFrequencies[4]
                + digitFrequencies[6] + digitFrequencies[8];

        String finalNumber = String.valueOf(mean) + median + mode + oddSum + evenSum;
        return new Result(mean, median, mode, oddSum, evenSum, digitFrequencies, finalNumber);
    }

    public static int[] parse(String commaSeparatedValues) {
        if (commaSeparatedValues == null || commaSeparatedValues.isBlank())
            throw new IllegalArgumentException("A comma-separated list is required");
        String[] tokens = commaSeparatedValues.split(",");
        int[] values = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++)
            values[i] = Integer.parseInt(tokens[i].trim());
        return values;
    }

    private static void countDigits(int value, int[] frequencies) {
        if (value == 0) {
            frequencies[0]++;
            return;
        }
        while (value > 0) {
            frequencies[value % 10]++;
            value /= 10;
        }
    }

    private static int round(long numerator, int denominator) {
        return (int) Math.floor((numerator / (double) denominator) + 0.5);
    }

    private static void expect(String description, String expected, String actual) {
        if (!expected.equals(actual))
            throw new AssertionError(description + ": expected " + expected + ", got " + actual);
    }

    public static void main(String[] args) {
        // Test scenarios requested by the challenge.
        Result sample = calculate(7, 12, 14, 5, 7, 3);
        expect("sample mean", "8", String.valueOf(sample.mean()));
        expect("sample median", "7", String.valueOf(sample.median()));
        expect("sample mode", "7", String.valueOf(sample.mode()));

        Result evenMedian = calculate(1, 2, 3, 4);
        expect("even median rounds .5 upward", "3", String.valueOf(evenMedian.median()));

        Result tiedMode = calculate(19, 19, 41, 41, 85, 85);
        expect("tied modes round their mean", "48", String.valueOf(tiedMode.mode()));

        Result noMode = calculate(10, 20, 30);
        expect("no repeated number has mode zero", "0", String.valueOf(noMode.mode()));

        Result digitScenario = calculate(10, 235, 1047);
        expect("digit frequencies", "[2, 2, 1, 1, 1, 1, 0, 1, 0, 0]",
                Arrays.toString(digitScenario.digitFrequencies()));
        expect("digit sums", "5/4", digitScenario.oddSum() + "/" + digitScenario.evenSum());

        System.out.println("All test scenarios passed.");
        if (args.length > 0) {
            Result result = calculate(parse(String.join(",", args)));
            System.out.println("Mean: " + result.mean());
            System.out.println("Median: " + result.median());
            System.out.println("Mode: " + result.mode());
            System.out.println("Digit frequencies: " + Arrays.toString(result.digitFrequencies()));
            System.out.println("Odd sum: " + result.oddSum());
            System.out.println("Even sum: " + result.evenSum());
            System.out.println("Final number: " + result.finalNumber());
        }
    }
}
