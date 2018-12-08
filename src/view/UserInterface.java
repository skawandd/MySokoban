package view;

import java.util.Optional;

import controller.Game;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modele.Tile;

public class UserInterface extends Application {
	private volatile Game game;
	private Move lastKey;
        
        public UserInterface(){
            this.lastKey = Move.DOWN;
        }

	@Override
	public void start(Stage primaryStage) {

		game = new Game();
		new Thread(game).start();
                

		primaryStage.setTitle("MySokoban");
//		primaryStage.setResizable(false);
		GridPane gridPane = buildGrid();

		Scene scene = new Scene(gridPane);
		primaryStage.setScene(scene);
		primaryStage.show();

		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
                            
				switch (event.getCode()) {

				case UP:
                                    lastKey = Move.UP;
                                    game.move(lastKey);
                                    gridPane.getChildren().clear();
                                    gridPane.getChildren().add(buildGrid());
                                    break;
				case DOWN:
                                    lastKey = Move.DOWN;
                                    game.move(lastKey);
                                    gridPane.getChildren().clear();
                                    gridPane.getChildren().add(buildGrid());
                                    break;

				case LEFT:
                                    lastKey = Move.LEFT;
                                    game.move(lastKey);
                                    gridPane.getChildren().clear();
                                    gridPane.getChildren().add(buildGrid());
                                    break;
				case RIGHT:
                                    lastKey = Move.RIGHT;
                                    game.move(lastKey);
                                    gridPane.getChildren().clear();
                                    gridPane.getChildren().add(buildGrid());
                                    break;
				case ENTER:
					game = new Game();
					lastKey = Move.DOWN;
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

				if (game.checkWin()) {
					showDialog(gridPane);
				}
			}
		});
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
			game = new Game();
			lastKey = Move.DOWN;
			gridPane.getChildren().clear();
			gridPane.getChildren().add(buildGrid());
		} else if (option.get() == exit) {
			System.exit(0);
		}
	}

}
