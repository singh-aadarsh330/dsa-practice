/**
 * Demonstrates basic try-catch exception handling in Java.
 * Handles ArithmeticException caused by division by zero.
 */
class TryCatchExample {

    public static void main(String[] args) {

        int a = 10;
        int b = 0;

        try {
            int result = a / b;   // May throw ArithmeticException
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        System.out.println("Program continues after exception handling.");
    }
}
