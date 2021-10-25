package problems;

public class ArmstrongNumbers {

    public static Boolean isArmstrongNumber(int number) {

        String numberString = String.valueOf(number);
        char[] numbers = numberString.toCharArray();

        int finalResult = 0;
        for (char n: numbers) {
            double currentNumber = Integer.parseInt(Character.toString(n));
            int result = (int) Math.pow(currentNumber, numbers.length);
            finalResult += result;
        }

        return finalResult == number;

    }

}
