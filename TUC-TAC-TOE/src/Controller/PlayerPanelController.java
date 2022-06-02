package Controller;

import Model.Game;
import Model.Player;
import View.PlayerPanel;

import java.awt.*;

public class PlayerPanelController {


    private PlayerPanel playerPanel;
    private Player player;

    public PlayerPanelController( PlayerPanel playerPanel, Player player){

        this.playerPanel=playerPanel;
        this.player=player;
    }

    public void setBest5Games(){

        for(int i=1;i<6;i++){
            if(player.getBest5Games()[i-1]!=null){
                playerPanel.best5Games[i].setText(player.getBest5Games()[i-1].player1.getName()+" VS "+
                        player.getBest5Games()[i-1].player2.getName());
                setGameColor(i,player.getBest5Games()[i-1]);
            }
            else{
                playerPanel.best5Games[i].setText("Not played yet!");
            }
        }
    }

    public void setGameColor(int i, Game game){

        if(game.winner==player)
            playerPanel.best5Games[i].setForeground(Color.GREEN);
        else if(game.draw);
        else
            playerPanel.best5Games[i].setForeground(Color.RED);

    }
}
