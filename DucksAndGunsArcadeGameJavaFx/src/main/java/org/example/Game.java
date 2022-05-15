package org.example;


import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;


public class Game extends Pane {
    int duckCounter = 0;

    Interface anInterface;

    public Game(){
       super();

       newGame();
    }

    public void duckCreator(){
        Sprite duck;

        int side  = (int)(Math.random()*2);
        if(side == 0){
             duck = new Sprite("Duck1.png", "Duck2.png", -50 - (int)(Math.random()*150), 110 + 145 * (int)(Math.random()*4), 100,100);
        }
        else{
            duck = new Sprite("Duck1.png", "Duck2.png", 810 + (int)(Math.random()*140), 110 + 145 * (int)(Math.random()*4), 100,100);
        }

        getChildren().add(duck);


       AnimationTimer duckTimer = new AnimationTimer() {
           double time = 0.1;

           @Override
           public void handle(long l) {
               time -=0.01;

               if(time < 0){
                   if(duck.getLayoutX() < -200 || duck.getLayoutX() > 950){
                       getChildren().remove(duck);
                       duckCounter--;
                       stop();
                   }else{

                       if(side == 0)
                       duck.setLayoutX(duck.getLayoutX() + 7);
                       else
                       duck.setLayoutX(duck.getLayoutX() - 7);

                       time = 0.1;
                       start();
                   }
               }
           }
       };
       duckTimer.start();


    duck.setOnMouseClicked(e->{
        if(anInterface.getAvailableBullets() > 0 && anInterface.shootingMode()){
            getChildren().remove(duck);
            duckCounter--;
            duckTimer.stop();

            anInterface.increaseScore();
            anInterface.spendBullet();

            if(anInterface.getAvailableBullets() == 0){
                anInterface.reload();
            }
        }
    });

    }

    public void newGame(){
        anInterface = new Interface(0,0,800,800);
        getChildren().add(anInterface);

        AnimationTimer gameTimer = new AnimationTimer() {
            double time = 0.1;
            @Override
            public void handle(long l) {
                time -= 0.01;

                if(time < 0){
                    if(duckCounter < 10){
                        duckCreator();
                        duckCounter++;
                    }

                    time = 0.1;
                    start();
                }
            }
        };
        gameTimer.start();

        anInterface.setOnMouseClicked( e->{
            if(anInterface.getAvailableBullets() > 0 && anInterface.shootingMode()){
                anInterface.decreaseMisses();
                anInterface.spendBullet();
                if(anInterface.getMissesLeft() == 0){
                    getChildren().clear();
                    gameTimer.stop();
                    newGame();
                }

                if(anInterface.getAvailableBullets() == 0){
                    anInterface.reload();
                }
            }

        });


    }

}
