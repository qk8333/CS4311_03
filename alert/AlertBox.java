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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
       
       Label label = new Label();
       label.setText(message);
       label.setFont(new Font(14));
       Button button = new Button("Close");
       button.setPrefSize(100, 30);
       button.setOnAction(lambda -> window.close());
       
       VBox layout = new VBox(20);
       layout.getChildren().addAll(label, button);
       layout.setAlignment(Pos.CENTER);
       
       StackPane stack = new StackPane();
       stack.getChildren().addAll(new Rectangle(350, 100, Color.LIGHTGREY), layout);
       
       Scene scene = new Scene(stack);
       window.setScene(scene);
       window.showAndWait();
    }
    
}
