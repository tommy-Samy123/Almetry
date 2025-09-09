import java.util.Stack;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in); 
        while (true){
            String input = s.nextLine();
            if (input.equals("quit")) break;
            System.out.println("Result: " + calculator(input));
        } 
    }
    public static int calculator(String str) {
        char sign = '+';
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        str += '+';

        for (int i = 0; i < str.length(); i++) {
            try {
                char c = str.charAt(i);

                if (Character.isDigit(c)) {
                    num = num * 10 + (c - '0');
                }

                if (c == '(') {
                    stack.push((int) sign);
                    stack.push(Integer.MIN_VALUE);  
                    sign = '+';
                    num = 0;
                } 

                else if (c == ')') {
                    if (sign == '+') stack.push(num);
                    else if (sign == '-') stack.push(-num);
                    else if (sign == '*') stack.push(stack.pop() * num);
                    else if (sign == '/') stack.push(stack.pop() / num);
                    
                    num = 0;

                    int sum = 0;
                    int item = stack.pop();
                    while (item != Integer.MIN_VALUE) {
                        sum += item;
                        item = stack.pop();
                    }
     
                    sign = (char) stack.pop().intValue();
                    num = sum;
                }

                else if ("+-*/".indexOf(c) != -1 || i == str.length() - 1) {
                    if (sign == '+') {
                        stack.push(num);
                    } else if (sign == '-') {
                        stack.push(-num);
                    } else if (sign == '*') {
                        stack.push(stack.pop() * num);
                    } else if (sign == '/') {
                        stack.push(stack.pop() / num);
                    } else {
                        System.out.println("Something went wrong.");
                        break;
                    }
                    num = 0;
                    sign = c;
                    
                }
            }
            catch(Exception e) {
                System.out.println("There was an error");
                return 0;
            }
        }
        return stack.stream().mapToInt(Integer::intValue).sum();
    }
}