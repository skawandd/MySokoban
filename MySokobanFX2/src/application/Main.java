package application;

import controller.Board;
import controller.Game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modele.Cell;

public class Main extends Application implements EventHandler<KeyEvent> {

	public static void main(String[] args) {

		launch(args);
		Game game = new Game();
		// game.askPlayerName();
		// game.getBoard().initWorld(1);
		game.Play();

		
		
	}

	public void start(Stage primaryStage) {

		// On met en place les éléments permettant de construire la matrice

		primaryStage.setTitle("Mon Sokoban");
		GridPane gridPane = new GridPane();
		Board board = new Board();
		int xLength = board.getBoardXSize();
		int yLength = board.getBoardYSize();
		gridPane.resize(xLength, yLength);
		Cell[][] tableauCellules = board.getMatrix();
		Scene scene = new Scene(gridPane, 400, 400);
		for (int i = 0; i < xLength; i++) {
			for (int j = 0; j < yLength; j++) {

				// Ajouter le gif correspondant à l'élément sol/floor à la
				// gridPane
				if(tableauCellules[i][j].getElement().getId() == 0){

					Image floor = new Image("ressources/floor.gif");
					ImageView iv0 = new ImageView(floor);
					//gridPane.getChildren().add(iv0);
					gridPane.add(iv0, j, i);
				}

				// Element mur id = 1
				
				if(tableauCellules[i][j].getElement().getId() == 1){
					
					Image wall = new Image("ressources/wall.gif");
					ImageView iv1 = new ImageView(wall);
					//gridPane.getChildren().add(iv1);
					gridPane.add(iv1, j, i);
				}

				// Element caisse id = 2
				if(tableauCellules[i][j].getElement().getId() == 2){
				
					Image boxOnFloor = new Image("ressources/box.gif");
					ImageView iv2 = new ImageView(boxOnFloor);
					//gridPane.getChildren().add(iv2);
					gridPane.add(iv2, j, i);
				}

				// Element caisse placée id = 3
				if(tableauCellules[i][j].getElement().getId() == 3){
					
					Image boxOnGoal = new Image("ressources/boxLocked.gif");
					ImageView iv3 = new ImageView(boxOnGoal);
					//gridPane.getChildren().add(iv3);
					gridPane.add(iv3, j, i);
				
				}
				// Element goal id = 4
				
				if(tableauCellules[i][j].getElement().getId() == 4){
				
					Image goal = new Image("ressources/zone.gif");
					ImageView iv4 = new ImageView(goal);
					//gridPane.getChildren().add(iv4);
					gridPane.add(iv4, j, i);
				}
				// Element perso id = 5
				if(tableauCellules[i][j].getElement().getId() == 5){
					Image character = new Image("ressources/perso.gif");
					ImageView iv5 = new ImageView(character);
					//gridPane.getChildren().add(iv5);
					gridPane.add(iv5, j, i);
					scene.setOnKeyPressed(this);
				}
				
				// Element persoZone id = 6
				if(tableauCellules[i][j].getElement().getId() == 6){
					
					Image characterOnGoal = new Image("ressources/persoZone.gif");
					ImageView iv6 = new ImageView(characterOnGoal);
					//gridPane.getChildren().add(iv6);
					gridPane.add(iv6, j, i);
				}
			}

		}
		
		
		gridPane.setGridLinesVisible(true);
		//scene.setOnKeyPressed(this);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public void handle(KeyEvent keyEvent) {

		
		Board board = new Board();
			if (keyEvent.getCode() == KeyCode.UP)
				board.moveCharacter(1);

			if (keyEvent.getCode() == KeyCode.DOWN)
				board.moveCharacter(2);

			if (keyEvent.getCode() == KeyCode.LEFT)
				board.moveCharacter(3);

			if (keyEvent.getCode() == KeyCode.RIGHT)
				board.moveCharacter(4);
		
	}

}
