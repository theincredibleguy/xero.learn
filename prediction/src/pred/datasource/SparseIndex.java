package pred.datasource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import pred.datastructures.LocationList;
import pred.model.SparseModel;
import pred.model.Weight;
import pred.model.XY;

public class SparseIndex {
	
	private HashMap<Character, SparseModel> baseIndex = new HashMap<Character,SparseModel>();
	private SparseModel currentModel = null;
	private StringBuffer currentResult = new StringBuffer();
	
	public SparseIndex(){
		initialize();
	}
	
	private void initialize(){
		for(int i=0;i<Data.s1.length;i++){
			populate(i, Data.s1[i]);
		}
	}
	
	public void populate(int id, String message){
		for(int i=0; i<message.length(); i++){
			
			if (message.charAt(i) != ' ') {
				Character c = message.charAt(i);
				SparseModel model;
				if (baseIndex.containsKey(c)) {
					model = baseIndex.get(c);
				} else {
					model = new SparseModel(c);
					baseIndex.put(c, model);
				}
				XY xy = new XY(id, i);

				if (i + 1 == message.length() || message.charAt(i+1)==' ') {
					xy.setEndLetter(true);
					c=' ';
				} else{
					c = message.charAt(i + 1);
				}
				model.addData(xy, c);
			}
		}
	}
	
	public List<String> search(String criteria)	{
		SparseModel currOccurrences = null;

		criteria = criteria.trim();
		for(int i =0; i<criteria.length(); i++)	{
			Character c = criteria.charAt(i);
			if(c==' '){
				if(currentModel != null)
					currentModel.resetAllLocations();
				continue;
			}

			currOccurrences = baseIndex.get(c);
			
			if(currentModel ==null)	{
				currentModel = baseIndex.get(c);
			}
			else {
				currentModel = delta(currentModel,currOccurrences);
			}
		}
		
		
		
		List<String> results = new ArrayList<String>();
		for(XY pos: currentModel.getAllLocations())	{
			String ret = searchSuffix(currentModel, pos);
			if(ret!=null)
				results.add(ret);
		}
		
		return results;
		
	}
	
	public void resetSparseModel(String criteria){
		
		for(int i=0;i<criteria.length();i++){
			Character c = criteria.charAt(i);
			SparseModel currOccurrences = baseIndex.get(c);
			currentModel = delta(currentModel,currOccurrences);
		}
		
		currentModel.resetAllLocations();
		
	}
	
	private String searchSuffix(SparseModel searchModel, XY pos)	{
		
		if(pos.isEndLetter())
			return String.valueOf(searchModel.getElement());
		Collection<Weight> weightage = searchModel.getWeightage().values();
		for(Weight w: weightage){
			SparseModel temp = baseIndex.get(w.getElement());
			XY nextPos = new XY(pos.getX(),pos.getY()+1);
			if(temp.isLocationPresent(nextPos))	{
				int i = temp.getAllLocations().indexOf(nextPos);
				nextPos = temp.getAllLocations().get(i);
				String ret = searchSuffix(temp,nextPos);
				if(ret != null) {
					return searchModel.getElement() + ret;
				}
			}			
		}
		return null;
	}
	
	
	private SparseModel delta(SparseModel s1Data, SparseModel s2Data){
		
		ArrayList<XY> nextLocations = new LocationList();
		SparseModel temp = new SparseModel(s2Data.getElement());
		for(XY xy: s1Data.getAllLocations()){
			int tempY = xy.getY();
			boolean isPresent = false;
			if(tempY < 0){
				if(s2Data.getAllLocations().belongsTo(xy))
					isPresent = true;
			}
			else
				isPresent = s2Data.getAllLocations().contains(new XY(xy.getX(),tempY+1));

			if(isPresent)
				nextLocations.add(new XY(xy.getX(),xy.getY()+1));
		}
		
		//TODO need a better weightage engine
		temp.setWeightage(s2Data.getWeightage());
		temp.setAllLocations((LocationList)nextLocations);
		
		return temp;
	}

	public void show(){
		for(SparseModel s: baseIndex.values())
			s.show();
	}
	
	public static void main(String[] args) {
		SparseIndex si = new SparseIndex();
		si.show();
		System.out.println(si.search("Div"));
	}

}