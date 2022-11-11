package algorithms.recursion;

import java.util.List;

public class RunExercises {

    public static void main(String[] args) {
        RemoveInvalidParentheses removeInvalidParentheses = new RemoveInvalidParentheses();
        List<String> result = removeInvalidParentheses.removeInvalidParentheses("(()");
        System.out.println(result);
    }

}
