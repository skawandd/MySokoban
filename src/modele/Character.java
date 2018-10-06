package modele;

public class Character extends MobilElement {
	
	public Character(int cell) {
		super(5, cell);
	}

	public Character(boolean onGoal) {
		super(6);
	}
	

	public Character checkCell() {
		if (getCurrentCell() == 4)
			return new CharacterOnGoal();
		return this;
	}
}