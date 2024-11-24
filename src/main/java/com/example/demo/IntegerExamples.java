package com.example.demo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.*;

class RemoveDuplicatedElementsInArray {
    public static void main(String[] args) {
        int[] addresses = {1, 2, 3, 2, 1, 5, 3, 1, 2, 1, 4, 5, 6};
        out.println(Arrays.toString(removeDuplicates(addresses)));   // Returns [1, 2, 3, 5, 4, 6]
        out.println(removeDuplicatesUsingStreamAPI(addresses));   // Returns [1, 2, 3, 5, 4, 6]
    }

    private static int[] removeDuplicates(int[] ads) {
        Set<Integer> set = new HashSet<>();
        for (int s : ads) // O(n)
            set.add(s);

        int i = 0;
        int[] result = new int[set.size()];
        for (int s : set) // O(n)
            result[i++] = s;

        return result;
    }

    private static List<Integer> removeDuplicatesUsingStreamAPI(int[] ads) {
        return Arrays.stream(ads).distinct().boxed() // Convert int to Integer
                .collect(Collectors.toList());
    }
}

class FindDuplicatedElementsInArray {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 2, 1, 5, 3, 1, 2, 1, 4, 5, 6};
        out.println(findFirstDuplicate(arr1));
        out.println(findFirstDuplicateUsingStreamAPI(arr1));
        out.println(Arrays.toString(findDuplicates(arr1)));   // Returns [1, 2, 3, 5]
        out.println(Arrays.toString(findDuplicateElementsUsingSet(arr1)));   // Returns [1, 2, 3, 5]
        out.println(findDuplicateElementsUsingMap(arr1));
        out.println(findDuplicateElementsUsingStreamAPI(arr1));
    }

    static int[] findDuplicates(int[] ads) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i : ads) // O(n)
            map.put(i, map.getOrDefault(i, 0) + 1);
        out.println(map);

        long duplicatedElementsSize = map.entrySet().stream().filter(entry -> entry.getValue() > 1).count(); // O(n)
        int i = 0;
        int[] result = new int[(int) duplicatedElementsSize];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) // O(k)
            if (entry.getValue() != 1) result[i++] = entry.getKey();

        return result;
    }

    static int[] findDuplicateElementsUsingSet(int[] ads) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();

        for (int i : ads)
            if (!set.add(i)) list.add(i);

        int[] result = new int[list.size()];
        int i = 0;
        for (int num : list)
            result[i++] = num;

        return result;
    }

    static Map<Integer, Integer> findDuplicateElementsUsingMap(int[] ads) {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        for (int i : ads) // O(n)
            map.put(i, map.getOrDefault(i, 0) + 1);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) // O(k)
            if (entry.getValue() == 1) map.remove(entry.getKey());

        return map;
    }

    static Set<Integer> findDuplicateElementsUsingStreamAPI(int[] ads) {
        return Arrays.stream(ads).boxed().collect(Collectors.groupingBy(i -> i, Collectors.counting())) // Group by element and count
                .entrySet().stream().filter(entry -> entry.getValue() > 1) // Filter entries with count > 1
                .map(Map.Entry::getKey) // Extract keys (duplicates)
                .collect(Collectors.toSet()); // Collect to a Set to avoid duplicates
    }

    static int findFirstDuplicate(int[] ads) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(ads);
        for (int i : ads)
            if (!set.add(i)) return i;

        return 0;
    }

    static int findFirstDuplicateUsingStreamAPI(int[] ads) {
        return Arrays.stream(ads).distinct().findFirst().orElse(0);
    }
}

class FindTopKElements {
    public static void main(String[] args) {
        out.println(findTopKElementUsingTreeSet(List.of(1, 2, 3, 3, 4, 4, 5, 6), 2));
        out.println(findTopKElementUsingStream(List.of(9, 2, 5, 6, 11), 2));
    }

    public static List<Integer> findTopKElementUsingTreeSet(List<Integer> lst, int k) {
        Set<Integer> sortedSet = new TreeSet<>(Comparator.reverseOrder()); // O(1)
        sortedSet.addAll(lst); // O(nLogn)
        return sortedSet.stream().limit(k).collect(Collectors.toList()); // O(k)
    }

    public static List<Integer> findTopKElementUsingStream(List<Integer> lst, int k) {
        return lst.stream().sorted(Comparator.reverseOrder()) // O(nLogn)
                .limit(k) // O(k)
                .collect(Collectors.toList()); // O(k)
    }
}

