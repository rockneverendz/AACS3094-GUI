import javax.swing.*;

public class Practical1Q1 {
    public static void main(String[] args) {
        do {
            String fahrenheit = JOptionPane.showInputDialog("Enter Fahrenheit temperature");
            double celsius = (double) 5 / 9 * (Integer.parseInt(fahrenheit) - 32);
            JOptionPane.showMessageDialog(null, "The celsius is " + celsius);
        }while (JOptionPane.showConfirmDialog(null, "Convert again?","Converter",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
}
