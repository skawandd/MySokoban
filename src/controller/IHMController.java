package controller;

import controller.ia.CPUPlayer;
import controller.tools.CSVElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import modele.Move;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modele.Tile;
import view.Menu;

public class IHMController extends Application {
	private volatile GameController game;
	private Move lastKey;
        CSVElement csv;
        
        public IHMController(){
            this.lastKey = Move.DOWN;
        }
        
        public static void main(String[] args) {
		Application.launch(IHMController.class, args);
	}


	@Override
	public void start(Stage primaryStage) {
            initCSV();
            game = new GameController(csv);

            GridPane gridPane = buildGrid();
            Scene scene = new Scene(gridPane);
            
            primaryStage.setTitle("MySokoban");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            if(!csv.isARobot()){
            scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent event) {
                    KeyCode key = event.getCode();
                    switch (key) {
                    case UP:
                        lastKey = Move.UP;
                        break;
                    case DOWN:
                        lastKey = Move.DOWN;
                        break;
                    case LEFT:
                        lastKey = Move.LEFT;
                        break;
                    case RIGHT:
                        lastKey = Move.RIGHT;
                        break;
                    case ENTER :
                        break;
                    case ESCAPE:
                        System.exit(0);
                        break;
                    default:
                        return;
                    }
                    switch(key){
                        case UP:
                        case DOWN :
                        case LEFT :
                        case RIGHT :
                            game.move(lastKey);
                    }
                    gridPane.getChildren().clear();
                    gridPane.getChildren().add(buildGrid());
                    event.consume();

                    if (game.checkWin()) {
                            showDialog(gridPane);
                    }
                }
            });
            }else{
                play(gridPane);
            }
            
	}
        private void play(GridPane gridPane){
            game.solve();
            List<Move> solution = game.getSolution();
            if(solution != null){
                for(Move move : solution){
                    game.move(move);
                    gridPane.getChildren().clear();
                    gridPane.getChildren().add(buildGrid());
                }
            }
            System.out.println(game.getLvl() + " coups");
            System.out.println(Menu.printSequence(solution));
        }

        /**
         * @brief load all the tiles of the grid
         * @return 
         */
	public GridPane buildGrid() {

		GridPane gridPane = new GridPane();
		int xLength = game.getGrid().getX();
		int yLength = game.getGrid().getY();
		gridPane.resize(xLength, yLength);
		byte[][] tableauCellules = game.getGrid().getGrid();
                String tilePath;

		for (int j = 0; j < yLength; j++){
                    for (int i = 0; i < xLength; i++) {
				// Ajouter le gif correspondant à l'élément à la gridPane
				switch (tableauCellules[i][j]){
                                        case Tile.MUR:
                                            tilePath = "file:src/view/sprites/mur.jpg";
                                            break;
                                        case Tile.BOX:
                                            tilePath = "file:src/view/sprites/caisse.jpg";
                                            break;
                                        case Tile.BOX_ON_GOAL:
                                            tilePath = "file:src/view/sprites/caisse_ok.jpg";
                                            break;
                                        case Tile.GOAL:
                                            tilePath = "file:src/view/sprites/objectif.png";
                                            break;
                                        case Tile.MARIO:
                                        case Tile.MARIO_ON_GOAL :
                                            tilePath = getMario(lastKey);
                                            break;
                                        default :
                                            tilePath = null;
                                    }
                                    if(tilePath!=null){
                                        Image image = new Image(tilePath);
                                        ImageView iv = new ImageView(image);
                                        gridPane.add(iv, i, j);
                                    }
			}
		}
		gridPane.setGridLinesVisible(true);
		return gridPane;
	}
	
	private String getMario(Move direction) {
            switch (direction) {
                case UP:
                        return "file:src/view/sprites/mario_haut.gif";
                case DOWN:
                default :
                        return "file:src/view/sprites/mario_bas.gif";
                case LEFT:
                        return "file:src/view/sprites/mario_gauche.gif";
                case RIGHT:
                        return "file:src/view/sprites/mario_droite.gif";
            }
        }

	private void showDialog(GridPane gridPane) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("VICTORY");
		alert.setHeaderText("YOU WIN !!!\n" + game.getMoves() + " moves in " + game.getTimer());

		ButtonType restart = new ButtonType("Restart");
		ButtonType exit = new ButtonType("Exit");

		// Remove default ButtonTypes
		alert.getButtonTypes().clear();

		alert.getButtonTypes().addAll(restart, exit);

		// option != null.
		Optional<ButtonType> option = alert.showAndWait();

		Label label = new Label();

		if (option.get() == null) {
                    label.setText("No selection!");
		} else if (option.get() == restart) {
                    //TODO relaucn real game, resize window
                    initCSV();
                    game = new GameController(csv);
                    lastKey = Move.DOWN;
                    gridPane.getChildren().clear();
                    gridPane.getChildren().add(buildGrid());
		} else if (option.get() == exit) {
			System.exit(0);
		}
	}
        
        /**
         * @brief init the CSV Grid that will be used by both IA and Human
         */
        private void initCSV(){
            boolean notInstanciated = true;
            do{
                File file = CSVElement.pick_CSVLevel();
                try {
                    this.csv = new CSVElement(file);
                    notInstanciated = false;
                } catch (FileNotFoundException ex) {
                    System.err.println("Unable to read specified file");
                }
            }while(notInstanciated);
        }

}
