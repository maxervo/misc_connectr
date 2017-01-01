package com.company.ui;

import com.company.product.Game;
import com.company.product.Grid;
import com.company.product.Main;

import java.awt.*;

import java.net.URL;
import java.util.List;
import java.util.stream.IntStream;
import javax.swing.*;

/**
 * Created by rstoke on 12/30/16.
 */
public class GUI extends UI {
    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 400;

    private JFrame mainFrame;
    private JPanel statusPanel;   //included inside JFrame
    private JPanel gridPanel;

    private String decision;

    public GUI(Grid grid, List<Integer> score) {
        //Main Frame
        this.mainFrame = new JFrame(Main.PROGRAM_NAME);
        this.mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.mainFrame.setLayout(new BoxLayout(this.mainFrame.getContentPane(), BoxLayout.Y_AXIS)); //Attention getContentPane() again
        this.mainFrame.setDefaultLookAndFeelDecorated(true);
        this.mainFrame.setVisible(true);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Prepare status
        this.statusPanel = new JPanel();
        this.statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));

        //Prepare grid
        this.gridPanel = new JPanel();

        //Attach
        this.mainFrame.add(statusPanel);
        this.mainFrame.add(gridPanel);

        //Build components
        displayStatus("Welcome to " + Main.PROGRAM_NAME, score);
        displayGrid(grid);

        //Data
        this.decision = UI.NO_DECISION_YET;
    }

    @Override
    public void displayGrid(Grid grid) {
        char[][] dataGrid = grid.getGrid();
        int height = grid.getHeight();
        int width = grid.getWidth();

        //Clear
        this.gridPanel.removeAll();

        //Panel
        JPanel gridInside = new JPanel();
        gridInside.setLayout(new GridLayout(height,width));

        //Fill
        IntStream.range(0, height).forEach(i ->{    //Java8 Lambda Functional loop, new feature v8
            IntStream.range(0, width).forEach(j ->{
                JButton square = new JButton();
                setGraphics(square, dataGrid[i][j]);    //set icon
                square.addActionListener(e -> actionPlay(i, j, grid));  //lambda function, new feature v8
                gridInside.add(square);
            });
        });
        this.gridPanel.add(gridInside);

        //Refresh
        this.gridPanel.revalidate();
        this.gridPanel.repaint();
    }

    @Override
    protected void displayStatus(String statusMsg, List<Integer> score) {
        //Clear
        this.statusPanel.removeAll();

        //Build component : score
        String scoreLine = "Score: ";
        for (int playerScore : score) {
            scoreLine +=  playerScore + "/" ;
        }
        JLabel scoreLabel = new JLabel(scoreLine, JLabel.CENTER);
        scoreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);     //TODO why align not working
        this.statusPanel.add(scoreLabel);

        //Build component : msg
        JLabel msgLabel = new JLabel(statusMsg, JLabel.CENTER);
        msgLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.statusPanel.add(msgLabel);

        //Build component : quit button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> actionQuit());
        quitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.statusPanel.add(quitButton);

        //Build component : resume button
        if(statusMsg.equals(UI.MSG_VICTORY) || statusMsg.equals(UI.MSG_DRAW)) {
            JButton resumeButton = new JButton("Resume");
            resumeButton.addActionListener(e -> actionResume());
            resumeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.statusPanel.add(resumeButton);
        }

        //Refresh
        this.statusPanel.revalidate();
        this.statusPanel.repaint();
    }

    private void setGraphics(JButton square, char tokenValue) {     //TODO: if you do a token class ("higher level"), you have to stay "high level" throughout the program so use Token everywhere, so grid not char but tokens, then you do all operations with token methods, the "lower level" is managed in Token class, thus you need to stay consistent, here tmp val until you take decision to make it higher level and make consistent your code
        String imagePath = "/images/";
        switch(tokenValue) {
            case '.':
                imagePath += "empty.png";
                break;
            case 'x':
                imagePath += "red.png";
                break;
            case 'o':
                imagePath += "blue.png";
                break;
            default:    //TODO tmp
                break;
        }

        URL tokenImg = getClass().getResource(imagePath);
        square.setIcon(new ImageIcon(tokenImg));
    }

    private void actionPlay(int i, int j, Grid grid) {
        char[][] dataGrid = grid.getGrid();

        if(dataGrid[i][j] == '.') { //Otherwise token positioned on taken position
            this.decision = Integer.toString(j);
        }
    }

    private void actionQuit() {
        this.decision = Game.QUIT_CMD;
    }

    private void actionResume() {
        this.decision = Game.RESUME_CMD;
    }

    @Override
    public String requestDecision() {
        String requested = this.decision;
        this.decision = UI.NO_DECISION_YET; //reset to no decision

        return requested;
    }

}
