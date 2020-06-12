package com.tmw.png;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * @author TMW
 * @since 2020/5/14 15:09
 */
public class PngMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image("./img/wallhaven-183124.jpg"));

        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.setWidth(0);

        primaryStage.setHeight(0);

        primaryStage.show();

        Stage visibleStage = new Stage();

        visibleStage.initOwner(primaryStage);

        visibleStage.getIcons().add(new Image("img/wallhaven-183124.jpg"));

        // visibleStage.setScene(new Scene(...));

        visibleStage.setOnHidden(e -> Platform.runLater(primaryStage::hide));

        visibleStage.show();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(primaryStage);

    }
}
