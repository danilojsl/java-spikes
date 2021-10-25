package algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Parentheses {

    public static List<String> AllParenthesis(int numberOfPairs) {

        List<String> ans = new ArrayList<>();
        generateParenthesis("", numberOfPairs,0,0, ans);

        return ans;
    }

    public static void generateParenthesis(String parentheses, int numberOfPairs, int openingCount, int closingCount,
                                           List<String> ans)
    {
        if(closingCount == numberOfPairs){
            ans.add(parentheses);
            return;
        }

        if(openingCount > closingCount) {
            generateParenthesis(parentheses + ')', numberOfPairs, openingCount,closingCount + 1, ans);
        }

        if(openingCount < numberOfPairs) {
            generateParenthesis(parentheses + '(', numberOfPairs,openingCount + 1, closingCount, ans);
        }

    }

}
