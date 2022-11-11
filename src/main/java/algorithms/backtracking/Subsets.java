package algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        int [] input = {1, 2, 3};
        subsets.subsets(input);
        System.out.println("done");
    }
    List<List<Integer>> output = new ArrayList();

    public List<List<Integer>> subsets(int[] nums) {
        int chunkSize;
        int numsSize = nums.length;
        for (chunkSize = 0; chunkSize < numsSize + 1; chunkSize++) {
            backtrack(0, new ArrayList<Integer>(), nums, chunkSize, numsSize);
        }
        return output;
    }

    public void backtrack(int first, ArrayList<Integer> curr, int[] nums, int chunkSize, int numsSize) {
        // if the combination is done
        if (curr.size() == chunkSize) {
            output.add(new ArrayList(curr));
            return;
        }
        for (int i = first; i < numsSize; i++) {
            // add i into the current combination
            curr.add(nums[i]);
            // use next integers to complete the combination
            backtrack(i + 1, curr, nums, chunkSize, numsSize);
            // backtrack
            curr.remove(curr.size() - 1);
        }
    }

}
