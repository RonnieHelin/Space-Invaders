package spaceinvaders;

import javafx.scene.image.Image;

public class Shot extends Sprite {

    private String shot = "/spacepix/shot.png";
    private final int H_SPACE = 6;
    private final int V_SPACE = 1;

    public Shot() {
    }

    public Shot(int x, int y) {

        Image image = new Image(this.getClass().getResourceAsStream(shot));
        setImage(image);
        setX(x + H_SPACE);
        setY(y - V_SPACE);
    }
}