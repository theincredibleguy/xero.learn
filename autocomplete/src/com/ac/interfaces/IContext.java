package com.ac.interfaces;

import com.ac.exceptions.NoMessageFoundException;

public interface IContext {
	Object getMessages(String partMessage) throws NoMessageFoundException;
	
	Object getMessagesC2(String partMessage) throws NoMessageFoundException;
	
	void enableC2();
}
