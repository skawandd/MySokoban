package modele;

public class MobilElement extends Element {
	//private int x, y;
	private Point point;
	
	public MobilElement(int id, int x, int y) {
		super(id);
		this.point = new Point(x, y);
	}
	
	public Point getPoint() {
		return this.point;
	}
	/*
	 * 
	public MobilElement(int id) {
		super(id);
	}
	
	public boolean checkMobility() {
		
		return true;
	}
	*/
}
