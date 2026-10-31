package com.example.demo.onlinequiz.citi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ChangeCalculatorJava {

 //greedy algorithm

    public static Map<Integer, Integer> calculateMinCoinsForChange(BigDecimal productCost, List<Integer> insertedCoins,
                                                                   Set<Integer> coinSystem) {
        if(productCost == null || insertedCoins == null || coinSystem == null) return Collections.emptyMap();

        //convert product to cents
        int costIntCents=productCost.multiply(new BigDecimal(100))
                .setScale(0, RoundingMode.HALF_UP)
                .intValue();

        //calculate total inserted money in cents
        int totalInsertedCents=0;
        for(Integer coin:insertedCoins)
            if(coin!=null) totalInsertedCents += coin;

        //calculate the total change required
        int changeNeeded=totalInsertedCents-costIntCents;

        if(changeNeeded <= 0) return new HashMap<>();

        //sorting the available coin system
        List<Integer> sortedCoins=new ArrayList<>(coinSystem);
        sortedCoins.sort(Collections.reverseOrder());
        Map<Integer, Integer> result= new HashMap<>();
        for(int coinValue:sortedCoins){
            if(coinValue <= 0) continue;

            if(changeNeeded >= coinValue){
                int count=changeNeeded/coinValue;
                changeNeeded %= coinValue;
                result.put(coinValue,count);
            }
        }

        return result;
    }
}
