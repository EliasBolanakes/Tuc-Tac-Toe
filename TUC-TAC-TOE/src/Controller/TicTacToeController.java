package Controller;

import Application.Application;
import Model.*;
import View.LaunchPage;
import View.TicTacToe;
import javax.swing.*;
import java.awt.*;

public class TicTacToeController {

    private final TicTacToe ticTacToe;
    private final Application application;
    private final Player player1;
    private final Player player2;
    private final Board gameBoard;
    private boolean player1Turn=true;
    private final Game ticTacToeGame;
    private boolean gameInPlay=true;
    private final Timer timer =new Timer(500,e ->AIMoves());

    public TicTacToeController(Application application,TicTacToe ticTacToe,Player player1,Player player2){

        this.ticTacToe= ticTacToe;
        this.application=application;
        this.player1=player1;
        this.player2=player2;
        this.ticTacToeGame=new Game(player1,player2);
        this.gameBoard=new Board();
    }

    public void setButtons(int i,int j){

        if(!ticTacToe.buttons[i][j].getText().equals("")||!gameInPlay)
            return;
        if(player1Turn) {
            if(!AI_plays_X()){
                ticTacToe.buttons[i][j].setForeground(Color.RED);
                ticTacToe.buttons[i][j].setText("X");
                gameBoard.getBoard()[i][j] = 'X';
                Toggle();
            }
            if(AI_plays_O() && gameInPlay){
                timer.start();
            }
        }else{
             if(!AI_plays_O()) {
                ticTacToe.buttons[i][j].setForeground(Color.GREEN);
                ticTacToe.buttons[i][j].setText("O");
                gameBoard.getBoard()[i][j]='O';
                Toggle();
            }
            if(AI_plays_X() && gameInPlay){
                timer.start();
            }
        }
    }

    // making winning pattern green
    private void changeBackground() {

        for (int i = 0; i < Board.ROWS; i++) {
            if (gameBoard.getBoard()[i][0] == gameBoard.getBoard()[i][1]
                    && gameBoard.getBoard()[i][1] == gameBoard.getBoard()[i][2]
                    && gameBoard.getBoard()[i][0] != ' '){

                ticTacToe.buttons[i][0].setBackground(Color.GREEN);
                ticTacToe.buttons[i][1].setBackground(Color.GREEN);
                ticTacToe.buttons[i][2].setBackground(Color.GREEN);
            }
        }
        for (int i = 0; i < Board.COLS; i++) {
            if (gameBoard.getBoard()[0][i] == gameBoard.getBoard()[1][i]
                    && gameBoard.getBoard()[1][i] == gameBoard.getBoard()[2][i]
                    && gameBoard.getBoard()[0][i] != ' '){

                ticTacToe.buttons[0][i].setBackground(Color.GREEN);
                ticTacToe.buttons[1][i].setBackground(Color.GREEN);
                ticTacToe.buttons[2][i].setBackground(Color.GREEN);
            }
        }
        if (gameBoard.getBoard()[0][0] == gameBoard.getBoard()[1][1]
                && gameBoard.getBoard()[1][1] == gameBoard.getBoard()[2][2]
                && gameBoard.getBoard()[0][0] != ' '){

            ticTacToe.buttons[0][0].setBackground(Color.GREEN);
            ticTacToe.buttons[1][1].setBackground(Color.GREEN);
            ticTacToe.buttons[2][2].setBackground(Color.GREEN);

        } else if (gameBoard.getBoard()[0][2] == gameBoard.getBoard()[1][1]
                && gameBoard.getBoard()[1][1] == gameBoard.getBoard()[2][0]
                && gameBoard.getBoard()[0][2] != ' '){

            ticTacToe.buttons[0][2].setBackground(Color.GREEN);
            ticTacToe.buttons[1][1].setBackground(Color.GREEN);
            ticTacToe.buttons[2][0].setBackground(Color.GREEN);
        }
        checkWhoWon();
    }

    private void disableButtons(){
        for(int i=0;i<Board.ROWS;i++){
            for(int j=0;j<Board.COLS;j++){
                ticTacToe.buttons[i][j].setEnabled(false);
            }
        }
    }

    //setting the winner label
    private void checkWhoWon(){
        if(Board.checkIfWon(gameBoard.getBoard(),'X')==10){

            timer.stop();
            ticTacToe.titleLabel.setText(player1.getName()+" WON");
            gameInPlay=false;
            disableButtons();
            updateStats();

        } else if(Board.checkIfWon(gameBoard.getBoard(),'X')==-10){

            timer.stop();
            ticTacToe.titleLabel.setText(player2.getName()+" WON");
            gameInPlay=false;
            disableButtons();
            updateStats();

        } else if(!Board.hasMovesLeft(gameBoard.getBoard())){

            timer.stop();
            ticTacToe.titleLabel.setText("DRAW");
            gameInPlay=false;
            disableButtons();
            updateStats();
        }
    }

    private void rematch(){

        String[] choices={"YES, BRING IT ON!","NO, RETURN TO MENU"};
        int option=-1;
        while (option==-1){
            option=JOptionPane.showOptionDialog(application.getAppPanel(),
                    "Would you like a rematch?","Game Ended",
                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                    null,choices,0);
        }

        if(option==0){
            TicTacToe ticTacToe=new TicTacToe(application,player1,player2);
            application.getAppPanel().add(ticTacToe,"TicTacToe");
            ticTacToe.setColor(LaunchPage.COLOR);
            application.getCardLayout().show(application.getAppPanel(),"TicTacToe");
        }
        else{
            application.getCardLayout().show(application.getAppPanel(),"LaunchPage");
        }
    }

