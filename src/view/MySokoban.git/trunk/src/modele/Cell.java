package modele;

public class Cell {
	private Element element;
	private Ground floor;
	
	public Cell(Element element, Ground floor) {
		this.element = element;
		this.floor = floor;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Ground getFloor() {
		return floor;
	}

	public void setFloor(Ground floor) {
		this.floor = floor;
	}
	
	public boolean isFree() {
		if(this.element.getId() == -1)
			return true;
		return false;
	}
	
	public boolean isWall() {
		if(this.element.getId() == 1)
			return true;
		return false;
	}
	
	public boolean isBox() {
		if(this.element.getId() == 2 || this.element.getId() == 3)
			return true;
		return false;
	}
	
	public boolean isBoxOnGoal() {
		if(this.element.getId() == 3)
			return true;
		return false;
	}
	
	public boolean isGoal() {
		if(this.floor.getId() == 4)
			return true;
		return false;
	}
	
	public boolean isEmpty() {
		if(this.element.getId() == -1)
			return true;
		return false;
	}

}
