package Controller;

import Application.Application;
import Model.FileManager;
import Model.Player;
import View.LaunchPage;
import View.TicTacToe;
import javax.swing.*;
import java.awt.*;

public class LaunchPageController {

    private final Application application;
    public LaunchPage launchPage;

    public LaunchPageController(Application application,LaunchPage launchPage){

        this.application=application;
        this.launchPage=launchPage;
    }

    public void playButton(){

        String player1;
        Player p1=new Player("");
        String player2;
        Player p2=new Player("");

        player1= selectPlayer1();

        if(player1==null)
            return;

        player2=selectPlayer2();

        if(player2==null){
            return;
        }
        else if(player1.equals(player2)){
            JOptionPane.showMessageDialog(application.getFrame(),
                    "Can't choose the same player twice!",
                    "ERROR",JOptionPane.WARNING_MESSAGE);
            return;
        }

        for(int i=0;i<application.getPlayerRoaster().nonNullPlayerElems();i++){
            if(player1.equals(application.getPlayerRoaster().getPlayer(i).getName()))
                p1=application.getPlayerRoaster().getPlayer(i);
            if(player2.equals(application.getPlayerRoaster().getPlayer(i).getName()))
                p2=application.getPlayerRoaster().getPlayer(i);
        }

        TicTacToe ticTacToe=new TicTacToe(application,p1,p2);
        application.getAppPanel().add(ticTacToe,"TicTacToe");
        ticTacToe.setColor(LaunchPage.COLOR);
        application.getCardLayout().show(application.getAppPanel(),"TicTacToe");
    }

    public void hallOfFameButton(){

        application.getHallOfFame().HOFC.setTop10players();
        application.getCardLayout().show(application.getAppPanel(),"HallOfFame");
    }

    public void addPlayer(){

        String name= JOptionPane.showInputDialog(application.getFrame(),
                "Enter Player's name(max 20 characters)",
                "Player Addition",
                JOptionPane.QUESTION_MESSAGE);

        if(name==null)
            return;
        if(!application.getPlayerRoaster().addPlayer(name)||name.equals("")){
            JOptionPane.showMessageDialog(application.getFrame(),
                    "Player name must be unique, contain 20 characters max and have no whitespace in beginning/ending",
                    "Player couldn't be added",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        SwingWorker<Void,Void> worker=new SwingWorker<>(){
            @Override
            protected Void doInBackground() {
                application.getPlayerRoaster().addPlayer(name);
                FileManager.saveFile(FileManager.fileName,application.getPlayerRoaster());
                return null;
            }
        };
        worker.execute();
    }

    private class ColorThread implements Runnable{

        @Override
        public void run() {
            application.getHallOfFame().setColor(LaunchPage.COLOR);
            application.getLaunchPage().setColor(LaunchPage.COLOR);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeTheme(){

        Thread colorChange=new Thread(new ColorThread());
        LaunchPage.COLOR=JColorChooser.showDialog(application.getFrame(),"Pick the color the app",Color.BLACK);
        colorChange.start();
        try {
            colorChange.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String selectPlayer1(){

        return (String) JOptionPane.showInputDialog(application.getFrame(),
                "SELECT PLAYER 1","Player 1 selection",
                JOptionPane.QUESTION_MESSAGE,null,
                application.getPlayerRoaster().playerListNames(), 0);
    }
    private String selectPlayer2(){

        return (String) JOptionPane.showInputDialog(application.getFrame(),
                "SELECT PLAYER 2","Player 2 selection",
                JOptionPane.QUESTION_MESSAGE,null,
                application.getPlayerRoaster().playerListNames(), 0);
    }
}