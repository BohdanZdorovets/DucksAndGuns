package org.example;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Interface extends Pane {
    double width, height;

    private Sprite[] lives;
    private Label missesLabel;
    private int livesLeft;
    private int missesLeft;

    private Label scoreLabel;
    private int score;

    private Sprite[] bullets;
    private int availableBullets;

    private boolean shootingMode;


    public Interface(double locX, double locY, double width, double height){
        super();
        this.width = width;
        this.height = height;

        setLayoutX(locX);
        setLayoutY(locY);

        availableBullets = 5;
        shootingMode = true;

        designAdder();
    }

    public void increaseScore(){
        score++;
        scoreLabel.setText("Score : " + score);
    }

    public void decreaseMisses() {

        missesLeft--;
        if(missesLeft % 10 == 0 && missesLeft != 30){
          getChildren().remove(lives[missesLeft/10]);
        }

        missesLabel.setText("Misses left : " + missesLeft);
    }


    public void reload(){
        shootingMode = false;

        AnimationTimer reloadTimer = new AnimationTimer() {
           double time = 0.5;
            @Override
            public void handle(long l) {
                time -= 0.01;

                if(time < 0){
                    if(availableBullets < 5){
                        getChildren().add(bullets[availableBullets]);
                        availableBullets++;

                        time = 0.5;
                        start();
                    }else{
                        shootingMode = true;
                        stop();
                    }
                }
            }
        };
        reloadTimer.start();
    }

    public void spendBullet(){
        availableBullets--;
        getChildren().remove(bullets[availableBullets]);
    }

    public void designAdder(){
        constantDesignAdder();
        dynamicDesignAdder();
    }

    public void dynamicDesignAdder(){
        score = 0;
        scoreLabel = new Label("Score : " + score);
        scoreLabel.setFont(new Font("Arial Black", 30));
        scoreLabel.setLayoutX(30);
        scoreLabel.setLayoutY(30);

        livesLeft = 3;
        lives = new Sprite[livesLeft];
        for (int i = 0; i < livesLeft; i++) {
            lives[i] = new Sprite("Heart.png", 600 + i*60, 25, 50,50);
            getChildren().add(lives[i]);
        }

        missesLeft = 30;
        missesLabel = new Label("Misses left : " + missesLeft);
        missesLabel.setFont(new Font("Arial Black", 30));
        missesLabel.setLayoutX(30);
        missesLabel.setLayoutY(height - 90);
        getChildren().addAll(scoreLabel, missesLabel);

        availableBullets = 5;
        bullets = new Sprite[availableBullets];
        for (int i = 0; i < availableBullets; i++) {
            bullets[i] = new Sprite("Ammo.png", 485 + i*60, height-110, 50,75);
            getChildren().add(bullets[i]);
        }

    }

    public void constantDesignAdder(){
        Rectangle upperDesk = new Rectangle(width, 100, Color.BURLYWOOD);
        upperDesk.setLayoutY(0);
        upperDesk.setLayoutX(0);

        Rectangle board = new Rectangle(285, 100, Color.BEIGE);
        board.setLayoutY(0);
        board.setLayoutX(250);

        Sprite logo = new Sprite("Logo.png", 315, 5, 150, 75);

        Rectangle upperLine = new Rectangle(width, 15, Color.BEIGE);
        upperLine.setLayoutX(0);
        upperLine.setLayoutY(85);

        Rectangle background = new Rectangle(width, height, Color.SKYBLUE);
        getChildren().add(background);

        Rectangle lowerLine = new Rectangle(width, 15, Color.BEIGE);
        lowerLine.setLayoutX(0);
        lowerLine.setLayoutY(height - 150);

        Rectangle lowerDesk = new Rectangle(width, 150, Color.BURLYWOOD);
        lowerDesk.setLayoutY(height - 150);
        lowerDesk.setLayoutX(0);

        getChildren().addAll(upperDesk, board, upperLine, logo, lowerDesk, lowerLine);
    }


    public int getAvailableBullets() {
        return availableBullets;
    }

    public boolean shootingMode() {
        return shootingMode;
    }

    public int getMissesLeft() {
        return missesLeft;
    }
}
