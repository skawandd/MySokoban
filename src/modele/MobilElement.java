package modele;

public class MobilElement extends Element {
	private int currentCell;
	
	public MobilElement(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	public int getCurrentCell() {
		return this.currentCell;
	}
	
	public boolean checkMobility() {
		
		return true;
	}
}
