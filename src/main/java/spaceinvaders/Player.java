package spaceinvaders;

import javafx.scene.image.Image;

public class Player extends Sprite implements Commons{

    private final int START_Y = 280; 
    private final int START_X = 270;

    private final String player = "/spacepix/player.png";
    private int width;

    public Player() {

        Image image = new Image(this.getClass().getResourceAsStream(player));

        width = (int) image.getWidth(); 

        setImage(image);
        setX(START_X);
        setY(START_Y);
    }

    public void act() {
        x += dx;
        if (x <= 2) 
            x = 2;
        if (x >= BOARD_WIDTH - 2*width) 
            x = BOARD_WIDTH - 2*width;
    }

    public void setMovingLeft(boolean moving) {
        dx = moving ? -2 : 0;
    }

    public void setMovingRight(boolean moving) {
        dx = moving ? 2 : 0;
    }
}