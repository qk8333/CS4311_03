/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Class to construct and manage active game
 * @author Evan Smith
 */
public class GameGrid {
    private final Controller controller;
    
    /**
     * Constructor, initializes controller object
     * @param _controller   controller 
     */
    public GameGrid(Controller _controller) {
        this.controller = _controller;
    }
    
    /**
     * Static function to create a new JavaFX Scene
     * @param rows  ArrayList object for the row key
     * @param columns   ArrayList object for the column key
     * @param board ArrayList matrix containing board values
     * @return  new Scene
     */
    public Scene display(ArrayList<Integer> rows, ArrayList<Integer> columns,
            ArrayList<ArrayList<Integer>> board) {
        BorderPane base = new BorderPane(); // Top, bottom, left, right, center
        VBox top = new VBox();
        top.getChildren().addAll(this.menuGrid(rows.size()), this.columnKeyGrid(columns));
        base.setTop(top);
        base.setBottom(this.columnKeyGrid(columns));
        base.setLeft(this.rowKeyGrid(rows));
        base.setRight(this.rowKeyGrid(rows));
        base.setCenter(this.gameGrid(board));
        
        return new Scene(base);
    }
    
    /**
     * Function to create game grid
     * @param board ArrayList matrix of Integer values
     * @return  newly constructed GridPane
     */
    private GridPane gameGrid(ArrayList<ArrayList<Integer>> board) {
        GridPane game = new GridPane();
        ArrayList<ArrayList<ToggleButton>> grid = new ArrayList<>();
        for (int row = 0; row < board.size(); row++) {
            final int finalRow = row;
            grid.add(new ArrayList<>());
            for (int column = 0; column < board.get(0).size(); column++) {
                final int finalColumn = column;
                grid.get(row).add(new ToggleButton(board.get(row).get(column).toString()));
                grid.get(row).get(column).setPrefSize(50, 50);
                grid.get(row).get(column).setOnAction(lambda -> {this.controller.gridButtonPress(finalRow, finalColumn);});
                game.add(grid.get(row).get(column), column, row);
            }
        }
        return game;
    }
    
    /**
     * Function to create a row key
     * @param columnKey    ArrayList of column key values
     * @return  newly constructed GridPane
     */
    private GridPane columnKeyGrid(ArrayList<Integer> columnKey) {
        GridPane grid = new GridPane();
        grid.add(new Rectangle(50, 50, Color.LIGHTGREY), 0, 0);
        for (int row = 0; row < columnKey.size(); row++) {
            StackPane temp = new StackPane();
            temp.getChildren().addAll(new Rectangle(50, 50, Color.LIGHTGRAY), 
                    new Text(columnKey.get(row).toString()));
            grid.add(temp, row + 1, 0);
        }
       grid.add(new Rectangle(50, 50, Color.LIGHTGREY), columnKey.size()+1, 0);
        return grid;
    }
    
    /**
     * Function to create a column key
     * @param rowKey ArrayList of row key values
     * @return  newly constructed GridPane
     */
    private GridPane rowKeyGrid(ArrayList<Integer> rowKey) {
        GridPane grid = new GridPane();
        for (int column = 0; column < rowKey.size(); column++) {
            StackPane temp = new StackPane();
            temp.getChildren().addAll(new Rectangle(50, 50, Color.LIGHTGRAY), 
                    new Text(rowKey.get(column).toString()));
            grid.add(temp, 0, column);
        }
        return grid;
    }
    
    /**
     * Function to construct top menu
     * @param size  size of grid
     * @return  newly constructed GridPane
     */
    private GridPane menuGrid(int size) {
        GridPane grid = new GridPane();
        Button menu = new Button("< Menu");
        menu.setPrefSize(100, 30);
        menu.setOnAction(lambda -> {this.controller.menuButtonPress();});
        Button skip = new Button(" Skip >");
        skip.setPrefSize(100, 30);
        skip.setOnAction(lambda -> {this.controller.createNewGame();});
        grid.add(menu, 0, 0);
        grid.add(new Rectangle((size * 50) - 100, 30, Color.LIGHTGREY), 1, 0);
        grid.add(skip, size + 2, 0);
        return grid;
    }
    
}
