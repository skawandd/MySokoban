package view;

import controller.Board;
import javafx.application.Application;
import javafx.stage.Stage;
import modele.Cell;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
//import javafx.scene.Group;
//import javafx.scene.Parent;

public class UserInterface extends Application {
	
	@Override
	public void start(Stage primaryStage) {
			
		//1. On met en place les éléments permettant de construire la matrice
			
			primaryStage.setTitle("Mon Sokoban");
			GridPane gridPane = new GridPane();
			Board board = new Board();
			Scene scene = new Scene(gridPane,400,400);
			
			
			//Element sol id = 0
			Image floor = new Image("ressources/floor.gif");
			ImageView iv0 = new ImageView();
			iv0.setImage(floor);
			
			//Element mur id = 1
			Image wall = new Image("ressources/wall.gif");
			ImageView iv1 = new ImageView();
			iv1.setImage(wall);
			Label labelWall = new Label();
			
			//Element caisse id = 2
			Image boxOnFloor = new Image("ressources/box.gif");
			ImageView iv2 = new ImageView();
			iv2.setImage(boxOnFloor);
			Label labelBox = new Label();
							
			//Element caisse placée id = 3
			Image boxOnGoal = new Image("ressources/boxLocked.gif");
			ImageView iv3 = new ImageView();
			iv3.setImage(boxOnGoal);
			Label labelBoxOnGoal = new Label();
						
			//Element goal id = 4
			Image goal = new Image("ressources/zone.gif");
			ImageView iv4 = new ImageView();
			iv4.setImage(goal);
			Label labelGoal = new Label();
			
			//Element perso id = 5
			Image character = new Image("ressources/perso.gif");
			ImageView iv5 = new ImageView();
			iv5.setImage(character);
			Label labelCharacter = new Label();
			
			//Element perso id = 6
			Image characterOnGoal = new Image("ressources/persoZone.gif");
			ImageView iv6 = new ImageView();
			iv6.setImage(characterOnGoal);
			Label labelCharacterOnGoal = new Label();
			
			// 2. On récupére les proportions de la matrice que l'on souhaite représenter sur l'interface
			int xLength = board.getBoardXSize();
			int yLength = board.getBoardYSize();
			

			
			// 3. On redimensionne la taille de la matrice
			gridPane.resize(xLength, yLength);
			
			//4. On récupère les cellules contenues dans le board
			Cell[][] tableauCellules = board.getMatrix();
			
			
			//On construit la matrice grace à la boucle et on ajoute une image en fonction de l'id de l'élément contenu dans la cellule
			
			for(int x = 0; x < xLength; x++){
				for(int y = 0; y < yLength ; y++){
					
					//Floor
					if(tableauCellules[x][y].getElement().getId() == 0)
						gridPane.add(iv0, y, x);
					
					//Wall
					if(tableauCellules[x][y].getElement().getId() == 1)
						gridPane.add(iv1, x, y);
						
						
					//Box
					if(tableauCellules[x][y].getElement().getId() == 2)
						gridPane.add(iv2, x, y);
						
					//LockedBox
					if(tableauCellules[x][y].getElement().getId() == 3)
						gridPane.add(iv3, x, y);
						
					//Goal
					if(tableauCellules[x][y].getElement().getId() == 4)
						gridPane.add(iv4, x, y);
						
					//Character
					if(tableauCellules[x][y].getElement().getId() == 5)
						gridPane.add(iv5, x, y);
						
					//Character on goal
					if(tableauCellules[x][y].getElement().getId() == 5)
						gridPane.add(iv6, x, y);
				
				}
				
			}
			
			// 4. On affiche les lignes de la matrices et on met le container au centre
			
			gridPane.setGridLinesVisible(true);
			gridPane.setCenterShape(true);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
