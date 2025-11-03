package spaceinvaders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SpaceInvaders extends Application implements Commons {

    @Override
    public void start(Stage primaryStage) {
        Board board = new Board();
        Pane root = new Pane();
        root.getChildren().add(board);
        
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGTH);
        
        primaryStage.setTitle("Space Invaders");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        // Request focus for keyboard input
        board.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}