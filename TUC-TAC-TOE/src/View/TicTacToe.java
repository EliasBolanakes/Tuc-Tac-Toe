package View;

import Application.Application;
import Controller.TicTacToeController;
import Model.Player;

import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JPanel{

    TicTacToeController TTTC;
    public JLabel titleLabel;
    public JButton[][] buttons;
    public PlayerPanel playerPanel1;
    public PlayerPanel playerPanel2;

    public TicTacToe(Application application,Player player1,Player player2){

        playerPanel1=new PlayerPanel(player1,true);
        playerPanel2=new PlayerPanel(player2,false);
        TTTC=new TicTacToeController(application,this,player1,player2);
        JPanel buttonPanel = new JPanel();
        titleLabel=new JLabel();
        buttons=new JButton[3][3];

        playerPanel2.setForeground(Color.GREEN);

        this.setLayout(new BorderLayout());
        buttonPanel.setLayout(new GridLayout(3,3));

        titleLabel.setText(player1.getName()+" TURN");
        titleLabel.setFont(new Font("Monospaced",Font.BOLD,80));
        titleLabel.setBackground(LaunchPage.COLOR);
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j]=new JButton();
                buttons[i][j].setFont(new Font("Monospaced",Font.BOLD,100));
                buttons[i][j].setText("");
                buttons[i][j].setFocusable(false);
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                buttonPanel.add(buttons[i][j]);
            }
        }
        buttons[0][0].addActionListener(e ->TTTC.setButtons(0,0) );
        buttons[0][1].addActionListener(e ->TTTC.setButtons(0,1) );
        buttons[0][2].addActionListener(e ->TTTC.setButtons(0,2) );
        buttons[1][0].addActionListener(e ->TTTC.setButtons(1,0) );
        buttons[1][1].addActionListener(e ->TTTC.setButtons(1,1) );
        buttons[1][2].addActionListener(e ->TTTC.setButtons(1,2) );
        buttons[2][0].addActionListener(e ->TTTC.setButtons(2,0) );
        buttons[2][1].addActionListener(e ->TTTC.setButtons(2,1) );
        buttons[2][2].addActionListener(e ->TTTC.setButtons(2,2) );

        TTTC.initialClick();

        this.setBackground(LaunchPage.COLOR);
        this.setPreferredSize(new Dimension(1200,800));
        this.add(titleLabel,BorderLayout.NORTH);
        this.add(buttonPanel,BorderLayout.CENTER);
        this.add(playerPanel1,BorderLayout.WEST);
        this.add(playerPanel2,BorderLayout.EAST);
    }

    public void setColor(Color c){

        titleLabel.setBackground(c);
        playerPanel1.setColor(c);
        playerPanel2.setColor(c);
    }
}