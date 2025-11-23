package com.example.demo;

public class MinRequiredMeetingRooms {
   
    private static int minRooms(int[][] elements){
        if(elements == null || elements.length == 0){
            return 0;
        }
        
        Arrays.sort(elements, (a,b) -> Integer.compare(a[0],b[0]));
        
        PriorityQueue<Integer> minHeap =new PriorityQueue<>();
        
        minHeap.offer(elements[0][1]);
        
        for(int i=1; i<elements.length; i++){
            int start=elements[i][0];
            int end=elements[i][1];
            
            if(!minHeap.isEmpty() && start >= minHeap.peek()){
                minHeap.poll();
            }
            
            minHeap.offer(end);
        }
        return minHeap.size();
    }
   
    
    public static void main(String[] args){
        int[][] meetings_1={{0,30},{5,10},{15,20}};
        int[][] meetings_2={{7,10},{2,4}};
        
        System.out.println(minRooms(meetings_1));
        System.out.println(minRooms(meetings_2));
    }

}