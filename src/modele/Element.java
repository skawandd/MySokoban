package modele;

public class Element {
	private int id;
	private int currentCell;
	
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
}
