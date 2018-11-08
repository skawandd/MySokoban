package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	private Group tileGroup = new Group();
	private Group elementGroup = new Group();
	
	private Parent createContent(){
		
		GridPane root = new GridPane();
		Board board = new Board();
		int xLength = board.getLengthX();
		int yLength = board.getLengthY();
		root.setPrefSize(xLength*100, yLength*100);
		root.getChildren().addAll(tileGroup,elementGroup);
		
		//Element sol id = 0
		Image floor = new Image("ressources/floor.gif");
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
						
		//Element caisse placée id = 3
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
		
		for(int y = 0; y < yLength ; y++){
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
	}
	
	@Override
	public void start(Stage primaryStage) {
			
			primaryStage.setTitle("Mon Sokoban");
			Scene scene = new Scene(createContent());
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
		
		//Element sol id = 0
		Image floor = new Image("ressources/floor.gif");
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
						
		//Element caisse placée id = 3
		Image lockedBox = new Image("ressources/boxLocked.gif");
		ImageView iv3 = new ImageView();
		iv3.setImage(lockedBox);
		Label labelLockedBox = new Label();
					
		//Element goal id = 4
		/*Image goal = new Image("ressources/persoZone.gif");
		ImageView iv4 = new ImageView();
		iv4.setImage(goal);
		Label labelGoal = new Label();
		//Element perso id = 5
		Image perso = new Image("ressources/perso.gif");
		ImageView iv5 = new ImageView();
		iv5.setImage(perso);
		Label labelPerso = new Label();
		
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
