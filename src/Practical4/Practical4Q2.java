import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Practical4Q2 extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(Practical4Q2::new);
    }

    private Practical4Q2() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel jpnArray = new JpnArray();
        JButton jbtShowElem = new JButton("Show Element");

        add(jpnArray);
        add(jbtShowElem);

        jbtShowElem.addActionListener(e -> JpnArray.getElement());

        pack();
        super.setTitle("Array");
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
}

class JpnArray extends JPanel {
    private static JTextField jtfIndex = new JTextField(10);
    private static JTextField jtfElement = new JTextField();
    private static int[] array = new int[100];

    JpnArray() {
        super.setLayout(new GridLayout(2, 2));
        add(new JLabel("Array Index"));
        add(jtfIndex);
        add(new JLabel("Array Element"));
        add(jtfElement);
        jtfElement.setEditable(false);

        Random rand = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(10000 - 1000) + 1000;
            System.out.println(array[i]);
        }
    }

    static void getElement() {
        try{
            jtfElement.setText(
                    String.valueOf(
                            array[Integer.parseInt(jtfIndex.getText())]
                    )
            );
        }catch(IndexOutOfBoundsException e){
            jtfElement.setText("Out Of Bound");
        }catch (NumberFormatException e){
            jtfElement.setText("Illegal characters found");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}