package com.example.demo;

public class RandomizedSet {
    private List<Integer> list;
    private Map<Integer, Integer> map;
    private Random rand;
    
    public RandomizedSet(){
        list=new ArrayList<>();
        map=new HashMap<>();
        rand=new Random();
    }
    
    private boolean insert(int val){
        if(map.containsKey(val)){
            return false;
        }
        list.add(val);
        map.put(val, list.size()-1);
        return true;
    }
    
   private boolean remove(int val) {
       if(!map.containsKey(val)){
           return false;
       }
       
       int index=list.get(val);
       int lastIndex=list.size()-1;
       int lastValue=list.get(lastIndex);
       
       list.set(index,lastValue);
       map.put(lastValue, index);
       
       list.remove(lastIndex);
       map.remove(val);
       
      return true;
   }
   
   private int getRandomizedIndex(){
       int randIndex=rand.nextInt(list.size());
       return list.get(randIndex);
   }
   
    public static void main(String[] args){
        RandomizedSet randomizedIndex=new RandomizedSet();
       System.out.println(randomizedIndex.insert(1));
       System.out.println(randomizedIndex.insert(2));
       System.out.println(randomizedIndex.insert(1));
       System.out.println(randomizedIndex.remove(3));
    System.out.println("Random Index: "+randomizedIndex.getRandomizedIndex());
    }

}