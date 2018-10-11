package modele;

/**
 * 
 * @author Dylan
 * Generic Object to create Board's elements
 */
public class Element {
	private int id;
	private int currentCell;
	private int x, y;
	
	public Element(int id) {
		this.id = id;
	}
	
	public Element(int id, int currentCell) {
		this.id = id;
		this.currentCell = currentCell;
	}
	
	public int getElementId() {
		return this.id;
	}
	
	public void setElementId(int id) {
		this.id = id;
	}
	
	public int getCurrentCell() {
		return this.currentCell;
	}
	public void setCurrentCell(int currentCell) {
		this.currentCell = currentCell;
	}
	public boolean isGoal() {
		if(this.id == 4) {
			return true;
		}
		return false;
	}
	
	public boolean isWall() {
		if(this.getElementId() == 1)
			return true;
		return false;
	}
}
