package modele;

public class Character extends MobilElement {
	
	public Character(int cell) {
		super(5);
		this.setCurrentCell(cell);
	}
	
	public Character(int i, int cell) {
		super(i);
		this.setCurrentCell(cell);
	}
	
	
}