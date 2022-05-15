package org.example;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView {
    private final Image[] duck = new Image[2];
    double width, height;

    int currentImg = 0;

    public Sprite(String fileName1, String fileName2, double locX, double locY, double width, double height) {
        super();

        duck[0] = new Image(fileName1, width, height, false, false);
        duck[1] = new Image(fileName2, width, height, false, false);

        this.width = width;
        this.height = height;

        setLayoutX(locX);
        setLayoutY(locY);


        if(locX >= 800){
            setScaleX(-getScaleX());
        }

        setImage(duck[currentImg]);

        AnimationTimer animationTimer = new AnimationTimer() {
            double time = 0.25;
            @Override
            public void handle(long l) {
                time-=0.01;

                if (time < 0) {
                    if(currentImg == 0){
                        currentImg++;
                    }else
                        currentImg--;
                    setImage(duck[currentImg]);

                    time = 0.25;
                    start();
                }

            }
        };
        animationTimer.start();

    }

    public Sprite(String fileName, double locX, double locY, double width, double height) {
        super();

        this.width = width;
        this.height = height;

        setLayoutX(locX);
        setLayoutY(locY);

        Image image = new Image(fileName, width, height, false, false);
        setImage(image);
    }

}
