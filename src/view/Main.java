package view;

import controller.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
//import javafx.scene.Group;
//import javafx.scene.Parent;
import javafx.stage.Stage;

/*public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
			
		//1. On met en place les �l�ments permettant de construire la matrice
			
			primaryStage.setTitle("Mon Sokoban");
			GridPane gridPane = new GridPane();
			Board board = new Board();
			Scene scene = new Scene(gridPane,400,400);
			
			//TODO : Faire correspondre les bons gifs aux bons �l�ments de l'id 0 � 6
			
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
			Image box = new Image("ressources/box.gif");
			ImageView iv2 = new ImageView();
			iv2.setImage(box);
			Label labelBox = new Label();
							
			//Element caisse plac�e id = 3
			Image lockedBox = new Image("ressources/boxLocked.gif");
			ImageView iv3 = new ImageView();
			iv3.setImage(lockedBox);
			Label labelLockedBox = new Label();
						
			//Element goal id = 4
			Image goal = new Image("ressources/persoZone.gif");
			ImageView iv4 = new ImageView();
			iv4.setImage(goal);
			Label labelGoal = new Label();
			
			//Element perso id = 5
			Image perso = new Image("ressources/perso.gif");
			ImageView iv5 = new ImageView();
			iv5.setImage(perso);
			Label labelPerso = new Label();
			
			/*Element perso id = 6
			Image perso = new Image("ressources/perso.gif");
			ImageView iv5 = new ImageView();
			iv5.setImage(perso);
			Label labelPerso = new Label();
			
			// 2. On r�cup�re les proportions de la matrice que l'on souhaite repr�senter sur l'interface
			int xLength = board.getLengthX();
			int yLength = board.getLengthY();
			
			// 3. On redimensionne la taille de la matrice
			gridPane.resize(xLength, yLength);
			
			//On construit la matrice grace � la boucle et on ajoute une image en fonction de l'id de l'�l�ment contenu dans la cellule
			
			for(int x = 0; x < xLength; x++){
				for(int y = 0; y < yLength ; y++){
					
					//Floor
					if(board.getElement(x, y).getElementId() == 0)
						gridPane.add(iv0, y, x);
					
					//Wall
					if(board.getElement(x, y).getElementId() == 1)
						gridPane.add(iv1, y, x);
						
						
					//Box
					if(board.getElement(x, y).getElementId() == 2)
						gridPane.add(iv2, y, x);
						
					//LockedBox
					if(board.getElement(x, y).getElementId() == 3)
						gridPane.add(iv3, y, x);
						
					//Goal
					if(board.getElement(x, y).getElementId() == 4)
						gridPane.add(iv4, y, x);
						
					//Perso
					if(board.getElement(x, y).getElementId() == 5)
						gridPane.add(iv5, y, x);
						
					/*Perso on goal
					if(board.getElement(x, y).getElementId() == 6)
						gridPane.add(child, y, x);
				
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







/*import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class Main extends Application {
@Override
public void start(Stage primaryStage) {
	
	try {
	
		GridPane pane = new GridPane();
		Board board = new Board();
		Scene scene = new Scene(pane,400,400);
		primaryStage.setTitle("Mon Sokoban");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
		for(int i = 0; i < board.getLengthY(); i++){ 
		       
			for(int j = 0; j < board.getLengthX(); j++){ 

				Element element = board.getElement(j, i);
				
				if(element.getElementId() == 0)
		    	   pane.add(labelFloor,i,j);
				
				if(element.getElementId() == 1)
					pane.add(labelWall,i,j);
				
				if(element.getElementId() == 2)
					pane.add(labelBox, i, j);
				
				if(element.getElementId() == 3)
					pane.add(labelLockedBox, i, j);
					
				/*if(element.getElementId() == 4)
					pane.add(labelGoal, i, j);
				
				if(element.getElementId() == 5)
					pane.add(labelPerso, i, j);
		    
		     }
		}
	
	} catch(Exception e) {
		e.printStackTrace();
	}
}

public static void main(String[] args) {
	launch(args);
	
	
}
}*/






/*private Group tileGroup = new Group();
private Group elementGroup = new Group();

private Parent createContent(){
	
	GridPane root = new GridPane();
	Board board = new Board();
	int xLength = board.getLengthX();
	int yLength = board.getLengthY();
	root.setPrefSize(xLength*100, yLength*100);
	root.getChildren().addAll(tileGroup,elementGroup);*/
	
	//Element sol id = 0
	/*Image floor = new Image("ressources/floor.gif");
	ImageView iv0 = new ImageView();
	iv0.setImage(floor);
	Label labelFloor = new Label();
	
	//Element mur id = 1
	Image wall = new Image("ressources/wall.gif");
	ImageView iv1 = new ImageView();
	iv1.setImage(wall);
	Label labelWall = new Label();
	
	//Element caisse id = 2
	Image box = new Image("ressources/box.gif");
	ImageView iv2 = new ImageView();
	iv2.setImage(box);
	Label labelBox = new Label();
					
	//Element caisse plac�e id = 3
	Image lockedBox = new Image("ressources/boxLocked.gif");
	ImageView iv3 = new ImageView();
	iv3.setImage(lockedBox);
	Label labelLockedBox = new Label();
				
	//Element goal id = 4
	Image goal = new Image("ressources/persoZone.gif");
	ImageView iv4 = new ImageView();
	iv4.setImage(goal);
	Label labelGoal = new Label();
	
	//Element perso id = 5
	Image perso = new Image("ressources/perso.gif");
	ImageView iv5 = new ImageView();
	iv5.setImage(perso);
	Label labelPerso = new Label();*/
	
	/*for(int y = 0; y < yLength ; y++){
		for(int x = 0; x < xLength ; x++){
			Tile tile = new Tile(board.getElement(x, y).getElementId(),x,y);
			
			if(board.getElement(x, y).getElementId() == 0){
				root.add(labelFloor, x, y);
			}
			
			if(board.getElement(x, y).getElementId() == 1){
				root.add(labelWall, x, y);
			}
			
			if(board.getElement(x, y).getElementId() == 2){
				root.add(labelBox, x, y);
			}
			
			if(board.getElement(x, y).getElementId() == 3){
				root.add(labelLockedBox, x, y);
			}
			
			if(board.getElement(x, y).getElementId() == 4){
				root.add(labelGoal, x, y);
			}
			
			if(board.getElement(x, y).getElementId() == 5){
				root.add(labelPerso, x, y);
			}
			
			tileGroup.getChildren().add(tile);
			
		}
	}
	
	return root;
}*/
