package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Display extends Main {

    public void gameOverDisplay(int whoWin)
    {
        if(whoWin == 0) {
            Stage primaryStage = new Stage();
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(primaryStage);
            dialog.setTitle("Zakonczono gre.");
            dialog.setMinWidth(300);
            dialog.setMinHeight(50);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Biały wygrywa grę!"));
            dialogVbox.setAlignment(Pos.CENTER);
            Scene dialogScene = new Scene(dialogVbox, 300, 50);
            dialog.setScene(dialogScene);
            dialog.show();
        }
        else {
            Stage primaryStage = new Stage();
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(primaryStage);
            dialog.setTitle("Zakonczono gre.");
            dialog.setMinWidth(300);
            dialog.setMinHeight(50);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Czerwony wygrywa grę!"));
            dialogVbox.setAlignment(Pos.CENTER);
            Scene dialogScene = new Scene(dialogVbox, 300, 50);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }
}
