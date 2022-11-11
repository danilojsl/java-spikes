package dynamic.programming;

import java.util.HashMap;

public class SubarrayEqualsK {

    /*
    * Explanation:
The HashMap will store with the key being any particular sum, and the value being the number of times it has happened till the current iteration of the loop as we traverse the array from left to right.
For example:
k = 26.
If a sub-array sums up to k, then the sum at the end of this sub-array will be sumEnd = sumStart + k. That implies: sumStart = sumEnd - k.
Suppose, at index 10, sum = 50, and the next 6 numbers are 8,-5,-3,10,15,1.
At index 13, sum will be 50 again (the numbers from indexes 11 to 13 add up to 0).
Then at index 16, sum = 76.
Now, when we reach index 16, sum - k = 76 - 26 = 50. So, if this is the end index of a sub-array(s) which sums up to k, then before this, just before the start of the sub-array, the sum should be 50.
As we found sum = 50 at two places before reaching index 16, we indeed have two sub-arrays which sum up to k (26): from indexes 14 to 16 and from indexes 11 to 16.
Code Review / Coding Practices:
Why does the official solution use the name of the map variable simply as map?? Variables should be named such that it is easy to understand their purpose just by reading the name rather than having to spend time tracking their use. So, something like sumOccurrencesMap would be better than simply map.
Please always use braces with the if clause even if just 1 line follows, as otherwise it is really prone to errors and confusion.
    * */

    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap< Integer, Integer > map = new HashMap < > ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

}
