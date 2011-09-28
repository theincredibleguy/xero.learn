package com.ac.interfaces;

import com.ac.exceptions.AutoCException;

/**
 * defines engine
 * @author Administrator
 *
 */
public interface Engine {
	
	void start() throws AutoCException;
	
	void stop() throws AutoCException;
	
	void pause() throws AutoCException;
	
	void resume() throws AutoCException;

}
