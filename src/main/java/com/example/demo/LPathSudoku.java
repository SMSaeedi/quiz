package com.example.demo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Enumerates Sudoku solutions subject to minimum sums on four L-shaped paths.
 */
public final class LPathSudoku {
    private static final int SIZE = 9;
    private static final int FULL_MASK = (1 << SIZE) - 1;
    private static final int[][] PATHS = {
            {0, 9, 18, 19},
            {8, 7, 6, 15},
            {72, 73, 74, 65},
            {80, 71, 62, 61}
    };
    private static final int PATH_MINIMUM = 19;

    private static final int[][] PUZZLE = {
            {1, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 0, 8, 0, 0, 0, 0, 6, 0},
            {2, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private final int[] board = new int[SIZE * SIZE];
    private final int[] rowMasks = new int[SIZE];
    private final int[] columnMasks = new int[SIZE];
    private final int[] boxMasks = new int[SIZE];
    private final List<String> solutions = new ArrayList<>();

    private LPathSudoku() {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                int value = PUZZLE[row][column];
                if (value != 0) place(row * SIZE + column, value);
            }
        }
    }

    public static List<String> solve() {
        LPathSudoku solver = new LPathSudoku();
        solver.search();
        solver.solutions.sort(Comparator.naturalOrder());
        return List.copyOf(solver.solutions);
    }

    private void search() {
        int bestCell = -1;
        int bestCandidates = 0;
        int fewest = SIZE + 1;
        for (int cell = 0; cell < board.length; cell++) {
            if (board[cell] != 0) continue;
            int candidates = candidates(cell);
            int count = Integer.bitCount(candidates);
            if (count == 0) return;
            if (count < fewest) {
                fewest = count;
                bestCell = cell;
                bestCandidates = candidates;
            }
        }

        if (bestCell == -1) {
            if (validPaths()) solutions.add(format());
            return;
        }

        for (int bit = bestCandidates; bit != 0; bit &= bit - 1) {
            int value = Integer.numberOfTrailingZeros(bit) + 1;
            place(bestCell, value);
            if (pathsStillPossible()) search();
            remove(bestCell, value);
        }
    }

    private int candidates(int cell) {
        int row = cell / SIZE;
        int column = cell % SIZE;
        int box = (row / 3) * 3 + column / 3;
        return FULL_MASK & ~(rowMasks[row] | columnMasks[column] | boxMasks[box]);
    }

    private void place(int cell, int value) {
        board[cell] = value;
        int bit = 1 << (value - 1);
        int row = cell / SIZE;
        int column = cell % SIZE;
        rowMasks[row] |= bit;
        columnMasks[column] |= bit;
        boxMasks[(row / 3) * 3 + column / 3] |= bit;
    }

    private void remove(int cell, int value) {
        board[cell] = 0;
        int bit = ~(1 << (value - 1));
        int row = cell / SIZE;
        int column = cell % SIZE;
        rowMasks[row] &= bit;
        columnMasks[column] &= bit;
        boxMasks[(row / 3) * 3 + column / 3] &= bit;
    }

    private boolean pathsStillPossible() {
        for (int[] path : PATHS) {
            int sum = 0;
            int missing = 0;
            for (int cell : path)
                if (board[cell] == 0) missing++;
                else sum += board[cell];
            if (sum + missing * 9 < PATH_MINIMUM) return false;
        }
        return true;
    }

    private boolean validPaths() {
        for (int[] path : PATHS) {
            int sum = 0;
            for (int cell : path) sum += board[cell];
            if (sum < PATH_MINIMUM) return false;
        }
        return true;
    }

    private String format() {
        StringBuilder result = new StringBuilder(SIZE * (SIZE + 1));
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++)
                result.append(board[row * SIZE + column]);
            if (row < SIZE - 1)
                result.append('\n');
        }
        return result.toString();
    }

    private static String md5(List<String> solutions) {
        String canonical = String.join("\n\n", solutions);
        try {
            byte[] digest = MessageDigest.getInstance("MD5")
                    .digest(canonical.getBytes(StandardCharsets.UTF_8));
            StringBuilder result = new StringBuilder(32);
            for (byte value : digest)
                result.append(String.format("%02x", value));
            return result.toString();
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("MD5 is unavailable", exception);
        }
    }

    public static void main(String[] args) {
        List<String> solutions = solve();
        if (solutions.isEmpty())
            throw new AssertionError("No Sudoku solutions found");
        String hash = md5(solutions);
        if (solutions.size() != 14 || !"79e8d9a766c43b7069c3e3c8309d209b".equals(hash))
            throw new AssertionError("Unexpected result: count=" + solutions.size() + ", md5=" + hash);
        System.out.println("Count: " + solutions.size());
        System.out.println("MD5:   " + hash);
    }
}
