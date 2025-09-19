public class Calculator {
    // Adds two numbers and returns the result
    public double add(double a, double b) {
        return a + b;
    }

    // Subtracts the second number from the first and returns the result
    public double subtract(double a, double b) {
        return a - b;
    }

    // Multiplies two numbers and returns the result
    public double multiply(double a, double b) {
        return a * b;
    }

    // Divides the first number by the second and returns the result
    // Throws IllegalArgumentException if b is zero
    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }
}
