package pred.datastructures;

import java.util.ArrayList;

import pred.model.XY;

public class LocationList extends ArrayList<XY> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean contains(Object o) {
		return super.contains(o);
	}
	
	public boolean belongsTo(XY xy){
		for(XY location: this){
			if(xy.getX() == location.getX()){
				xy.setY(location.getY()-1);
				return true;
			}
		}
		return false;
	}

}