class FindSecondTopElement {
    public static void main(String[] args) {
        int[] list = {5, 3, 9, 7, 2, 8};
        out.println(secondTopElementUsingStreamAPI(list));
        out.println(secondTopElementUsingArraysSort(list));
    }

    private static int secondTopElementUsingStreamAPI(int[] list) {
        return Arrays.stream(list).boxed() // O(n), converts each primitive int in the array to an Integer object
                .sorted(Comparator.reverseOrder()) // O(nLogn)
                .skip(1) // O(1), skip the first element to reach the second one
                .mapToInt(Integer::intValue) // can be ignored, for the return type is primitive int
                .findFirst().orElse(0); // O(1)
    }

    private static int secondTopElementUsingArraysSort(int[] list) {
        Arrays.sort(list); //O(nlogn)
        return list[list.length - 2];
    }
}

class FindTheOnlyUniqueElement {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 2, 4, 1, 3, 2};
        out.println("The unique integer is: " + findTheOnlyUniqueElement(arr));  // Output should be 1
        out.println("The unique integer, using Map: " + findTheOnlyUniqueElementUsingMap(arr));  // Output should be 1
        out.println("The unique integer, using Stream API: " + findTheOnlyUniqueElementStreamAPI(arr));  // Output should be 1
    }

    public static int findTheOnlyUniqueElement(int[] arr) {
        int unique = 0;
        // O(n), XOR (^) helps identifying a unique element in an array where every other element is repeated
        for (int num : arr)
            unique ^= num;

        return unique;
    }

    public static int findTheOnlyUniqueElementUsingMap(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) //O(n)
            map.put(i, map.getOrDefault(i, 0) + 1);

        return map.keySet().stream().filter(k -> map.get(k) == 1).findFirst().orElse(0); // O(n)
    }

    public static int findTheOnlyUniqueElementStreamAPI(int[] arr) {
        return Arrays.stream(arr).boxed().collect(Collectors.groupingBy(o -> o, Collectors.counting())).entrySet().stream().filter(i -> i.getValue() == 1).map(Map.Entry::getKey).findFirst().orElseThrow(() -> new RuntimeException("No duplicated element found!"));
    }
}

class FindUniqueElements {
    public static void main(String[] args) {
        int[] arr1 = new int[]{4, 3, 2, 4, 1, 3, 2, 5, 7, 7, 9, 8, 11};
        out.println("The unique integers using map: " + Arrays.toString(findUniqueElementsUsingMap(arr1)));
        out.println("The unique integers Using Stream API: " + findUniqueElementsUsingStreamAPI(arr1));
    }

    public static int[] findUniqueElementsUsingMap(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) //O(n)
            map.put(num, map.getOrDefault(num, 0) + 1);

        var uniqueElementsCount = map.keySet().stream().filter(e -> map.get(e) == 1).count(); // O(n)
        int[] result = new int[(int) uniqueElementsCount];
        int i = 0;
        for (int num : map.keySet()) // O(n)
            if (map.get(num) == 1) result[i++] = num;

        return result;
    }

    public static List<Integer> findUniqueElementsUsingStreamAPI(int[] arr) {
        return Arrays.stream(arr).boxed().collect(Collectors.groupingBy(e -> e, Collectors.counting())).entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).collect(Collectors.toList());
    }
}

class NumberContiguousList {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 5, 3, 4, 2);
        out.println(isNumberContiguous(numbers) ? "Yes" : "No");
        List<Integer> numbers1 = List.of(1, 3, 5, 4, 6);
        out.println(isNumberContiguous(numbers1) ? "Yes" : "No");
        out.println("-------------------------------------");
        List<Integer> numbers2 = List.of(1, 5, 3, 4, 2);
        out.println(isNumberContiguousUsingIntStream(numbers2) ? "Yes" : "No");
        List<Integer> numbers3 = List.of(1, 3, 5, 4, 6);
        out.println(isNumberContiguousUsingIntStream(numbers3) ? "Yes" : "No");
    }

    private static boolean isNumberContiguous(List<Integer> numbers) {
        if (numbers.isEmpty()) // O(1)
            return false;

        var sortedList = numbers.stream().sorted(Comparator.naturalOrder()).toList(); // O(nLogn)

        for (int i = 1; i < sortedList.size() - 1; i++) // O(n)
            if (sortedList.get(i) - sortedList.get(i - 1) != 1) return false;

        return true;
    }

    private static boolean isNumberContiguousUsingIntStream(List<Integer> numbers) {
        List<Integer> sortedList = numbers.stream().sorted().toList();
        return IntStream.range(0, sortedList.size() - 1).allMatch(v -> sortedList.get(v + 1) - sortedList.get(v) == 1);
    }
}

