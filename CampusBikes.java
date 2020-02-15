  TC: O(w*b*log(size of hashmap)
  SC: O(n)
  
 28 / 28 test cases passed. Status: Accepted Runtime: 263 ms Memory Usage: 96.9 MB  
 
 Approach: TreeMap will give the ascending order of distance as key and values as the pair of worker and biker.We traverse the worker,bike
 pair from the smallest distance and assign worker and bike. While traversing the list of [worker,pair] we assign a bike to a worker
 if both worker and bike are not yet assigned.

  class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        
        Map<Integer,List<int[]>> map = new TreeMap<>();
        for(int i=0; i<workers.length;i++){
            for(int j=0 ; j<bikes.length; j++){
                int dist = calc_dist(workers[i],bikes[j]);
                
                if(map.containsKey(dist)){
                    List<int[]> list = map.get(dist);
                    list.add(new int[]{i,j});
                    map.put(dist,list);
                }else{
                    List<int[]> list = new ArrayList<>();
                    list.add(new int[]{i,j});
                    map.put(dist,list);
                }
                
            }
        }
        
        int[] result = new int[workers.length];
        boolean [] checkworker = new boolean[workers.length];
        boolean [] checkbike = new boolean[bikes.length];
                
                // List<int[]> list = map.getOrDefault(dist,new ArrayList<>());
                // map.put(dist,list.add(new int[]{i,j}));
        for(List<int[]> list : map.values())       {
            for(int[] temp: list){
                int worker = temp[0];
                int bike = temp[1];
                
                if(!checkworker[worker] && !checkbike[bike]){
                    checkworker[worker] = true;
                    checkbike[bike] = true;
                    
                    result[worker]= bike;
                }
            }
        }
       return result;
    }
    private int calc_dist(int[] worker, int[] bike){
        return Math.abs(worker[0]-bike[0])+Math.abs(worker[1]-bike[1]);
    }
}
