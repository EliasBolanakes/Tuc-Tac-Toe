package Controller;

import Application.Application;
import Model.Player;
import View.HallOfFame;

public class HallOfFameController {

    public final HallOfFame hallOfFame;
    private Application application;

    public HallOfFameController(HallOfFame hallOfFame,Application application){

        this.hallOfFame=hallOfFame;
        this.application=application;
    }

    public void returnToMenu(){

        application.getCardLayout().show(application.getAppPanel(),"LaunchPage");
    }

    public void setTop10players(){

        Player[] players= application.getPlayerRoaster().sortedPlayerArray();

        if(application.getPlayerRoaster().nonNullPlayerElems()>=10){
            for(int i=0;i<10;i++) {
                hallOfFame.rankLabels[i].setText((i + 1) + ") " + players[i].getName() + "   " +
                        Math.round(players[i].score()) + "%");
            }
        }
        else if(application.getPlayerRoaster().nonNullPlayerElems()<10){
            for(int i=0;i<application.getPlayerRoaster().nonNullPlayerElems();i++){
                hallOfFame.rankLabels[i].setText((i+1)+") "+players[i].getName()+"   "+
                        Math.round(players[i].score())+"%");
            }
        }
    }
}