class NumberContiguousArray {
    public static void main(String[] args) {
        int[] arr1 = {2, 6, 3, 8, 1, 9, 11, 12};
        out.println(isNumberContinue(arr1));
        int[] arr2 = {2, 6, 3, 8, 1, 7, 4, 5};
        out.println(isNumberContinue(arr2));
        out.println("-------------------------------------");
        int[] arr3 = {2, 6, 3, 8, 1, 9, 11, 12};
        out.println(isNumberContinueUsingSet(arr3));
        int[] arr4 = {2, 6, 3, 8, 1, 7, 4, 5};
        out.println(isNumberContinueUsingSet(arr4));
        out.println("-------------------------------------");
        int[] arr5 = {2, 6, 3, 8, 1, 9, 11, 12};
        out.println(isNumberContinueUsingIntStream(arr5));
        int[] arr6 = {2, 6, 3, 8, 1, 7, 4, 5};
        out.println(isNumberContinueUsingIntStream(arr6));
    }

    static boolean isNumberContinue(int[] arr1) {
        if (arr1 == null || arr1.length == 0) return false;

        Arrays.sort(arr1); // O(nLogn)

        for (int i = 1; i < arr1.length - 1; i++) // O(n)
            if (arr1[i] - arr1[i - 1] != 1) // O(1)
                return false;

        return true;
    }

    static boolean isNumberContinueUsingSet(int[] arr1) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i : arr1)
            set.add(i);

        int smallest = 1;
        while (set.contains(smallest)) smallest++;

        return smallest >= arr1.length;
    }

    static boolean isNumberContinueUsingIntStream(int[] arr1) {
        Arrays.sort(arr1); //O(nlogn)
        return IntStream.range(0, arr1.length - 1).allMatch(e -> arr1[e + 1] - arr1[e] == 1);
    }
}

class MatrixAverage {
    public static void main(String[] args) {
        int[][] arr = {{2, 6, 3, 8, 1, 9, 11, 12}, {0, 3, 2, 1, 10, 5}};
        out.println("Average array " + averageArray(arr));
        out.println("-------------------------------------");
        List<List<Integer>> matrix = Arrays.asList(Arrays.asList(2, 6, 3, 8, 1, 9, 11, 12), Arrays.asList(0, 3, 2, 1, 10, 5));
        out.println("Average list<list<>> " + averageList(matrix));
        out.println("Average list<list<>> using Stream API " + averageListUsingStreamAPI(matrix));
    }

    static float averageArray(int[][] arr) {
        int sum = 0;
        float average = 0;
        int elementCount = 0;

        for (int[] ints : arr) //O(n)
            for (int anInt : ints) { //O(n)
                sum += anInt;
                elementCount++;
            }

        average = (float) sum / elementCount;
        out.println("Sum " + sum);
        out.println("Total Elements " + elementCount);

        return average;
    }

    static float averageList(List<List<Integer>> matrix) {
        int sum = 0;
        float average = 0;

        var flatArray = matrix.stream().flatMap(List::stream).toList(); // O(n)

        for (int anInt : flatArray) //O(n)
            sum += anInt;

        average = (float) sum / flatArray.size();
        out.println("Sum " + sum);
        out.println("Total Elements " + flatArray.size());

        return average;
    }

    static float averageListUsingStreamAPI(List<List<Integer>> matrix) {
        List<Integer> flatList = matrix.stream().flatMap(List::stream).toList();
        return (float) flatList.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }
}

class FizzBuzz {
    public static void main(String[] args) {
        for (int i = 1; i <= 15; i++) {
            if (i % 3 == 0 && i % 5 == 0) out.println("FizzBuzz");
            else if (i % 3 == 0) out.println("Fizz");
            else if (i % 5 == 0) out.println("Buzz");
            else out.println(i);
        }
    }
}

class CompareTriplets {
    public static void main(String[] args) {
        var a = List.of(5, 6, 7);
        var b = List.of(3, 6, 10);
        out.println(compareTriplets(a, b));
    }

