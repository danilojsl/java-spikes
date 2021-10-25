package problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestArmstrongNumbers {

    @Test
    public void shouldReturnTrueWhenItIsAnArmstrongNumber() {
        Boolean result = ArmstrongNumbers.isArmstrongNumber(153);

        assertTrue(result);
    }

}