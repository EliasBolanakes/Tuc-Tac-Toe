package View;

import Controller.PlayerPanelController;
import Model.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel{

    JLabel playerName;
    JLabel[] playerStats;
    JLabel playerChar;
    public JLabel[] best5Games;
    public PlayerPanelController PPC;

    public PlayerPanel(Player player, boolean player1){

        PPC=new PlayerPanelController(this,player);
        playerName=new JLabel();
        playerChar=new JLabel();
        playerStats=new JLabel[4];
        best5Games=new JLabel[6];

        this.setLayout(new GridLayout(12,1,0,0));

        playerChar.setFont(new Font("Monospaced",Font.BOLD,75));

        if(player1){
            playerChar.setText("X");
            playerChar.setForeground(Color.RED);
        }
        else {
            playerChar.setText("O");
            playerChar.setForeground(Color.GREEN);
        }
        playerChar.setHorizontalAlignment(JLabel.CENTER);
        playerChar.setBackground(LaunchPage.COLOR);
        playerChar.setOpaque(true);
        this.add(playerChar);

        playerName.setText(player.getName());
        playerName.setHorizontalAlignment(JLabel.CENTER);
        playerName.setBackground(LaunchPage.COLOR);
        playerName.setOpaque(true);
        playerName.setFont(new Font("Monospaced",Font.BOLD,40));
        this.add(playerName);

        for (int i=0;i<4;i++){
            playerStats[i]=new JLabel();
            playerStats[i].setBackground(LaunchPage.COLOR);
            playerStats[i].setOpaque(true);
            playerStats[i].setHorizontalAlignment(JLabel.CENTER);
            playerStats[i].setFont(new Font("Monospaced",Font.BOLD,25));
            this.add(playerStats[i]);
        }

        playerStats[0].setText("WINS: "+player.getWins());
        playerStats[1].setText("GAMES PLAYED: "+player.getGamesPlayed());
        playerStats[2].setText("SCORE: "+Math.round(player.score()));
        playerStats[3].setText("RECENT SCORE: "+Math.round(player.recentScore()));

        for (int i=0;i<6;i++){
            best5Games[i]=new JLabel();
            best5Games[i].setOpaque(true);
            best5Games[i].setBackground(LaunchPage.COLOR);
            best5Games[i].setHorizontalAlignment(JLabel.CENTER);
            best5Games[i].setFont(new Font("Monospaced",Font.PLAIN,20));
            this.add(best5Games[i]);
        }

        best5Games[0].setText("5 BEST GAMES");
        best5Games[0].setFont(new Font("Monospaced",Font.BOLD,25));
        PPC.setBest5Games();
    }

    public void setColor(Color c){

        this.setBackground(c);
        for(int i=0;i<6;i++){
            best5Games[i].setBackground(c);
            playerName.setBackground(c);
        }
        for(int i=0;i<3;i++){
            playerStats[i].setBackground(c);
        }
    }
}
