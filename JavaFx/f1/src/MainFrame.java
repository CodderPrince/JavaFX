import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class MainFrame extends JFrame {
    /*Global Variable */
    final private Font mainFont = new Font("Segoe print",Font.BOLD,18);
    JTextField tfFirstName, tfLastName;

    public void initialize() {
        /*********** Form Panel ************/
        JLabel lbFirstName = new JLabel("First Name");
        lbFirstName.setFont(mainFont);

        tfFirstName = new JTextField();
        tfFirstName.setFont(mainFont);

        //Level
        JLabel lbLastName = new JLabel("Last Name");
        lbLastName.setFont(mainFont);


        JPanel maiPanel = new JPanel();
        maiPanel.setLayout(new BorderLayout());
        maiPanel.setBackground(new Color(128, 128, 255));

        setTitle("Welcome");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

}
