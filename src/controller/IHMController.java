package controller;

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
import view.Display;

/**
 * The main controller, it loads the GUI and the GameController
 */
public class IHMController extends Application {

    private volatile GameController game;
    private Move lastKey;
    CSVElement csv;

    /**
     * On creation character is looking down
     */
    private IHMController() {
        this.lastKey = Move.DOWN;
    }

    /**
     * Entry point of the app
     * @param args Mandatory but useless
     */
    public static void main(String[] args) {
        Application.launch(IHMController.class, args);
    }

    /**
     * Start the App
     * @param primaryStage the Stage used in the app
     */
    @Override
    public void start(Stage primaryStage) {
        initCSV();
        Scene scene;

        if (!csv.isARobot()) {
            game = new PlayerGameController(csv);
            GridPane gridPane = buildGrid();
            scene = new Scene(gridPane);
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
                        case ENTER:
                            break;
                        case ESCAPE:
                            System.exit(0);
                            break;
                        default:
                            return;
                    }
                    switch (key) {
                        case UP:
                        case DOWN:
                        case LEFT:
                        case RIGHT:
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
        } else {
            this.game = new RobotGameController(csv);
            GridPane gridPane = buildGrid();
            scene = new Scene(gridPane);
            play(gridPane);
        }
        primaryStage.setTitle("MySokoban");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    /**
     * Move the character of the robotGameController
     * @param gridPane this App gridPane
     */
    private void play(GridPane gridPane) {
        RobotGameController robotGame = (RobotGameController) this.game;
        robotGame.solve();
        List<Move> solution = robotGame.getSolution();
        if (solution != null) {
            for (Move move : solution) {
                game.move(move);
                gridPane.getChildren().clear();
                gridPane.getChildren().add(buildGrid());
            }
        }
        System.out.println(game.getNbMoves() + " coups");
        System.out.println(Display.moveSequenceAsString(solution));
    }

    /**
     * Load all the tiles of the grid
     * @return new gridPane made from the Grid's las state
     */
    private GridPane buildGrid() {

        GridPane gridPane = new GridPane();
        int xLength = game.getGrid().getX();
        int yLength = game.getGrid().getY();
        gridPane.resize(xLength, yLength);
        byte[][] tableauCellules = game.getGrid().getGrid();
        String tilePath;

        for (int j = 0; j < yLength; j++) {
            for (int i = 0; i < xLength; i++) {
                // Ajouter le gif correspondant à l'élément à la gridPane
                switch (tableauCellules[i][j]) {
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
                    case Tile.MARIO_ON_GOAL:
                        tilePath = getMario(lastKey);
                        break;
                    default:
                        tilePath = null;
                }
                if (tilePath != null) {
                    Image image = new Image(tilePath);
                    ImageView iv = new ImageView(image);
                    gridPane.add(iv, i, j);
                }
            }
        }
        gridPane.setGridLinesVisible(true);
        return gridPane;
    }

    /**
     * @param direction The direction the character has to face
     * @return the appropriate mario picture
     */
    private String getMario(Move direction) {
        switch (direction) {
            case UP:
                return "file:src/view/sprites/mario_haut.gif";
            case DOWN:
            default:
                return "file:src/view/sprites/mario_bas.gif";
            case LEFT:
                return "file:src/view/sprites/mario_gauche.gif";
            case RIGHT:
                return "file:src/view/sprites/mario_droite.gif";
        }
    }

    /**
     * Victory pop-up
     * @param gridPane  the GridPane
     */
    private void showDialog(GridPane gridPane) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("VICTORY");
        alert.setHeaderText("YOU WIN !!!\n" + game.getNbMoves() + " moves in " + game.getTimer());

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
            game = new PlayerGameController(csv);
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
    private void initCSV() {
        boolean notInstanciated = true;
        do {
            File file = CSVElement.pick_CSVLevel();
            try {
                this.csv = new CSVElement(file);
                notInstanciated = false;
            } catch (FileNotFoundException ex) {
                System.err.println("Unable to read specified file");
            }
        } while (notInstanciated);
    }

}
