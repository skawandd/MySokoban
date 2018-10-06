package modele;

public class MobilElement extends Element {
	
	public MobilElement(int id, int currentCell) {
		super(id, currentCell);
	}
	public MobilElement(int id) {
		super(id);
	}
	
	
	
	public boolean checkMobility() {
		
		return true;
	}
}
