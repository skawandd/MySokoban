package view;

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

public class UserInterface extends Application {
	private volatile Game game;
	
	@Override
	public void start(Stage primaryStage) {

		game = new Game();
		new Thread(game).start();
		primaryStage.setTitle("Mon Sokoban");
		// GridPane gridPane = new GridPane();
		Board board = new Board();
		GridPane gridPane = buildGrid();

		Scene scene = new Scene(gridPane, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				Menu menu = new Menu(game);
				if (event.getCode() == KeyCode.UP) {
					game.getBoard().moveCharacter(1);
					System.out.println("up");
					menu.showBoard();
					gridPane.getChildren().clear();
					gridPane.getChildren().add(buildGrid());
				}

				if (event.getCode() == KeyCode.DOWN) {
					game.getBoard().moveCharacter(2);
					System.out.println("down");
					menu.showBoard();
					gridPane.getChildren().clear();
					gridPane.getChildren().add(buildGrid());
				}

				if (event.getCode() == KeyCode.LEFT) {
					game.getBoard().moveCharacter(3);
					System.out.println("left");
					menu.showBoard();
					gridPane.getChildren().clear();
					gridPane.getChildren().add(buildGrid());
				}

				if (event.getCode() == KeyCode.RIGHT) {
					game.getBoard().moveCharacter(4);
					System.out.println("right");
					menu.showBoard();
					gridPane.getChildren().clear();
					gridPane.getChildren().add(buildGrid());
				}

			//	event.consume();
			}
		});
	}

	public GridPane buildGrid() {

		GridPane gridPane = new GridPane();
		int xLength = game.getBoard().getMatrix()[0].length;
		int yLength = game.getBoard().getMatrix()[1].length;
		gridPane.resize(xLength, yLength);
		Cell[][] tableauCellules = game.getBoard().getMatrix();
		// Scene scene = new Scene(gridPane, 400, 400);

		for (int i = 0; i < xLength; i++) {
			for (int j = 0; j < yLength; j++) {

				// Ajouter le gif correspondant à l'élément sol/floor à la
				// gridPane
				if (tableauCellules[i][j].getElement().getId() == 0) {
					Image floor = new Image("file:src/ressources/floor.gif");
					ImageView iv0 = new ImageView(floor);
					// gridPane.getChildren().add(iv0);
					gridPane.add(iv0, j, i);
				}

				// Element mur id = 1
				if (tableauCellules[i][j].getElement().getId() == 1) {
					Image wall = new Image("file:src/ressources/wall.gif");
					ImageView iv1 = new ImageView(wall);
					gridPane.add(iv1, j, i);
					// gridPane.getChildren().add(iv1);
				}

				// Element caisse id = 2
				if (tableauCellules[i][j].getElement().getId() == 2) {
					Image boxOnFloor = new Image("file:src/ressources/box.gif");
					ImageView iv2 = new ImageView(boxOnFloor);
					gridPane.add(iv2, j, i);
				}

				// Element caisse placée id = 3
				if (tableauCellules[i][j].getElement().getId() == 3) {
					Image boxOnGoal = new Image("file:src/ressources/boxLocked.gif");
					ImageView iv3 = new ImageView(boxOnGoal);
					gridPane.add(iv3, j, i);

				}

				// Element goal id = 4
				if (tableauCellules[i][j].getElement().getId() == 4) {
					Image goal = new Image("file:src/ressources/zone.gif");
					ImageView iv4 = new ImageView(goal);
					gridPane.add(iv4, j, i);
				}

				// Element perso id = 5
				if (tableauCellules[i][j].getElement().getId() == 5) {
					Image character = new Image("file:src/ressources/perso.gif");
					ImageView iv5 = new ImageView(character);
					gridPane.add(iv5, j, i);
				}

				// Element persoZone id = 6
				if (tableauCellules[i][j].getElement().getId() == 6) {

					Image characterOnGoal = new Image("file:src/ressources/persoZone.gif");
					ImageView iv6 = new ImageView(characterOnGoal);
					gridPane.add(iv6, j, i);
				}
			}

		}
		gridPane.setGridLinesVisible(true);

		return gridPane;
	}


}
