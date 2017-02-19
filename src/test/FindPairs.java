package test;

import java.util.HashMap;

public class FindPairs {
	public static void main(String [] args){
		int [] arr = {1,2,3,4,5};
		int sum = 6;
		GetPair result = new GetPair();
		System.out.println(result.pair(arr,sum));
	}
}

	/**
     * Find the pair in an array whose sum with complexity O(2n)
     *
     * @param arr
     *            input array of elements
     * @param k
     *            sum for which pair of array elements needs to be searched
     * @return string of combination of array pairs
     */
class GetPair{
    public String pair(int[] arr, int k) {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        String result = "";
 
        /**
         * First store array elements into hashmap with key as the value of the
         * array This has time complexity of O(n) and space complexity of O(n)
         */
        for (int i = 0; i < arr.length; i++) {
            hm.put(arr[i], arr[i]);
        }
 
        /**
         * Try getting the hashmap value at key (sum-array_element) If it
         * exists, it means array_element and sum-array_element is the pair
         * thats forms sum 'k' This has time complexity of O(n)
         */
        for (int i = 0; i < arr.length; i++) {
            if (hm.get(k - arr[i]) != null) {
                result += arr[i] + "," + (k - arr[i]) + ";";
            }
        }
        return result;
    }
}