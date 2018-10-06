package modele;

public class Character extends MobilElement {
	
	public Character() {
		super(5);
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