package sample;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Main extends Application {

    public static int width = 800, height = width;
    public static int tileSize = width / 8;
    public static int TilesPerRow = 8;
    public static int[][] gameData = new int[TilesPerRow][TilesPerRow];
    public static int EMPTY = 3, RED = 1, WHITE = 2;
    public int currentPlayer = RED;
    public int rectX, rectY;
    public int circleX, circleY;
    public int numberOfRed = 0, numberOfWhite = 0;
    Pane root = new Pane();

    @Override
    public void start(Stage stage) {

        startGame(stage);
    }

    private void startGame(Stage stage) {
        Board checkersBoard = new Board();
        Scene scene = new Scene(root, width, height, Color.DIMGRAY);
        stage.setTitle("Warcaby");
        stage.setScene(scene);
        stage.show();
        checkersBoard.initializeBoard();
        paint();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void paint() { // rysowanie planszy
        for (int row = 0; row < TilesPerRow; row++) {
            for (int col = 0; col < TilesPerRow; col++) {
                if ((row % 2 == 0 && col % 2 == 0) || (row % 2 != 0 && col % 2 != 0)) {
                    Rectangle rect = new Rectangle(col * tileSize, row * tileSize, tileSize, tileSize);
                    rect.setFill(Color.LIGHTGRAY);
                    root.getChildren().add(rect);
                }

                if (gameData[col][row] == EMPTY) {
                    Rectangle rect = new Rectangle(col * tileSize, row * tileSize, tileSize, tileSize);
                    rect.setFill(Color.DIMGRAY);
                    root.getChildren().add(rect);

                    positionOfRect(rect);
                }

                if (gameData[col][row] == RED) {
                    Circle circle = new Circle(col * tileSize + tileSize / 2, row * tileSize + tileSize / 2, tileSize / 2);
                    circle.setCursor(Cursor.HAND);
                    circle.setFill(Color.RED);
                    circle.setStroke(Color.BLACK);
                    root.getChildren().add(circle);

                    positionOfCircle(circle);
                }

                if (gameData[col][row] == WHITE) {
                    Circle circle = new Circle(col * tileSize + tileSize / 2, row * tileSize + tileSize / 2, tileSize / 2);
                    circle.setCursor(Cursor.HAND);
                    circle.setFill(Color.WHITE);
                    circle.setStroke(Color.BLACK);
                    root.getChildren().add(circle);

                    positionOfCircle(circle);
                }
            }
        }
    }

    public void makeMove() {
        circleX = (circleX - 8) / tileSize;
        circleY = (circleY - 30) / tileSize;

        rectX = (rectX - 8) / tileSize;
        rectY = (rectY - 30) / tileSize;

        if (currentPlayer == RED && gameData[circleX][circleY] == RED && gameData[rectX][rectY] == EMPTY) {
            if ((circleX - 1 == rectX && circleY - 1 == rectY) || (circleX + 1 == rectX && circleY - 1 == rectY)) { // ruszanie czerwony o jedno pole
                gameData[circleX][circleY] = EMPTY;
                gameData[rectX][rectY] = RED;
                swapPlayer();  //ruch wykonany = zmiana gracza
            }

            if ((circleX + 2 == rectX && circleY - 2 == rectY) && (gameData[circleX + 1][circleY - 1] == WHITE)) { // bicie prawo gora
                gameData[circleX][circleY] = EMPTY;
                gameData[rectX][rectY] = RED;
                gameData[rectX - 1][rectY + 1] = EMPTY;
                swapPlayer();  //ruch wykonany = zmiana gracza
            }

            if ((circleX - 2 == rectX && circleY - 2 == rectY) && (gameData[circleX - 1][circleY - 1] == WHITE)) { // bicie lewo gora
                gameData[circleX][circleY] = EMPTY;
                gameData[rectX][rectY] = RED;
                gameData[rectX + 1][rectY + 1] = EMPTY;
                swapPlayer();  //ruch wykonany = zmiana gracza
            }
        }

        if (currentPlayer == WHITE && gameData[circleX][circleY] == WHITE && gameData[rectX][rectY] == EMPTY) {
            if ((circleX - 1 == rectX && circleY + 1 == rectY) || (circleX + 1 == rectX && circleY + 1 == rectY)) { // ruszanie bialy o jedno pole
                gameData[circleX][circleY] = EMPTY;
                gameData[rectX][rectY] = WHITE;
                swapPlayer();  //ruch wykonany = zmiana gracza
            }

            if ((circleX + 2 == rectX && circleY + 2 == rectY) && (gameData[circleX + 1][circleY + 1] == RED)) { // bicie prawo dol
                gameData[circleX][circleY] = EMPTY;
                gameData[rectX][rectY] = WHITE;
                gameData[rectX - 1][rectY - 1] = EMPTY;
                swapPlayer();  //ruch wykonany = zmiana gracza
            }

            if ((circleX - 2 == rectX && circleY + 2 == rectY) && (gameData[circleX - 1][circleY + 1] == RED)) { // bicie lewo dol
                gameData[circleX][circleY] = EMPTY;
                gameData[rectX][rectY] = WHITE;
                gameData[rectX + 1][rectY - 1] = EMPTY;
                swapPlayer();  //ruch wykonany = zmiana gracza
            }
        }
        paint(); //rysowanie planszy na nowo, po wykonaniu ruchu
        numberOfCircles();
    }

    public void swapPlayer() {
        if (currentPlayer == RED) {
            currentPlayer = WHITE;
            System.out.println("Ruch: BIAŁY");
        } else {
            currentPlayer = RED;
            System.out.println("Ruch: CZERWONY");
        }
    }

    public void positionOfCircle(Circle circle) {
        circle.setOnMousePressed((y) -> {
            circleX = (int) y.getSceneX();
            circleY = (int) y.getSceneY();
        });
    }

    public void positionOfRect(Rectangle rect) {
        rect.setOnMousePressed((y) -> {
            double sceneX = y.getSceneX();
            double sceneY = y.getSceneY();
            rectX = (int) sceneX;
            rectY = (int) sceneY;
            makeMove();
        });
    }

    public void numberOfCircles() {
        Display whoWin = new Display();
        numberOfWhite = 0;
        numberOfRed = 0;
        for (int i = 0; i < TilesPerRow; i++) {
            for (int j = 0; j < TilesPerRow; j++) {
                if (gameData[i][j] == RED)
                    numberOfRed++;

                if (gameData[i][j] == WHITE)
                    numberOfWhite++;
            }
        }

        if (numberOfRed == 0) {
            whoWin.gameOverDisplay(0);
        }
        if (numberOfWhite == 0)
            whoWin.gameOverDisplay(1);
    }

}

