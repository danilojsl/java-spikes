package algorithms.backtracking;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParenthesesTest {

    @Test
    public void shouldCreateParentheses() {
       List<String> result = Parentheses.AllParenthesis(3);

       assertTrue(result.size() > 1);
    }

}