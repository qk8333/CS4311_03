/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import alert.AlertBox;
import javafx.stage.Stage;
import rullo.Rullo;
import userdata.CompletedData;
import view.GameGrid;
import view.RulloMenu;

/**
 * Controller class to handle and manage data
 * @author Evan Smith
 */
public class Controller {
    private Stage stage;
    private RulloMenu menu;
    private GameGrid grid;
    private Rullo rullo;
    private CompletedData data;
    private int size;
    
    /**
     * Constructor, initializes new user data
     * @param _stage    stage to handle
     */
    public Controller(Stage _stage) {
        this.stage = _stage;
        this.data = new CompletedData();
        this.menu = new RulloMenu(this);
        this.grid = new GameGrid(this);
    }
    
    /**
     * Initialize the program
     */
    public void start() {
        this.stage.setScene(this.menu.display(this.data.getFiveGrid(),
                this.data.getSixGrid(), this.data.getSevenGrid(), 
                this.data.getEightGrid()));
        this.stage.show();
    }
    
    /**
     * Handler for the skip button in the Game grid using currently stored size
     */
    public void createNewGame() {
        this.rullo = new Rullo(this.size);
        // Make sure game isn't a blank solution
        while (this.rullo.solved() == true) {
            this.rullo = new Rullo(this.size);
        }
        this.stage.setScene(this.grid.display(this.rullo.getRowKey(), 
                this.rullo.getColumnKey(), this.rullo.getBoard()));
        this.stage.show();
    }
    
    /**
     * Handler for the skip button in the Game grid
     * @param _size  size of the new game to be created 
     */
    public void createNewGame(int _size) {
        this.size = _size;
        this.rullo = new Rullo(this.size);
        // Make sure game isn't a blank solution
        while (this.rullo.solved() == true) {
            this.rullo = new Rullo(this.size);
        }
        this.stage.setScene(this.grid.display(this.rullo.getRowKey(), 
                this.rullo.getColumnKey(), this.rullo.getBoard()));
        this.stage.show();
    }
    
    /**
     * Handler for the menu button in the GameGrid
     */
    public void menuButtonPress() {
        this.start();
    }
    
    /**
     * Handler function for ToggleButton changes on the game grid, outputs
     * alert notification when puzzle has effectively been solved
     * @param row   row address of button in grid
     * @param column    column address of button in grid
     */
    public void gridButtonPress(int row, int column) {
        this.rullo.setCell(row, column);
        if (this.rullo.solved()) {
            switch (this.size) {
                case 5:
                    this.data.incrementFiveGrid();
                    break;
                case 6:
                    this.data.incrementSixGrid();
                    break;
                case 7:
                    this.data.incrementSevenGrid();
                    break;
                case 8:
                    this.data.incrementEightGrid();
                    break;
                default:
                    break;
            };
            AlertBox.display("Congratulations!", "Good work solving that puzzle!");
            this.createNewGame(this.size);
        }
        else{}; 
    }
    
    /**
     * Handler for reset button
     */
    public void resetButtonPress() {
        this.data.reset();
        this.menuButtonPress();
    }
    
}
