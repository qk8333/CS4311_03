/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import Rullo.rullo.Rullo;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.lang.String;
/**
 *
 * @author Brendan
 */
public class view extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rullo");
        TextField input = new TextField("Enter size of board. One int between 3 and 5");
        Button btn1 = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        Button btn4 = new Button();
        Button btn5 = new Button();
        Button btn6 = new Button();
        Button btn7 = new Button();
        Button btn8 = new Button();
        Button btn9 = new Button();
        Button btn10 = new Button();
        Button btn11 = new Button();
        Button btn12 = new Button();
        Button btn13 = new Button();
        Button btn14 = new Button();
        Button btn15 = new Button();
        Button btn16 = new Button();
        Button btn17 = new Button();
        Button btn18 = new Button();
        Button btn19 = new Button();
        Button btn20 = new Button();
        Button btn21 = new Button();
        Button btn22 = new Button();
        Button btn23 = new Button();
        Button btn24 = new Button();
        Button btn25 = new Button();
        input.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                int size = Integer.parseInt(input.getCharacters().toString());
                Rullo game = new Rullo(size);
            switch(size){
            case 1: size = 3;
                btn1.setText(String.valueOf(game.getValue(0, 0));
                break;
            case 2: size = 4;
                break;
            case 3: size = 5;
                break;
            }
        };
        
        StackPane root = new StackPane();
        primaryStage.show();
    }
}