/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alert;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * Generic class builder of an alert box
 * @author Evan Smith
 */
public class AlertBox {
    /**
     * Builds and deploys a generic alert box
     * @param title title of the alert box
     * @param message   message in the alert box
     */
    public static void display(String title, String message) {
       Stage window = new Stage();
       window.initModality(Modality.APPLICATION_MODAL);
       window.setTitle(title);
       window.setWidth(250);
       window.setHeight(140);
       
       Label label = new Label();
       label.setFont(new Font("Bodoni", 15));
       label.setText(message);
       Button button = new Button("Close");
       button.setOnAction(lambda -> window.close());
       
       
       StackPane layout = new StackPane(label, button);
       layout.setStyle("-fx-background-color: #D3D3D3");
       StackPane.setAlignment(label, Pos.CENTER);
       StackPane.setAlignment(button, Pos.BOTTOM_CENTER);
       
       
       Scene scene = new Scene(layout);
       window.setScene(scene);
       window.showAndWait();
    }
    
}
