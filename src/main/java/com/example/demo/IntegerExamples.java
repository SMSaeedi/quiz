package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.*;

class DuplicatedIntArray {
    public static void main(String[] args) {
        int[] addresses = {1, 2, 3, 2, 1, 5, 3, 1, 2, 1, 4, 5, 6};
        int[] uniqueAddresses = findDuplicates(addresses);
        out.println(Arrays.toString(uniqueAddresses));   // Returns [1, 2, 3, 5, 4, 6]
    }

    private static int[] findDuplicates(int[] ads) {
        Set<Integer> set = new HashSet<>();
        for (int s : ads)
            set.add(s);
        out.println(set);

        int i = 0;
        int[] result = new int[set.size()];
        for (int s : set)
            result[i++] = s;

        return result;
    }
}

class FindTopKElements {
    public static void main(String[] args) {
        out.println(FindTopKElements.findTopKElementUsingTreeSet(List.of(1, 2, 3, 3, 4, 4, 5, 6), 2));
    }

    public static List<Integer> findTopKElementUsingTreeSet(List<Integer> lst, int k) {
        Set<Integer> sortedSet = new TreeSet<>(Comparator.reverseOrder());
        sortedSet.addAll(lst);
        return sortedSet.stream().limit(k).collect(Collectors.toList());
    }
}

class FindSecondTopElement {
    public static void main(String[] args) {
        int[] list = {5, 3, 9, 7, 2, 8};
        var result = Arrays.stream(list)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(1) //skip the first element to reach the second one
                .mapToInt(Integer::intValue)// This line can be ignored
                .findFirst();
        result.ifPresentOrElse(out::println, () -> {
            throw new IllegalArgumentException("Arrays too small");
        });
    }
}

class findUniqueInt {
    public static int findUniqueInteger(int[] arr) {
        int unique = 0;
        for (int num : arr)
            unique ^= num;

        return unique;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 2, 4, 1, 3, 2};
        out.println("The unique integer is: " + findUniqueInteger(arr));  // Output should be 1
    }
}

class FindUniqueIntArray {
    public static int[] findUniqueIntegerArray(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr)
            map.put(num, map.getOrDefault(num, 0) + 1);

        List<Integer> uniqueList = new ArrayList<>();
        for (Integer num : arr)
            if (map.get(num) == 1) uniqueList.add(num);

        int[] result = new int[uniqueList.size()];
        int i = 0;
        for (Integer num : uniqueList)
            result[i++] = num;

        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{4, 3, 2, 4, 1, 3, 2, 5, 7, 7, 9, 8, 11};
        out.println("The unique integers are: " + Arrays.toString(findUniqueIntegerArray(arr1)));  // Output should be 1
    }
}

class NumberContiguousList {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 5, 3, 4, 2);
        out.println(isNumberContiguous(numbers) ? "Yes" : "No");

        List<Integer> numbers1 = List.of(1, 3, 5, 4, 6);
        out.println(isNumberContiguous(numbers1) ? "Yes" : "No");
    }

    private static boolean isNumberContiguous(List<Integer> numbers) {
        if (numbers.isEmpty())
            return false;

        var sortedList = numbers.stream().sorted(Comparator.naturalOrder()).toList();

        for (int i = 1; i < sortedList.size() - 1; i++)
            if (sortedList.get(i) - sortedList.get(i - 1) != 1)
                return false;

        return true;
    }

}

class NumberContiguousArray {
    public static void main(String[] args) {
        int[] arr1 = {2, 6, 3, 8, 1, 9, 11, 12};
        out.println(isNumberContinueSequentially(arr1));
        int[] arr2 = {2, 6, 3, 8, 1, 7, 4, 5};
        out.println(isNumberContinueSequentially(arr2));
    }

    private static boolean isNumberContinueSequentially(int[] arr1) {
        if (arr1 == null || arr1.length == 0)
            return false;

        Arrays.sort(arr1);

        for (int i = 1; i < arr1.length - 1; i++)
            if (arr1[i] - arr1[i - 1] != 1)
                return false;

        return true;
    }
}

class AverageTwoArrays {
    public static void main(String[] args) {
        int[][] arr = {{2, 6, 3, 8, 1, 9, 11, 12}, {0, 3, 2, 1, 10, 5}};
        int sum = 0;
        float average = 0;
        int totalElements = 0;

        for (int[] ints : arr)
            for (int anInt : ints) {
                sum += anInt;
                totalElements++;
            }

        average = (float) sum / totalElements;
        out.println("Sum " + sum);
        out.println("Total Elements " + totalElements);
        out.println("Average " + average);
    }
}