    public static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        List<Integer> result = new ArrayList<>(Arrays.asList(0, 0));

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > b.get(i)) result.set(0, result.get(0) + 1);
            else if (a.get(i) < b.get(i)) result.set(1, result.get(1) + 1);
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

    public static List<Float> calculateRatio(List<Integer> arr) {
        int positive = 0;
        int negative = 0;
        int zero = 0;
        int size = arr.size();
        List<Float> result = new ArrayList<>();

        for (int num : arr) {
            if (num > 0) positive++;
            else if (num < 0) negative++;
            else zero++;
        }

        result.add(positive / (float) size);
        result.add(negative / (float) size);
        result.add(zero / (float) size);
        return result;
    }
}

class SumMinAndMax {
    public static void main(String[] args) {
        var list = List.of(256, 64, 1024, 32, 4);
        var allButMinValue = list
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(list.size() - 1)
                .mapToLong(Integer::longValue)
                .sum();
        var allButMaxValue = list
                .stream()
                .sorted(Comparator.naturalOrder())
                .limit(list.size() - 1)
                .mapToLong(Integer::longValue)
                .sum();
        out.println("MaxSum: " + allButMaxValue + ", MinSum: " + allButMinValue);
    }
}

class BirthdayCakeCandles {
    public static void main(String[] args) {
        var list = List.of(3, 2, 1, 3);
        OptionalLong max = list.stream().mapToLong(Integer::longValue).max();
        long count = list.stream().filter(num -> num == max.getAsLong()).count();
        out.println(count);
    }
}

class Multiply {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        // .reduce: the operation of reduction on element to have a single result by repeatedly applying binary operation
        out.println("Multiply1 stream API: " + list.stream().reduce(1, Math::multiplyExact));
        out.println("Multiply2 stream API: " + list.stream().reduce(1, (a, b) -> a * b));
        out.println("Multiply: " + multiply(list));
    }

    static float multiply(List<Integer> list) {
        float multiplier = 1;
        for (int i : list)
            multiplier *= i;

        return multiplier;
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
        out.println(matrixToArrayStreamAPI(matrix));
        out.println(matrixToArray(matrix));
    }

    private static List<Integer> matrixToArrayStreamAPI(List<List<Integer>> matrix) {
        // .flatMap() combines multiple collections into a stream
        return matrix.stream().flatMap(List::stream).collect(Collectors.toList()); // O(n)
    }

    private static List<Integer> matrixToArray(List<List<Integer>> matrix) {
        List<Integer> result = new ArrayList<>();

        for (List<Integer> integers : matrix) // O(n)
            result.addAll(integers);

        return result;
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
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[mid + 1]) right = mid;
            else left = mid + 1;
        }
        return nums[left];
    }
}

class FindMaxElementArray {
    public static void main(String[] args) {
        int[] arr = {1, 9, 8, 6, 5, 3, 7};

        long start = nanoTime();
        int max1 = arr[0];
        for (int i : arr) //O(n)
            if (i > max1) max1 = i;
        long end = nanoTime();
        out.println(max1 + ", execution time (ms): " + (end - start) / 1000000);

        long start1 = nanoTime();
        Arrays.sort(arr); // O(nlogn)
        int max2 = arr[arr.length - 1];
        long end1 = nanoTime();
        out.println(max2 + ", execution time (ms): " + (end1 - start1) / 1000000);

        long start2 = nanoTime();
        int max3 = Arrays.stream(arr).max().orElse(0); // O(n)
        long end2 = nanoTime();
        out.println(max3 + ", execution time (ms): " + (end2 - start2) / 1000000);
    }
}

