import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Main {
    static java.util.List<String> equations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter equations of lines (e.g., y=2x+3). Type 'done' when finished:");

        while (true) {
            String line = sc.nextLine().replace(" ", "");
            if (line.equalsIgnoreCase("done")) break;
            if (line.startsWith("y=")) {
                equations.add(line.substring(2)); // store only right side
            } else {
                System.out.println("Invalid format. Use y=... Try again.");
            }
        }
        sc.close();

        JFrame frame = new JFrame("Graphing Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JPanel graphPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                int w = getWidth();
                int h = getHeight();

                // Axes
                g2.setColor(Color.BLACK);
                g2.drawLine(0, h/2, w, h/2); // x-axis
                g2.drawLine(w/2, 0, w/2, h); // y-axis

                double scale = 20.0; // pixels per unit
                Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.ORANGE};

                int colorIndex = 0;
                for (String expr : equations) {
                    g2.setColor(colors[colorIndex % colors.length]);
                    colorIndex++;

                    // plot each line
                    for (int px = 0; px < w; px++) {
                        double x = (px - w/2) / scale;
                        double y = eval(expr, x);
                        int py = (int)(h/2 - y * scale);
                        if (py >= 0 && py < h) {
                            g2.fillRect(px, py, 1, 1);
                        }
                    }
                }
            }
        };

        frame.add(graphPanel);
        frame.setVisible(true);
    }

    // Evaluate expression like "2x+3"
    public static double eval(String expr, double x) {
        expr = expr.replace("x", "(" + x + ")");
        try {
            return ((Number) new javax.script.ScriptEngineManager()
                    .getEngineByName("JavaScript")
                    .eval(expr)).doubleValue();
        } catch (Exception e) {
            return 0;
        }
    }
}
