package spaceinvaders;

import javafx.scene.image.Image;

public class Alien extends Sprite {

    private Bomb bomb;
    private final String shot = "/spacepix/alien.png";

    public Alien(int x, int y) {
        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);
        Image image = new Image(this.getClass().getResourceAsStream(shot));
        setImage(image);

    }

    public void act(int direction) {
        this.x += direction;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public class Bomb extends Sprite {

        private final String bomb = "/spacepix/bomb.png";
        private boolean destroyed;

        public Bomb(int x, int y) {
            setDestroyed(true);
            this.x = x;
            this.y = y;
            Image image = new Image(this.getClass().getResourceAsStream(bomb));
            setImage(image);
        }

        public void setDestroyed(boolean destroyed) {
            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {
            return destroyed;
        }
    }
}