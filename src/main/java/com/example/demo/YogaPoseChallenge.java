package com.example.demo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Finds the shortest and longest valid yoga classes whose pose values total 108.
 */
public final class YogaPoseChallenge {
    private static final int TARGET = 108;
    private static final int ALL_DIFFICULTIES = 0b111;
    private static final int ALL_TYPES = 0b1111111;

    private static final Map<String, Integer> DIFFICULTY_POINTS = Map.of(
            "beginner", 0,
            "intermediate", 5,
            "advanced", 10);
    private static final Map<String, Integer> TYPE_POINTS = Map.of(
            "sitting", 1,
            "kneeling", 2,
            "standing", 3,
            "reclining", 4,
            "squatting", 5,
            "balancing", 6,
            "inversion", 7);

    private YogaPoseChallenge() {}

    public record Pose(String name, String type, String difficulty, int value,
                       int difficultyMask, int typeMask) {}

    private static final class State {
        private BigInteger count = BigInteger.ZERO;
        private List<Pose> shortest;
        private List<Pose> longest;
    }

    public record Result(List<Pose> shortestClass, List<Pose> longestClass,
                         BigInteger totalSolutions) {}

    public static Result solve() {
        List<Pose> poses = poses();
        @SuppressWarnings("unchecked")
        Map<Integer, State>[] bySum = new Map[TARGET + 1];
        for (int sum = 0; sum <= TARGET; sum++)
            bySum[sum] = new HashMap<>();
        State initial = new State();
        initial.count = BigInteger.ONE;
        initial.shortest = List.of();
        initial.longest = List.of();
        bySum[0].put(0, initial);

        for (Pose pose : poses) {
            for (int sum = TARGET; sum >= pose.value(); sum--) {
                for (Map.Entry<Integer, State> entry : new ArrayList<>(bySum[sum - pose.value()].entrySet())) {
                    int oldKey = entry.getKey();
                    int difficultyMask = oldKey >>> 7;
                    int typeMask = oldKey & ALL_TYPES;
                    int newKey = ((difficultyMask | pose.difficultyMask) << 7)
                            | (typeMask | pose.typeMask);
                    State source = entry.getValue();
                    State target = bySum[sum].computeIfAbsent(newKey, ignored -> new State());
                    target.count = target.count.add(source.count);

                    List<Pose> shortest = append(source.shortest, pose);
                    List<Pose> longest = append(source.longest, pose);
                    if (target.shortest == null || shortest.size() < target.shortest.size())
                        target.shortest = shortest;

                    if (target.longest == null || longest.size() > target.longest.size())
                        target.longest = longest;
                }
            }
        }

        int requiredKey = (ALL_DIFFICULTIES << 7) | ALL_TYPES;
        State result = bySum[TARGET].get(requiredKey);
        if (result == null)
            throw new IllegalStateException("No valid yoga class exists");
        return new Result(result.shortest, result.longest, result.count);
    }

    private static List<Pose> append(List<Pose> poses, Pose pose) {
        List<Pose> result = new ArrayList<>(poses);
        result.add(pose);
        return List.copyOf(result);
    }

    private static List<Pose> poses() {
        String[][] data = {
                {"downward-facing tree", "balancing", "advanced"}, {"shooting bow", "sitting", "advanced"},
                {"crescent moon", "standing", "advanced"}, {"caterpillar", "reclining", "intermediate"},
                {"crane", "balancing", "advanced"}, {"formidable", "reclining", "advanced"},
                {"frog", "reclining", "intermediate"}, {"cobra", "reclining", "beginner"},
                {"cat", "kneeling", "beginner"}, {"bow", "reclining", "intermediate"},
                {"eagle", "standing", "advanced"}, {"cow-faced", "sitting", "intermediate"},
                {"cowherd", "sitting", "beginner"}, {"plough", "inversion", "advanced"},
                {"pigeon", "kneeling", "advanced"}, {"heron", "sitting", "intermediate"},
                {"tortoise", "sitting", "advanced"}, {"crocodile", "reclining", "beginner"},
                {"garland", "squatting", "intermediate"}, {"fish", "reclining", "beginner"},
                {"lord of the fishes", "sitting", "beginner"}, {"lord of the dance", "standing", "intermediate"},
                {"boat", "sitting", "intermediate"}, {"lotus", "sitting", "beginner"},
                {"gate", "standing", "intermediate"}, {"feathered peacock", "balancing", "advanced"},
                {"king pigeon", "sitting", "advanced"}, {"corpse", "reclining", "beginner"},
                {"shoulder supported bridge", "inversion", "intermediate"}, {"lion", "sitting", "beginner"},
                {"headstand", "inversion", "advanced"}, {"sun salutation", "standing", "beginner"},
                {"mountain", "standing", "beginner"}, {"firefly", "balancing", "advanced"},
                {"scales", "balancing", "intermediate"}, {"wheel", "inversion", "intermediate"},
                {"camel", "kneeling", "intermediate"}, {"powerful", "squatting", "intermediate"},
                {"thunderbolt", "kneeling", "beginner"}, {"reversed warrior", "standing", "intermediate"},
                {"warrior I", "standing", "beginner"}, {"hero", "kneeling", "beginner"},
                {"tree", "standing", "intermediate"}, {"scorpion", "inversion", "advanced"},
                {"yogic sleep", "reclining", "advanced"}
        };
        List<Pose> poses = new ArrayList<>();
        for (String[] row : data) {
            int difficultyIndex = List.of("beginner", "intermediate", "advanced").indexOf(row[2]);
            int typeIndex = List.of("sitting", "kneeling", "standing", "reclining",
                    "squatting", "balancing", "inversion").indexOf(row[1]);
            poses.add(new Pose(row[0], row[1], row[2],
                    DIFFICULTY_POINTS.get(row[2]) + TYPE_POINTS.get(row[1]),
                    1 << difficultyIndex, 1 << typeIndex));
        }
        return poses;
    }

    private static int sum(List<Pose> poses) {
        return poses.stream().mapToInt(Pose::value).sum();
    }

    private static void validate(String label, List<Pose> poses) {
        if (sum(poses) != TARGET
                || poses.stream().map(Pose::name).distinct().count() != poses.size()
                || poses.stream().map(Pose::difficulty).distinct().count() != 3
                || poses.stream().map(Pose::type).distinct().count() != 7)
            throw new AssertionError(label + " is invalid: " + poses);
    }

    public static void main(String[] args) {
        Result result = solve();
        validate("30 minute class", result.shortestClass());
        validate("60 minute class", result.longestClass());
        if (result.shortestClass().size() != 9 || result.longestClass().size() != 22)
            throw new AssertionError("Unexpected class length");

        System.out.println("30 minute yoga class (" + result.shortestClass().size() + " poses):");
        result.shortestClass().forEach(pose -> System.out.println(" - " + pose.name() + " (" + pose.value() + ")"));
        System.out.println();
        System.out.println("60 minute yoga class (" + result.longestClass().size() + " poses):");
        result.longestClass().forEach(pose -> System.out.println(" - " + pose.name() + " (" + pose.value() + ")"));
        System.out.println();
        System.out.println("Possible valid solutions: " + result.totalSolutions());
    }
}
