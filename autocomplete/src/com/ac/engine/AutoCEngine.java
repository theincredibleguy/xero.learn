package com.ac.engine;

import com.ac.exceptions.AutoCException;
import com.ac.interfaces.Engine;
/**
 * this should probably be a Process .. but let's see
 * @author Administrator
 *
 */
public class AutoCEngine implements Engine {

	
	private void initialize(){
		loadObjects();
	}
	
	private void loadObjects(){
		// TODO loadObjects -- probably from dump 
		// may have to come up with custom class loader for the same
		// as of now creating objects from random strings at runtime for testing
	}
	
	@Override
	public void start() throws AutoCException {
		initialize();
	}

	@Override
	public void stop() throws AutoCException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() throws AutoCException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() throws AutoCException {
		// TODO Auto-generated method stub
		
	}

	

}
