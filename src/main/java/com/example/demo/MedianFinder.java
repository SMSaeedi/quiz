package com.example.demo;

import static java.lang.System.out;
import java.util.*;

public class MedianFinder {
    private static PriorityQueue<Integer> lowerHalf;
    private static PriorityQueue<Integer> upperHalf;
    
    public MedianFinder(){
        lowerHalf= new PriorityQueue<Integer>(Collections.reverseOrder());  //max-heap
        upperHalf=new PriorityQueue<Integer>();  //min-heap
    }
    
    private static void addToMaxHeap(int n){
        //add to max-heap
        if(lowerHalf.isEmpty() || n <= lowerHalf.peek()){
            lowerHalf.offer(n);
        } else {
            upperHalf.offer(n);
        }

        //balance heaps
        if(lowerHalf.size() > upperHalf.size() +1){
            upperHalf.offer(lowerHalf.poll());
        }else if (upperHalf.size() > lowerHalf.size()){
            lowerHalf.offer(upperHalf.poll());
        }
    }
    
    private static double getMedian(){
        if(lowerHalf.size() > upperHalf.size()){
            return lowerHalf.peek();
        } else{
           return (lowerHalf.peek() + upperHalf.peek()); 
        }
    }
    
    public static void main(String[] args){
        MedianFinder medianFinder=new MedianFinder();
        int[] stream={5,15,1,3};
        
        for(int n: stream){
            medianFinder.addToMaxHeap(n);
            System.out.println("added: "+n+" median: "+medianFinder.getMedian());
        }
    }

}