package pred.model;

public class Weight implements Comparable<Weight>{
	private Character element;
	private int weight = 1;
	
	public Weight(Character s){
		this.element = s;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Character getElement() {
		return element;
	}

	@Override
	public int compareTo(Weight o) {
		return this.weight - o.weight;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(((Weight)obj).weight == this.weight)
			return true;
		else
			return false;
	}
	
	public void increaseWeightage(){
		weight++;
	}
	
	public void decreaseWeight(){
		weight--;
	}
	
	public String toString(){
		return "Element " + element + " Weight " + weight;
	}
	
}
