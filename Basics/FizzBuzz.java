// User function Template for Java

/**
 * FizzBuzz Problem
 * Prints:
 * - "FizzBuzz" if number is divisible by both 3 and 5
 * - "Fizz" if number is divisible by 3
 * - "Buzz" if number is divisible by 5
 * - The number itself otherwise
 */
class Solution {

    public static void fizzBuzz(int number) {

        if (number % 3 == 0 && number % 5 == 0) {
            System.out.println("FizzBuzz");
        } else if (number % 3 == 0) {
            System.out.println("Fizz");
        } else if (number % 5 == 0) {
            System.out.println("Buzz");
        } else {
            System.out.println(number);
        }
    }
}