class FizzBuzz {
    public static void main(String[] args) {
        for (int i = 1; i <= 15; i++) {
            if (i % 3 == 0 && i % 5 == 0)
                out.println("FizzBuzz");
            else if (i % 3 == 0)
                out.println("Fizz");
            else if (i % 5 == 0)
                out.println("Buzz");
            else
                out.println(i);
        }
    }
}

class CompareTriplets {
    public static void main(String[] args) {
        var list1 = List.of(5, 6, 7);
        var list2 = List.of(3, 6, 10);
        out.println(compareTriplets(list1, list2));
    }

    public static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        List<Integer> result = new ArrayList<>(Arrays.asList(0, 0));

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > b.get(i))
                result.set(0, result.get(0) + 1);
            else if (a.get(i) < b.get(i))
                result.set(1, result.get(1) + 1);
        }
        return result;
    }
}

class DiagonalDifference {
    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        row1.add(2);
        row1.add(3);

        List<Integer> row2 = new ArrayList<>();
        row2.add(4);
        row2.add(5);
        row2.add(6);

        List<Integer> row3 = new ArrayList<>();
        row3.add(9);
        row3.add(8);
        row3.add(9);

        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);
        out.println(diagonalDifference(matrix));
    }

    public static int diagonalDifference(List<List<Integer>> arr) {
        int leftDiagonalSum = 0;
        int rightDiagonalSum = 0;
        int n = arr.size();

        for (int i = 0; i < n; i++) {
            leftDiagonalSum += arr.get(i).get(i);
            rightDiagonalSum += arr.get(i).get(n - i - 1);
        }

        return Math.abs(leftDiagonalSum - rightDiagonalSum);
    }
}

class CalculateRatio {
    public static void main(String[] args) {
        out.println(calculateRatio(List.of(-4, 3, -9, 0, 4, 1)));
    }

    public static List<Double> calculateRatio(List<Integer> arr) {
        int positive = 0;
        int negative = 0;
        int zero = 0;
        int size = arr.size();
        List<Double> result = new ArrayList<>();

        for (int num : arr) {
            if (num > 0)
                positive++;
            else if (num < 0)
                negative++;
            else
                zero++;
        }
        result.add(positive / (double) size);
        result.add(negative / (double) size);
        result.add(zero / (double) size);
        return result;
    }
}

class SumMinAndMax {
    public static void main(String[] args) {
        var list = List.of(256741038, 623958417, 467905213, 714532089, 938071625);
        var allButMinValue = list.stream()
                .sorted(Comparator.reverseOrder())
                .limit(list.size() - 1)
                .mapToLong(Integer::longValue)
                .sum();
        var allButMaxValue = list.stream()
                .sorted(Comparator.naturalOrder())
                .limit(list.size() - 1)
                .mapToLong(Integer::longValue)
                .sum();

        out.println("MaxSum: " + allButMaxValue + ", MinSum: " + allButMinValue);
    }
}

class BirthdayCakeCandles {
    public static void main(String[] args) {
//        var list = List.of(3, 2, 1, 3);
        var list = List.of(2472649, 2472649, 9999907, 328325, 9999907);
        OptionalLong max = list.stream().mapToLong(Integer::longValue).max();
        long count = list.stream().filter(num -> num == max.getAsLong()).count();
        out.println(count);
    }
}

class MultipleListElements {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        out.println(list.stream().reduce(1, Math::multiplyExact));
        out.println(list.stream().reduce(1, (a, b) -> a * b));
    }
}

class MatrixToArray {
    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        row1.add(2);
        row1.add(3);

        List<Integer> row2 = new ArrayList<>();
        row2.add(4);
        row2.add(5);
        row2.add(6);

        List<Integer> row3 = new ArrayList<>();
        row3.add(9);
        row3.add(8);
        row3.add(9);

        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);
        out.println(matrixToArray(matrix));
    }

    private static List<Integer> matrixToArray(List<List<Integer>> matrix) {
        return matrix.stream().flatMap(List::stream).collect(Collectors.toList());
    }
}

class PairSum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9}; // if arr is not sorted, we sort it before the process
        int target = 8;
        Map<Integer, List<Integer>> map = new HashMap<>();

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                List<Integer> pairNr = new ArrayList<>();
                pairNr.add(arr[left]);
                pairNr.add(arr[right]);
                map.put(left, pairNr);
                left++; // Move the left pointer up
                right--; // Move the right pointer down
            } else if (sum < target)
                left++;  // Move the left pointer up to increase the sum
            else
                right--; // Move the right pointer down to decrease the sum
        }

        out.println(map);
    }
}

class FindMaxUsingBinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 2, 1, 7, 6, 5};
        out.println(findMax(nums));  // Output: 7

        int[] nums2 = new int[]{3, 2, 1};
        out.println(findMax(nums2));  // Output: 3

        int[] nums3 = new int[]{6, 5, 4, 3, 2, 1, 7};
        out.println(findMax(nums3));  // Output: 7
    }

    public static int findMax(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[mid + 1]) high = mid;
            else low = mid + 1;
        }
        return nums[low];
    }
}

class FindMaxArrayElement {
    public static void main(String[] args) {
        int[] arr = {1, 9, 8, 6, 5, 3, 7};

        // For Loop o(n)
        long start = nanoTime();
        int max1 = arr[0];
        for (int i : arr)
            if (i > max1)
                max1 = i;
        long end = nanoTime();
        out.println(max1 + ", execution time (ms): " + (end - start) / 1000000);

        // Sort and Fetch the last O(nlogn)
        long start1 = nanoTime();
        Arrays.sort(arr);
        int max2 = arr[arr.length - 1];
        long end1 = nanoTime();
        out.println(max2 + ", execution time (ms): " + (end1 - start1) / 1000000);

        // Stream API o(n)
        long start2 = nanoTime();
        int max3 = Arrays.stream(arr).max().orElse(0);
        long end2 = nanoTime();
        out.println(max3 + ", execution time (ms): " + (end2 - start2) / 1000000);
    }
}

class ProcessInputIntegers {
    public static List<Integer> processInputIntegers(int[] numbers) {
        Map<Integer, Integer> resultMap = new LinkedHashMap<>();
        int currentIndex = 0;

        for (int number : numbers) { // o(n)
            if (number > 100 || number < -100) {
                err.println("Array out of Range");
                return null;
            }

            if (number < 0)
                resultMap.put(currentIndex++, number);
            else if (number > 0) {
                int indexToRemove = number - 1;
                try {
                    resultMap.remove(indexToRemove);
                } catch (Exception e) {
                    err.println(e.getCause());
                    e.printStackTrace();
                }
            }
        }
        return new ArrayList<>(resultMap.values());
    }

    public static void main(String[] args) {
        int[] arr = {-1, -2, -3, 2};
        System.out.println(processInputIntegers(arr));
        int[] arr1 = {-111, -2, -3, 2};
        System.out.println(processInputIntegers(arr1));
    }
}

class FindSmallestPositiveInt {
    public static int solution(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int i : A)
            if (i > 0)
                set.add(i);

        int smallest = 1;
        while (set.contains(smallest))
            smallest++;

        return smallest;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 6, 4, 1, 2};
        out.println(solution(arr1));
        int[] arr2 = {1, 2, 3};
        out.println(solution(arr2));
        int[] arr3 = {-1, -3};
        out.println(solution(arr3));
    }
}

class VisitCounter {
    public static void main(String[] args) {
        // Map1 with valid user IDs and visit counts
        Map<String, UserStats> map1 = new HashMap<>();
        map1.put("1", new UserStats(Optional.of(5L)));
        map1.put("2", new UserStats(Optional.of(10L)));

        // Map2 with overlapping user IDs
        Map<String, UserStats> map2 = new HashMap<>();
        map2.put("1", new UserStats(Optional.of(3L))); // Should be added to the count of userId 1
        map2.put("3", new UserStats(Optional.of(7L))); // New userId, should be added as-is

        // Map3 with null values and invalid user ID
        Map<String, UserStats> map3 = new HashMap<>();
        map3.put("abc", new UserStats(Optional.of(4L))); // Invalid key, should be skipped
        map3.put("2", null); // Null UserStats, should be skipped
        map3.put("4", new UserStats(null)); // Null visit count, should be skipped

        // Map4 null should be skipped
        Map<String, UserStats> nullMap = null;

        Map<Long, Long> result = count(map1, map2, map3, nullMap);

        out.println("Result: " + result);
    }

    @SafeVarargs
    public static Map<Long, Long> count(Map<String, UserStats>... visits) {
        Map<Long, Long> resultMap = new HashMap<>();
        if (visits == null)
            return resultMap;

        for (Map<String, UserStats> visit : visits) {
            if (visit == null)
                continue;

            for (Map.Entry<String, UserStats> entry : visit.entrySet()) {
                try {
                    Long userId = Long.parseLong(entry.getKey());
                    UserStats userStats = entry.getValue();
                    if (userStats == null || userStats.visitCount().isEmpty())
                        break;

                    userStats.visitCount().ifPresent(count -> resultMap.merge(userId, count, Long::sum));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultMap;
    }

    record UserStats(Optional<Long> visitCount) {
    }
}