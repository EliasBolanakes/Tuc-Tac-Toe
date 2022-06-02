package View;

import Application.Application;
import Controller.HallOfFameController;

import javax.swing.*;
import java.awt.*;

public class HallOfFame extends JPanel{

    public HallOfFameController HOFC;
    private JPanel buttonPanel;
    private JPanel rankingPanel;
    private JLabel titleLabel;
    public JLabel[] rankLabels;
    private JButton returnButton;

    public HallOfFame(Application application){

        HOFC=new HallOfFameController(this,application);
        rankingPanel=new JPanel();
        buttonPanel=new JPanel();
        returnButton=new JButton();
        titleLabel=new JLabel();
        rankLabels=new JLabel[10];

        rankingPanel.setLayout(new GridLayout(10,1));
        this.setLayout(new BorderLayout());

        titleLabel.setBackground(LaunchPage.COLOR);
        titleLabel.setText("HALL OF FAME");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setFont(new Font("Monospaced",Font.BOLD,80));

        returnButton.setText("RETURN TO MENU");
        returnButton.setFont(new Font("Monospaced",Font.BOLD,30));
        returnButton.setBackground(Color.WHITE);
        returnButton.setEnabled(true);
        returnButton.setFocusable(false);
        returnButton.addActionListener(e -> HOFC.returnToMenu());
        buttonPanel.add(returnButton);

        rankingPanel.setBackground(LaunchPage.COLOR);

        buttonPanel.setPreferredSize(new Dimension(1200,100));
        buttonPanel.setBackground(LaunchPage.COLOR);

        for(int i=0;i<10;i++){

            rankLabels[i]=new JLabel();
            rankLabels[i].setText("");
            rankLabels[i].setBackground(LaunchPage.COLOR);
            rankLabels[i].setFont(new Font("Monospaced",Font.BOLD,25));
            rankLabels[i].setHorizontalAlignment(JLabel.CENTER);
            rankLabels[i].setOpaque(true);
            rankingPanel.add(rankLabels[i]);
        }

        this.setBackground(LaunchPage.COLOR);
        this.add(titleLabel,BorderLayout.NORTH);
        this.add(rankingPanel,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);
    }

    public void setColor(Color c){

        titleLabel.setBackground(c);
        buttonPanel.setBackground(c);
        rankingPanel.setBackground(c);
        for(int i=0;i<10;i++){
            rankLabels[i].setBackground(c);
        }
    }
}