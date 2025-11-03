package spaceinvaders;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Board extends Canvas implements Commons { 

    private GraphicsContext gc;
    private ArrayList <Alien> aliens;
    private Player player;
    private Shot shot;

    private int alienX = 150;
    private int alienY = 5;
    private int direction = -1;
    private int deaths = 0;

    private boolean ingame = true;
    private final String expl = "/spacepix/explosion.png";
    private final String alienpix = "/spacepix/alien.png";
    private String message = "Game Over";

    private AnimationTimer animationTimer;

    public Board() 
    {
        super(BOARD_WIDTH, BOARD_HEIGTH);
        this.gc = getGraphicsContext2D();
        
        setFocusTraversable(true);
        
        // Set up key event handling
        setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
        setOnKeyReleased(e -> handleKeyReleased(e.getCode()));

        gameInit();
    }

    public void gameInit() {

        aliens = new ArrayList<Alien>();

        Image alienImage = new Image(this.getClass().getResourceAsStream(alienpix));

        for (int i=0; i < 4; i++) {
            for (int j=0; j < 6; j++) {
                Alien alien = new Alien(alienX + 18*j, alienY + 18*i);
                alien.setImage(alienImage);
                aliens.add(alien);
            }
        }

        player = new Player();
        shot = new Shot();

        if (animationTimer == null || !ingame) {
            animationTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (ingame) {
                        draw();
                        animationCycle();
                    } else {
                        stop();
                        gameOver();
                    }
                }
            };
            animationTimer.start();
        }
    }

    public void drawAliens() 
    {
    	for (Alien alien : aliens) {

            if (alien.isVisible()) {
                gc.drawImage(alien.getImage(), alien.getX(), alien.getY());
            }

            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    public void drawPlayer() {

        if (player.isVisible()) {
            gc.drawImage(player.getImage(), player.getX(), player.getY());
        }

        if (player.isDying()) {
            player.die();
            ingame = false;
        }
    }

    public void drawShot() {
        if (shot.isVisible())
            gc.drawImage(shot.getImage(), shot.getX(), shot.getY());
    }

    public void drawBombing() {

    	for (Alien a : aliens) {
            Alien.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {
                gc.drawImage(b.getImage(), b.getX(), b.getY()); 
            }
        }
    }

    public void draw()
    {
      // Clear the canvas
      gc.setFill(Color.BLACK);
      gc.fillRect(0, 0, getWidth(), getHeight());
      gc.setStroke(Color.GREEN);   

      if (ingame) {

        gc.strokeLine(0, GROUND, BOARD_WIDTH, GROUND);
        drawAliens();
        drawPlayer();
        drawShot();
        drawBombing();
      }
    }

    public void gameOver()
    {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGTH);

        gc.setFill(Color.rgb(0, 32, 48));
        gc.fillRect(50, BOARD_WIDTH/2 - 30, BOARD_WIDTH-100, 50);
        gc.setStroke(Color.WHITE);
        gc.strokeRect(50, BOARD_WIDTH/2 - 30, BOARD_WIDTH-100, 50);

        Font font = Font.font("Helvetica", FontWeight.BOLD, 14);
        gc.setFont(font);
        gc.setFill(Color.WHITE);
        
        // Calculate text width for centering (approximate)
        double textWidth = message.length() * 8; // Approximate character width
        gc.fillText(message, (BOARD_WIDTH - textWidth)/2, BOARD_WIDTH/2);
    }

    public void animationCycle()  {

        if (deaths == NUMBER_OF_ALIENS_TO_DESTROY) {
            ingame = false;
            message = "Game won!";
        }

        // player

        player.act();

        // shot
        if (shot.isVisible()) {
            int shotX = shot.getX();
            int shotY = shot.getY();

            for (Alien alien : aliens) {
                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible()) {
                        if (shotX >= (alienX) && 
                        shotX <= (alienX + ALIEN_WIDTH) &&
                        shotY >= (alienY) &&
                        shotY <= (alienY+ALIEN_HEIGHT) ) {
                            Image explImage = new Image(getClass().getResourceAsStream(expl));
                            alien.setImage(explImage);
                            alien.setDying(true);
                            deaths++;
                            shot.die();
                        }
                }
            }

            int y = shot.getY();
            y -= 4;
            if (y < 0)
                shot.die();
            else shot.setY(y);
        }

        // aliens


         for (Alien a1 : aliens) {
             int x = a1.getX();

             if (x  >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {
                 direction = -1;
                 for (Alien a2 : aliens) {
                     a2.setY(a2.getY() + GO_DOWN);
                 }
             }

            if (x <= BORDER_LEFT && direction != 1) {
                direction = 1;

                for (Alien a : aliens) {
                    a.setY(a.getY() + GO_DOWN);
                }
            }
        }


        for (Alien alien : aliens) {
            if (alien.isVisible()) {

                int y = alien.getY();

                if (y > GROUND - ALIEN_HEIGHT) {
                    ingame = false;
                    message = "Invasion!";
                }

                alien.act(direction);
            }
        }

        // bombs

        Random generator = new Random();
        

        for (Alien a : aliens) {
            int shot = generator.nextInt(15);
            Alien.Bomb b = a.getBomb();
            if (shot == CHANCE && a.isVisible() && b.isDestroyed()) {

                b.setDestroyed(false);
                b.setX(a.getX());
                b.setY(a.getY());   
            }

            int bombX = b.getX();
            int bombY = b.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !b.isDestroyed()) {
                if ( bombX >= (playerX) && 
                    bombX <= (playerX+PLAYER_WIDTH) &&
                    bombY >= (playerY) && 
                    bombY <= (playerY+PLAYER_HEIGHT) ) {
                        Image explImage = new Image(this.getClass().getResourceAsStream(expl));
                        player.setImage(explImage);
                        player.setDying(true);
                        b.setDestroyed(true);;
                    }
            }

            if (!b.isDestroyed()) {
                b.setY(b.getY() + 1);   
                if (b.getY() >= GROUND - BOMB_HEIGHT) {
                    b.setDestroyed(true);
                }
            }
        }
    }

    private void handleKeyPressed(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT) {
            player.setMovingLeft(true);
        } else if (keyCode == KeyCode.RIGHT) {
            player.setMovingRight(true);
        } else if (keyCode == KeyCode.SPACE && ingame) {
            if (!shot.isVisible()) {
                shot = new Shot(player.getX(), player.getY());
            }
        }
    }

    private void handleKeyReleased(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT) {
            player.setMovingLeft(false);
        } else if (keyCode == KeyCode.RIGHT) {
            player.setMovingRight(false);
        }
    }
}