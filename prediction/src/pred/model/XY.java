package pred.model;


public class XY implements Comparable<XY>{
	
	private int x,y;
	private boolean endLetter = false;
	private boolean selected = false;
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isEndLetter() {
		return endLetter;
	}

	public void setEndLetter(boolean endLetter) {
		this.endLetter = endLetter;
	}

	public XY(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		return (((XY) obj).x == this.x)
				&& (((XY) obj).y == this.y);
	}

	@Override
	public int compareTo(XY o) {
		if(this.x < o.x)
			return -1;
		else
			if(this.x == o.x)
				return this.y - o.y;

		return 1;
	}
	
	@Override
	public String toString() {
		return x+","+y + " is EndLetter " + endLetter;
	}
}
