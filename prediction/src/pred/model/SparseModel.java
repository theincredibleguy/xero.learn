package pred.model;

import java.util.ArrayList;
import java.util.HashMap;

import pred.datastructures.LocationList;

public class SparseModel {

	private Character element;

	private LocationList locations = new LocationList();
	private HashMap<Character,Weight> weightage = new HashMap<Character,Weight>();
	
	public HashMap<Character, Weight> getWeightage() {
		return weightage;
	}

	public void setWeightage(HashMap<Character, Weight> weightage) {
		this.weightage = weightage;
	}

	public SparseModel(char s){
		this.element = s;
	}
	
	public void addData(XY location, Character nextElement){
		this.locations.add(location); 
		if(!location.isEndLetter()){
			addWeightageOf(nextElement);
		}
	}
	
	public Character getElement() {
		return element;
	}
	
	public void setElement(Character element) {
		this.element = element;
	}
	
	public void addData(XY location){
		
	}
	
	public void removeData(XY location){
		this.locations.remove(location);
		
		//TODO decrease the weightage of next element
	} 
	
	public boolean isLocationPresent(XY location){
		return this.locations.contains(location);
	}
	
	private void addWeightageOf(Character c){
		Weight w = this.weightage.get(c);
		if(w!=null)
			this.weightage.get(c).increaseWeightage();
		else{
			weightage.put(c,  new Weight(c));
		}
	}
	
	private void decreaseWeightageOf(String c){
		Weight w = this.weightage.get(c);
		if(w!=null)
			this.weightage.get(c).decreaseWeight();
		
	}
	
	public void show(){
		System.out.println("locations of " + element);
		for(XY xy: locations){
			System.out.println(xy);
		}
		for(Weight e: weightage.values()){
			System.out.println(e);
		}
		
		System.out.println("\n\n");
	}
	
	public LocationList getAllLocations(){
		return this.locations;
	}
	
	public void setAllLocations(LocationList locations){
		this.locations = locations;
	}

	public void resetAllLocations() {
		for(XY xy: locations){
			xy.setY(-1);
		}
		
	}
}
