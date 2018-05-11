/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Class to construct and manage main menu
 * @author Evan Smith
 */
public class RulloMenu {
    private final Controller controller;
    
    /**
     * Constructor
     * @param _controller   Controller handling menu
     */
    public RulloMenu(Controller _controller) {
        this.controller = _controller;
    }
    /**
     * Create and display menu scene (handle the main menu)
     * @param five  number of 5 x 5 wins
     * @param six   number of 6 x 6 wins
     * @param seven number of 7 x 7 wins
     * @param eight number of 8 x 8 wins
     * @return constructed scene to be called in stage
     */
    public Scene display(int five, int six, int seven, int eight) {
        Label label = new Label();
        label.setText("RULLO");
        label.setFont(new Font(48));
        Button fiveGrid = new Button("5 x 5");
        fiveGrid.setPrefSize(100, 30);
        fiveGrid.setOnAction(lambda -> {this.controller.createNewGame(5);});
        Label label5 = new Label("Completed: " + five);
        Button sixGrid = new Button("6 x 6");
        sixGrid.setPrefSize(100, 30);
        sixGrid.setOnAction(lambda -> {this.controller.createNewGame(6);});
        Label label6 = new Label("Completed: " + six);
        Button sevenGrid = new Button("7 x 7");
        sevenGrid.setPrefSize(100, 30);
        sevenGrid.setOnAction(lambda -> {this.controller.createNewGame(7);});
        Label label7 = new Label("Completed: " + seven);
        Button eightGrid = new Button("8 x 8");
        eightGrid.setPrefSize(100, 30);
        eightGrid.setOnAction(lambda -> {this.controller.createNewGame(8);});
        Label label8 = new Label("Completed: " + eight);
        Button reset = new Button("RESET");
        reset.setOnAction(lambda -> {this.controller.resetButtonPress();});
        reset.setPrefSize(100, 30);
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, fiveGrid, label5, sixGrid, label6, 
                sevenGrid, label7, eightGrid, label8, reset);
        layout.setAlignment(Pos.CENTER);
        
        return new Scene(layout, 300, 400);
    }
    
}
