
public class Box extends MobilElement {
	private boolean locked;

	public Box() {
		super(2);
		this.locked = false;
	}
	
	public Box(Boolean locked) {
		super(3);
		this.locked = locked;
	}

	public boolean getBox() {
		return this.locked;
	}
	
	public void setBox(Boolean locked) {
		this.locked = locked;
	}

}
