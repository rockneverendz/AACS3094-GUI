import javax.swing.*;

public class Practical3Q1 extends JFrame {
    private int i = 0; 
    private int j = 0;
    
    public static void main(String[] args) {
        new Practical3Q1();
    }

    private Practical3Q1() {
        ImageIcon imgGrapes = new ImageIcon(getClass().getResource("images/grapes.gif"));
        JButton jbtGrape = new JButton("Grapes", imgGrapes);
        pack();
        add(jbtGrape);

        int[] sc = {
                SwingConstants.LEADING,
                SwingConstants.LEFT,
                SwingConstants.RIGHT,
                SwingConstants.TRAILING,
                SwingConstants.CENTER,
                SwingConstants.TOP,
                SwingConstants.BOTTOM,
                SwingConstants.CENTER,
        };
        String[] title = {
                "LEADING",
                "LEFT",
                "RIGHT",
                "TRAILING",
                "CENTER",
                "TOP",
                "BOTTOM",
                "CENTER"
        };

        jbtGrape.addActionListener(e -> {
            if (j == 0){
                if (i < 5)
                    jbtGrape.setHorizontalAlignment(sc[i]);
                else if (i < 8)
                    jbtGrape.setVerticalAlignment(sc[i]);
                else{
                    i = 0;
                    j++;
                }
            }
            else if (j == 1){
                if (i < 5)
                    jbtGrape.setHorizontalTextPosition(sc[i]);
                else if (i < 8)
                    jbtGrape.setVerticalTextPosition(sc[i]);
                else{
                    i = 0;
                    j++;
                }
            }
            else
                j = 0;
            super.setTitle(title[i]);
            System.out.println(j + "." + i++);
        });

        super.setTitle("Button Icons");
        super.setSize(500, 500);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
}