class FindFirstMissingPositive {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 6, 4, 1, 2};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = {-1, -3};
        out.println(firstMissingPositiveElement(arr1) + ", " +
                firstMissingPositiveElement(arr2) + ", " +
                firstMissingPositiveElement(arr3));
        out.println(firstMissingPositiveElementUsingStreamAPI(arr1) + ", " +
                firstMissingPositiveElementUsingStreamAPI(arr2) + ", " +
                firstMissingPositiveElementUsingStreamAPI(arr3));
    }

    public static int firstMissingPositiveElement(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int i : A) //O(n)
            if (i > 0) set.add(i);

        int smallest = 1; // smallest positive integers start from 1 and go upward: 1, 2, 3, 4, 5, ....
        while (set.contains(smallest)) //O(n)
            smallest++;

        return smallest;
    }

    public static int firstMissingPositiveElementUsingStreamAPI(int[] A) {
        /**
         * IntStream.range(startInclusive, endExclusive)
         *      e.g. IntStream.range(1, 5).forEach(System.out::print); // Output: 1234
         * IntStream.rangeClosed(startInclusive, endInclusive)
         *      e.g. IntStream.rangeClosed(1, 5).forEach(System.out::print); // Output: 12345
         */
        return IntStream.rangeClosed(1, A.length + 1)
                .filter(value -> !Arrays.stream(A)
                        .boxed()
                        .collect(Collectors.toSet())
                        .contains(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No missing positive num found!"));
    }
}

class LargestNumberTwiceSize {
    public static void main(String[] args) {
        int[] arr1 = {3, 2};
        int[] arr2 = {3, 2, 1};
        out.println(largestNumberTwiceSize(arr1) + ", " + largestNumberTwiceSize(arr2));
    }

    static int largestNumberTwiceSize(int[] arr) {
        Arrays.sort(arr); // O(nLogn)
        int max = arr[arr.length - 1];
        int counter = 0;
        for (int i : arr) // O(n)
            if (i * i <= max) counter++;
        return counter > 0 ? max : -1;
    }
}

class PairSum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9}; // if arr is not sorted, we sort it before the process
        int[] arr2 = {9, 1, 2, 8, 7, 4, 3, 4, 6, 5, 4};
        out.println("pairSum: " + pairSum(arr, 8) + ", " + pairSum(arr, 9));
        out.println("pairSumUsingSet: " + pairSumUsingSet(arr2, 8) + ", " + pairSumUsingSet(arr, 9));
        out.println("pairSumUsingStreamAPI: " + pairSumUsingStreamAPI(arr2, 8) + ", " + pairSumUsingStreamAPI(arr, 9));
    }

    static boolean pairSumUsingSet(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int count = 0;
        for (int i : arr) { // O(n)
            if (set.contains(target - i)) {
                count++;
                List<Integer> pairNr = new ArrayList<>();
                pairNr.add(i);
                pairNr.add(target - i);
                map.put(count, pairNr);
            }
            set.add(i); // O(1)
        }
        return count > 0;
    }

    static List<List<Integer>> pairSumUsingStreamAPI(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();
        return Arrays.stream(arr)
                .boxed() // Convert primitive int to Integer
                .flatMap(num -> { // use flatMap to generate pairs.
                    int complement = target - num;
                    if (set.contains(complement))
                        return Stream.of(List.of(complement, num));

                    set.add(num);
                    return Stream.empty(); // Return an empty Stream if no pair is found
                })
                .collect(Collectors.toList());
    }

    static Map<Integer, List<Integer>> pairSum(int[] arr, int target) {
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
            } else if (sum < target) left++;  // Move the left pointer up
            else if (sum > target) right--; // Move the right pointer down
        }

        return map;
    }
}

class ZeroSumTriplets {
    public static void main(String[] args) {
        int[] arr = {0, -2, 7, 2, 4, -6};
        zeroSumTriplets(arr);
    }

    static void zeroSumTriplets(int[] arr) {
        Arrays.sort(arr); // O(nLogn)

        for (int i = 0; i < arr.length - 2; i++) { // O(n)
            int min = i + 1;
            int max = arr.length - 1;
            int target = -arr[i];

            while (min < max) { // O(n)
                int sum = arr[min] + arr[max];

                if (sum == target) {
                    System.out.println(arr[i] + ", " + arr[min] + ", " + arr[max]);
                    min++;
                    max--;
                } else if (sum < target) {
                    min++;
                } else {
                    max--;
                }
            }
        }
    }
}

class SubArrayWithGivenSum {
    public static void main(String[] args) {
        int[] arr = {10, 3, 5, 8, 6, 12, 20, 15, 31};
        out.println(subArrayWithGivenSum(arr, arr.length, 31));
    }

    static List<List<Integer>> subArrayWithGivenSum(int[] arr, int length, int targetSum) {
        List<List<Integer>> subArrays = new ArrayList<>();

        for (int start = 0; start < length; start++) {
            int sum = 0;
            List<Integer> list = new ArrayList<>();

            for (int end = start + 1; end < length; end++) {
                sum += arr[end];
                list.add(arr[end]);

                if (sum == targetSum) subArrays.add(new ArrayList<>(list));
            }
        }
        return subArrays;
    }
}