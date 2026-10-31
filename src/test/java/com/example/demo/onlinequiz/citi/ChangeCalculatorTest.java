package com.example.demo.onlinequiz.citi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

class ChangeCalculatorTest {


    @Test
    void calculateChangeForPurchaseWithNonExactChange() {
        Set<Integer> coins = new HashSet<>();

        coins.add(1);

        coins.add(2);

        coins.add(5);

        coins.add(10);

        coins.add(20);

        coins.add(50);

        coins.add(100);

        Map<Integer, Integer> changeGiven =
                ChangeCalculatorJava.calculateMinCoinsForChange(new BigDecimal("1.48"),
                Arrays.asList(100, 100), coins);

        Map<Integer, Integer> expected = new HashMap<>();

        expected.put(2, 1);
        expected.put(50, 1);

        Assertions.assertEquals(expected, changeGiven);
    }

    @Test
    void calculateChangeForPurchaseWithExactAmount(){
        Set<Integer> coins = new HashSet<>();

        coins.add(50);

        coins.add(1);

        Map<Integer, Integer> changeGiven=ChangeCalculatorJava
                .calculateMinCoinsForChange(new BigDecimal("2.00"), List.of(100), coins);

        Assertions.assertTrue(changeGiven.isEmpty());
    }
}
