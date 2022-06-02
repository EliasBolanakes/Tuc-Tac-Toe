package Application;

import Model.FileManager;
import Model.PlayerRoaster;
import View.HallOfFame;
import View.LaunchPage;
import View.LoadScreen;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    private final JFrame frame=new JFrame("TUC TAC TOE");
    private final JPanel appPanel=new JPanel();
    private final CardLayout cardLayout=new CardLayout();

    private final LaunchPage launchPage;
    private final HallOfFame hallOfFame;
    private final PlayerRoaster playerRoaster;
    private final LoadScreen loadScreen;

    public static final int WIDTH=1200;
    public static final int HEIGHT=800;

    public Application(){

        playerRoaster=new PlayerRoaster();
        launchPage=new LaunchPage(this);
        hallOfFame=new HallOfFame(this);
        loadScreen=new LoadScreen();

        appPanel.setLayout(cardLayout);
        appPanel.add(launchPage,"LaunchPage");
        appPanel.add(hallOfFame,"HallOfFame");
        appPanel.add(loadScreen,"LoadScreen");
        cardLayout.show(appPanel,"LaunchPage");

        //assigning loading tasks to different thread to avoid freezing the app
        SwingWorker<Void,Void> worker=new SwingWorker<>() {

            @Override
            protected Void doInBackground() {
                cardLayout.show(appPanel,"LoadScreen");
                FileManager.loadFile(FileManager.fileName,playerRoaster);
                return null;
            }

            @Override
            protected void done() {
                cardLayout.show(appPanel,"LaunchPage");
            }
        };
        worker.execute();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(appPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Application();
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getAppPanel() {
        return appPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public LaunchPage getLaunchPage() {
        return launchPage;
    }

    public HallOfFame getHallOfFame() {
        return hallOfFame;
    }

    public PlayerRoaster getPlayerRoaster() {
        return playerRoaster;
    }
}
