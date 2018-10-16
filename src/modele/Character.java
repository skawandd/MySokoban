package modele;

public class Character extends MobilElement {
	/*
	public Character(int cell) {
		super(5);
		this.setCurrentCell(cell);
	}
	
	public Character() {
		super(6);
		if(character.getX() == mElement.getX()+1 && character.getY() == mElement.getY()) {
			if(board[mElement.getY()][mElement.getX()-1].isWall()) {
		}
	}*/
	
	public Character(int id, int x, int y) {
		super(id, x, y);
	}
	
	public Character(int x, int y) {
		super(5, x, y);
	}
	
	public int posCharacter(MobilElement mElement) {
			if(this.getPoint().getX() == mElement.getPoint().getX()+1 && this.getPoint().getY() == mElement.getPoint().getY()){
				return 0;
			}
			return 0;
		}
		
	
}