import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in); 
        System.out.println("Result: " + calculator(s.nextLine()));
    }
    public static int calculator(String str) {
        int result = 0;
        int sign = 1;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c >= '0' && c <= '9') {
                StringBuilder num = new StringBuilder();
                num.append(c);
                while (i + 1 < str.length() && str.charAt(i + 1) >= '0' && str.charAt(i + 1) <= '9') {
                    num.append(str.charAt(i + 1));
                    i++;
                }
                result += sign * Integer.parseInt(num.toString());
            } else {
                System.out.println("Something went wrong.");
                break;
            }
        }
        return result;
    }
}