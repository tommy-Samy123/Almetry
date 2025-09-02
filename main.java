import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String expression = s.nextLine();
        int result = 0;

        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                result += Character.getNumericValue(c);
            }
            // If it's a '+', we simply continue the loop
        }
        System.out.println("Result: " + result);
    }
}