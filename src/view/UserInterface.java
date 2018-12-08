package view;

import java.util.Optional;

import controller.Game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modele.Cell;

public class UserInterface extends Application {
	private volatile Game game;

	@Override
	public void start(Stage primaryStage) {

		game = new Game();
		new Thread(game).start();
		primaryStage.setTitle("MySokoban");
		GridPane gridPane = buildGrid();

		Scene scene = new Scene(gridPane);
		primaryStage.setScene(scene);
		primaryStage.show();

		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				Menu menu = new Menu(game);

				switch (event.getCode()) {

				case UP:
					game.move(1);
					System.out.println("up");
					menu.showBoard();
					gridPane.getChildren().clear();
					gridPane.getChildren().add(buildGrid());
					break;

				case DOWN:
					game.move(2);
					System.out.println("down");
					menu.showBoard();
					gridPane.getChildren().clear();
					gridPane.getChildren().add(buildGrid());
					break;

				case LEFT:
					game.move(3);
					System.out.println("left");
					menu.showBoard();
					gridPane.getChildren().clear();
					gridPane.getChildren().add(buildGrid());
					break;

				case RIGHT:
					game.move(4);
					System.out.println("right");
					menu.showBoard();
					gridPane.getChildren().clear();
					gridPane.getChildren().add(buildGrid());
					break;

				case ENTER:
					game = new Game();
					gridPane.getChildren().clear();
					gridPane.getChildren().add(buildGrid());
					break;

				case ESCAPE:
					System.exit(0);
					break;
				default:
					break;
				}
				event.consume();
				
				if(game.checkWin()) {
					showDialog(gridPane);
				}
			}
		});
	}

	public GridPane buildGrid() {

		GridPane gridPane = new GridPane();
		int xLength = game.getBoard().getMatrix()[0].length;
		int yLength = game.getBoard().getMatrix()[1].length;
		gridPane.resize(xLength, yLength);
		Cell[][] tableauCellules = game.getBoard().getMatrix();

		for (int i = 0; i < xLength; i++) {
			for (int j = 0; j < yLength; j++) {

				// Ajouter le gif correspondant à l'élément sol/floor à la
				// gridPane
				if (tableauCellules[i][j].isEmpty() && tableauCellules[i][j].getFloor().getId() == 0) {
					/*
					 * Image floor = new Image("file:src/ressources/floor.gif");
					 * ImageView iv0 = new ImageView(floor); //
					 * gridPane.getChildren().add(iv0);
					 * System.out.println("wall"); gridPane.add(iv0, j, i);
					 */
				}

				// Element mur id = 1
				if (tableauCellules[i][j].getElement().getId() == 1) {
					Image wall = new Image("file:src/ressources/wall.PNG");
					ImageView iv1 = new ImageView(wall);
					gridPane.add(iv1, j, i);
				}

				// Element caisse id = 2
				if (tableauCellules[i][j].getElement().getId() == 2) {
					Image boxOnFloor = new Image("file:src/ressources/box.PNG");
					ImageView iv2 = new ImageView(boxOnFloor);
					gridPane.add(iv2, j, i);
				}

				// Element caisse placée id = 3
				if (tableauCellules[i][j].getElement().getId() == 3) {
					Image boxOnGoal = new Image("file:src/ressources/boxLocked.PNG");
					ImageView iv3 = new ImageView(boxOnGoal);
					gridPane.add(iv3, j, i);

				}

				// Element goal id = 4
				if (tableauCellules[i][j].isEmpty() && tableauCellules[i][j].getFloor().getId() == 4) {
					Image goal = new Image("file:src/ressources/goal.PNG");
					ImageView iv4 = new ImageView(goal);
					gridPane.add(iv4, j, i);
				}

				// Element perso id = 5
				if (tableauCellules[i][j].getElement().getId() == 5) {
					Image character = new Image("file:src/ressources/perso.png");
					ImageView iv5 = new ImageView(character);
					gridPane.add(iv5, j, i);
				}

				// Element persoZone id = 6
				if (tableauCellules[i][j].getElement().getId() == 6) {

					Image characterOnGoal = new Image("file:src/ressources/persoZone.png");
					ImageView iv6 = new ImageView(characterOnGoal);
					gridPane.add(iv6, j, i);
				}
			}

		}
		gridPane.setGridLinesVisible(true);

		return gridPane;
	}
	
	  private void showDialog(GridPane gridPane) {
		  
	        Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("VICTORY");
	        alert.setHeaderText("YOU WIN !!!\n"+game.getMoves()+" moves in "+game.getTimer());
	 
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
	        	game = new Game();
				gridPane.getChildren().clear();
				gridPane.getChildren().add(buildGrid());
	        } else if (option.get() == exit) {
	            System.exit(0);
	        }
	    }

}
