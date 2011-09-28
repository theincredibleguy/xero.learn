package com.ac.engine;

import com.ac.exceptions.AutoCException;
import com.ac.interfaces.IContext;

public class AutoCContext implements IContext {

	private AutoCEngine acEngine;
	
	public AutoCContext(){
	}
	
	@Override
	public Object getMessages(String partMessage)
			throws AutoCException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getMessagesC2(String partMessage)
			throws AutoCException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enableC2() {
		// TODO Auto-generated method stub
	}

}
