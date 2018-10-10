package modele;

public class Box extends MobilElement {
	private boolean locked;

	public Box() {
		super(2);
		this.locked = false;
	}
	
	public Box(boolean locked) {
		super(3);
		this.locked = locked;
	}

	public boolean getBox() {
		return this.locked;
	}
	
	public void setBox(boolean locked) {
		this.locked = locked;
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