package com.tmw.tree;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        TreeView<Node> treeView = new TreeView<>();
        BorderPane borderPane = new BorderPane();
        Label label = new Label("hello world!");
        borderPane.setCenter(label);
        HBox hBox = new HBox();
        TextField textField = new TextField();
        Button button1 = new Button("选择文件");
        Button button2 = new Button("上传文件");
        hBox.getChildren().addAll(textField, button1, button2);
        borderPane.setCenter(hBox);
        HBox.setHgrow(textField, Priority.ALWAYS);

        CheckBoxTreeItem<Node> treeItem1 = new CheckBoxTreeItem<>(createOrgNode(new TreeNode("1", "张三")));
        CheckBoxTreeItem<Node> treeItem11 = new CheckBoxTreeItem<>(createOrgNode(new TreeNode("11", "张三11")));
        CheckBoxTreeItem<Node> treeItem22 = new CheckBoxTreeItem<>(createOrgNode(new TreeNode("22", "张三22")));
        CheckBoxTreeItem<Node> treeItem2222 = new CheckBoxTreeItem<>(createOrgNode(new TreeNode("2222", "张三2222")));
        treeItem22.getChildren().add(treeItem2222);
        // treeItem1.getChildren().addAll(treeItem11, treeItem22);
        treeItem1.getChildren().add(treeItem11);
        treeItem1.getChildren().add(treeItem22);

        treeView.setRoot(treeItem1);
        treeView.setCellFactory(param -> new MyTreeCell());

        borderPane.setCenter(treeView);

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(borderPane, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Node createOrgNode(TreeNode treeNode) {
        final String id = treeNode.getId();
        final String name = treeNode.getName();
        HBox hBox = new HBox();
        hBox.setId(id + "_org_left_hbox");
        hBox.getStyleClass().add("sub-hbx");
        return hBox;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static class MyTreeCell extends TreeCell<Node> {

        @Override

        protected void updateItem(Node item, boolean empty) {

            super.updateItem(item, empty);

            if (item == null) {

                this.setText("");

            } else {
                // this.setId();
                this.setText(item.getId());

            }

        }

    }
}
