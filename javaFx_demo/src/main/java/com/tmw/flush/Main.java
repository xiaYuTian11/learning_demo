package com.tmw.flush;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TMW
 * @date 2020/8/3 11:28
 */
public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        final Scene scene = new Scene(new Group(), 200, 400);
        Group sceneRoot = (Group) scene.getRoot();

        TreeItem<String> childNode1 = new TreeItem<>("Node 1");
        TreeItem<String> childNode2 = new TreeItem<>("Node 2");
        TreeItem<String> childNode3 = new TreeItem<>("Node 3");

        TreeItem<String> root = new TreeItem<>("Root");
        root.setExpanded(true);

        root.getChildren().setAll(childNode1, childNode2, childNode3);

        TreeTableColumn<String, String> column = new TreeTableColumn<>("Column");
        column.setPrefWidth(150);

        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> p) -> new ReadOnlyStringWrapper(
                p.getValue().getValue()));

        TreeTableView<String> treeTableView = new TreeTableView<>(root);
        treeTableView.getColumns().add(column);
        sceneRoot.getChildren().add(treeTableView);
        stage.setScene(scene);
        stage.show();

        Platform.runLater(() -> {
            try {
                Thread.sleep(1000 * 10);
                TreeItem<String> childNode11 = new TreeItem<>("Node 1-1");
                TreeItem<String> childNode22 = new TreeItem<>("Node 2-1");
                TreeItem<String> childNode33 = new TreeItem<>("Node 3-1");
                List<TreeItem<String>> list = new ArrayList<>();
                ObservableList<TreeItem<String>> children = root.getChildren();
                for (int size = children.size() - 1; size >= 0; size--) {
                    children.remove(size);
                }
                children.add(childNode11);
                children.add(childNode22);
                children.add(childNode33);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

}
