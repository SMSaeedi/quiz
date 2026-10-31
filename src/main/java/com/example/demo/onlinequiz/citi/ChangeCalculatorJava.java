package com.example.demo.onlinequiz.citi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ChangeCalculatorJava {

    /**Problem Statement:

    Assuming the vending machine has an infinite supply of coins, calculate the minimum number of coins required to provide the change.

    Say if you are buying a soda from a vending machine which costs 1.5$ and you insert two 1$ bill, how vending machine can provide a change with minimum number of coins? Assume Vending machine have 1,2,5,10,20,50,100 cents multiple coins.

            Inputs

    productCost - The cost of the product
    insertedCoins - List of coins which have been inserted into the machine in the order that the customer inserted them
    coinSystem - A list of coins that are recognised by the machine
    The order of coinSystem cannot be assumed.

            Outputs

    coins - The coins needed to provide the change.
            Example

    Sample Inputs

    productCost - $1.50
    insertedCoins - 100, 100
    coinSystem - [1, 2, 5, 10, 20, 50, 100, 200]
    Sample Output

    coins - 1 x 50
    We expect one as the smallest number of coins is a single 50.*/

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
