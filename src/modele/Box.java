package modele;

public class Box extends MobilElement {

	public Box(int x, int y) {
		super(2, x, y);
	}
	
	public Box(int id, int x, int y) {
		super(2, x, y);
	}
}
/*
public void upCharacter() {
	int cell = 0;

	System.out.println("Up");
	// [y][x]
	try {
		board[characterY][characterX] = new Element(
				board[characterY][characterX].getCurrentCell());
		if (board[characterY - 1][characterX].isGoal()) {
			board[--characterY][characterX] = new CharacterOnGoal();
		} else
			board[--characterY][characterX] = new Character(cell);
	} catch (Exception e) {
		// TODO
	}

}*/