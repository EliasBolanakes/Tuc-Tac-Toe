package View;

import Application.Application;
import Controller.LaunchPageController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class LaunchPage extends JPanel implements MouseListener {

    LaunchPageController LPC;

    public static Color COLOR=new Color(75, 79, 75);
    private final JPanel X_O_Panel;
    private final JLabel titleLabel;
    public JLabel X_Label;
    public JLabel O_Label;

    public LaunchPage(Application application) {

        LPC=new LaunchPageController(application,this);

        JPanel buttonPanel = new JPanel();
        X_O_Panel = new JPanel();
        JButton[] buttons = new JButton[5];
        titleLabel = new JLabel("TUC-TAC-TOE");
        X_Label=new JLabel("X");
        O_Label=new JLabel("O");

        buttonPanel.setLayout(new GridLayout(1, 5));
        this.setLayout(new BorderLayout());
        X_O_Panel.setLayout(new GridLayout(1,2));

        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 80));
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBackground(LaunchPage.COLOR);
        titleLabel.setOpaque(true);

        X_Label.setFont(new Font("Monospaced", Font.BOLD, 300));
        O_Label.setFont(new Font("Monospaced", Font.BOLD, 300));
        X_Label.setHorizontalAlignment(JLabel.CENTER);
        O_Label.setHorizontalAlignment(JLabel.CENTER);
        X_Label.setOpaque(true);
        O_Label.setOpaque(true);
        X_Label.setBackground(COLOR);
        O_Label.setBackground(COLOR);
        X_Label.addMouseListener(this);
        O_Label.addMouseListener(this);
        X_O_Panel.add(X_Label, 0);
        X_O_Panel.add(O_Label, 1);

        String[] buttonText = {"PLAY", "HALL OF FAME", "ADD PLAYER", "THEME"};
        for (int i = 0; i < 4; i++) {

            buttons[i] = new JButton(buttonText[i]);
            buttons[i].setEnabled(true);
            buttons[i].setFocusable(false);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setFont(new Font("Monospaced", Font.BOLD, 30));
            buttons[i].setSize(100, 400);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
            buttonPanel.add(buttons[i]);
        }
        buttonPanel.setPreferredSize(new Dimension(800, 150));

        buttons[0].addActionListener(e -> LPC.playButton());
        buttons[1].addActionListener(e -> LPC.hallOfFameButton());
        buttons[2].addActionListener(e -> LPC.addPlayer());
        buttons[3].addActionListener(e -> LPC.changeTheme());

        this.setPreferredSize(new Dimension(Application.WIDTH, Application.HEIGHT));
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(X_O_Panel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        Random r=new Random();
        Random g=new Random();
        Random b=new Random();

        if(e.getSource()==X_Label){
            X_Label.setBackground(new Color(r.nextInt(256),g.nextInt(256),b.nextInt(256)));
            X_Label.setFont(new Font("Monospaced", Font.BOLD, 50));
            X_Label.setText("CREATED BY");
        }else if (e.getSource()==O_Label){
            O_Label.setBackground(new Color(r.nextInt(256),g.nextInt(256),b.nextInt(256)));
            O_Label.setFont(new Font("Monospaced", Font.BOLD, 50));
            O_Label.setText("MANOLIS & ILIAS");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        if(e.getSource()==X_Label){
            X_Label.setFont(new Font("Monospaced", Font.BOLD, 300));
            X_Label.setText("X");
            X_Label.setBackground(LaunchPage.COLOR);
        }else if(e.getSource()==O_Label){
            O_Label.setFont(new Font("Monospaced", Font.BOLD, 300));
            O_Label.setText("O");
            O_Label.setBackground(LaunchPage.COLOR);
        }

    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}

    public void setColor(Color c){
        X_Label.setBackground(c);
        O_Label.setBackground(c);
        titleLabel.setBackground(c);
        X_O_Panel.setBackground(c);
        this.setBackground(c);
    }
}