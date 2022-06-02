package View;

import javax.swing.*;
import java.awt.*;

public class LoadScreen extends JPanel {

    private JLabel loadLabel;

    public LoadScreen(){

        loadLabel=new JLabel("Loading...");
        loadLabel.setFont(new Font("Monospaced",Font.BOLD,100));
        loadLabel.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.setBackground(LaunchPage.COLOR);
        this.add(loadLabel);
    }
}
