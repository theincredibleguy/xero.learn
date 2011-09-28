package com.ac.interfaces;

import com.ac.exceptions.AutoCException;

public interface IContext {
	Object getMessages(String partMessage) throws AutoCException;
	
	Object getMessagesC2(String partMessage) throws AutoCException;
	
	void enableC2();
}
