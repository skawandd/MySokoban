package modele;

public class Character extends MobilElement {
	
	public Character(int cell) {
		super(5);
		this.setCurrentCell(cell);
	}
	
	public Character() {
		super(6);
	}
	
	
}