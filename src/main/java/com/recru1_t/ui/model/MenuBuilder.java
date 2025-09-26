package com.recru1_t.ui.model;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Builder;

public class MenuBuilder implements Builder<Region>{
    private Menus menu;
    private Stage stage;
    private Map<Menus,Scene> scenes = new HashMap<>();

    public MenuBuilder(Menus menu,Stage stage, Map<Menus,Scene> scenes) {
        this.menu = menu;
        this.stage = stage;
        this.scenes = scenes;
    }
    
   private Region setup() {
        BorderPane root = new BorderPane();
        
        VBox layout = new VBox(10);
        VBox menuBar = new VBox(10);
        Label header = new Label(menu.getHeader());



        root.setLeft(menuBar);
        root.setRight(layout);

        root.setCenter(menu.getMenu().build(stage));
        for (Menus menus: Menus.values()){
            Label menuItem = new Label(menus.getMenuName());
            menuItem.setOnMouseClicked(e -> {
                stage.setScene(scenes.get(menus));
                stage.setTitle(menus.getMenuName());
            });
            menuItem.setStyle("-fx-cursor: hand; -fx-text-fill: white; -fx-font-size: 18px;");
            menuBar.getChildren().add(menuItem);

        }
        layout.getChildren().add(header);

        
        


        menuBar.setStyle("-fx-padding: 10px; -fx-background-color: #336699;");
        header.setStyle("-fx-font-size: 24px;");
        layout.setStyle("-fx-padding: 20px;");
        
        
        return root;
    }
    
    @Override
    public Region build() {
        
        return setup();
    }
    
}