    //checking which AI player should make move
    private void AIMoves() {

        if(!gameInPlay)
            return;

        if(HalMove());
        else if(MamalakisMove());
        else if(MrBeanMove());

        if(!AI_plays_X() && player1Turn)
            timer.stop();
        else if(!AI_plays_O() && !player1Turn)
            timer.stop();
    }

    private boolean HalMove(){

        if(player1.getClass()==PerfectPlayer.class&&player1Turn){

            Move bestMove= application.getPlayerRoaster().getHal().findBestMove(gameBoard.getBoard(), 'X');
            ticTacToe.buttons[bestMove.getRow()][bestMove.getCol()].setForeground(Color.RED);
            ticTacToe.buttons[bestMove.getRow()][bestMove.getCol()].setText("X");
            gameBoard.getBoard()[bestMove.getRow()][bestMove.getCol()]='X';
            Toggle();
            return true;

        }else if(player2.getClass()==PerfectPlayer.class&&!player1Turn){

            Move bestMove= application.getPlayerRoaster().getHal().findBestMove(gameBoard.getBoard(), 'O');

            ticTacToe.buttons[bestMove.getRow()][bestMove.getCol()].setForeground(Color.GREEN);
            ticTacToe.buttons[bestMove.getRow()][bestMove.getCol()].setText("O");
            gameBoard.getBoard()[bestMove.getRow()][bestMove.getCol()]='O';
            Toggle();
            return true;
        }
        return false;
    }

    private boolean MamalakisMove(){

        if(player1.getClass()==MediocrePlayer.class&&player1Turn){

            Move bestMove= application.getPlayerRoaster().getMamalakis().findBestMove(gameBoard.getBoard(), 'X');
            ticTacToe.buttons[bestMove.getRow()][bestMove.getCol()].setForeground(Color.RED);
            ticTacToe.buttons[bestMove.getRow()][bestMove.getCol()].setText("X");
            gameBoard.getBoard()[bestMove.getRow()][bestMove.getCol()]='X';
            Toggle();
            return true;

        }else if(player2.getClass()==MediocrePlayer.class&&!player1Turn){

            Move bestMove= application.getPlayerRoaster().getMamalakis().findBestMove(gameBoard.getBoard(), 'O');
            ticTacToe.buttons[bestMove.getRow()][bestMove.getCol()].setForeground(Color.GREEN);
            ticTacToe.buttons[bestMove.getRow()][bestMove.getCol()].setText("O");
            gameBoard.getBoard()[bestMove.getRow()][bestMove.getCol()]='O';
            Toggle();
            return true;
        }
        return false;
    }

    private boolean MrBeanMove(){

        if(player1.getClass()==BadPlayer.class&&player1Turn){

            Move badMove= application.getPlayerRoaster().getMrBean().badPlayerMove(gameBoard.getBoard());
            ticTacToe.buttons[badMove.getRow()][badMove.getCol()].setForeground(Color.RED);
            ticTacToe.buttons[badMove.getRow()][badMove.getCol()].setText("X");
            gameBoard.getBoard()[badMove.getRow()][badMove.getCol()]='X';
            Toggle();
            return true;

        }else if(player2.getClass()==BadPlayer.class&&!player1Turn){

            Move badMove= application.getPlayerRoaster().getMrBean().badPlayerMove(gameBoard.getBoard());
            ticTacToe.buttons[badMove.getRow()][badMove.getCol()].setForeground(Color.GREEN);
            ticTacToe.buttons[badMove.getRow()][badMove.getCol()].setText("O");
            gameBoard.getBoard()[badMove.getRow()][badMove.getCol()]='O';
            Toggle();
            return true;
        }
        return false;
    }

    private void updateStats(){

        SwingWorker<Void,Void> worker=new SwingWorker<>() {
            @Override
            protected Void doInBackground() {

                if(Board.checkIfWon(gameBoard.getBoard(),'X')==10){
                    ticTacToeGame.winner=player1;
                    player1.updatePlayerStats(ticTacToeGame,true,false);
                    player2.updatePlayerStats(ticTacToeGame,false,false);
                    FileManager.saveFile(FileManager.fileName,application.getPlayerRoaster());

                } else if(Board.checkIfWon(gameBoard.getBoard(),'X')==-10){
                    ticTacToeGame.winner=player2;
                    player1.updatePlayerStats(ticTacToeGame,false,false);
                    player2.updatePlayerStats(ticTacToeGame,true,false);
                    FileManager.saveFile(FileManager.fileName,application.getPlayerRoaster());

                } else if(!Board.hasMovesLeft(gameBoard.getBoard())){
                    ticTacToeGame.draw=true;
                    player1.updatePlayerStats(ticTacToeGame,false,true);
                    player2.updatePlayerStats(ticTacToeGame,false,true);
                    FileManager.saveFile(FileManager.fileName,application.getPlayerRoaster());
                }
                return null;
            }

            @Override
            protected void done() {
                rematch();
            }
        };
        worker.execute();
    }

    // initial button click so that AI player makes first move without clicking the button
    public void initialClick(){

        if(AI_plays_X())
            timer.start();
    }

    private boolean AI_plays_X(){
        return (player1.getClass()==PerfectPlayer.class
                ||player1.getClass()==MediocrePlayer.class
                ||player1.getClass()==BadPlayer.class);
    }

    private boolean AI_plays_O(){
        return (player2.getClass()==PerfectPlayer.class
                ||player2.getClass()==MediocrePlayer.class
                ||player2.getClass()==BadPlayer.class);
    }

    //switching turn
    private void Toggle(){

        if(!gameInPlay)
            return;
        if(player1Turn){
            ticTacToe.titleLabel.setText(player2.getName()+" TURN");
            player1Turn=false;
        }
        else{
            ticTacToe.titleLabel.setText(player1.getName()+" TURN");
            player1Turn=true;
        }
        changeBackground();
    }
}