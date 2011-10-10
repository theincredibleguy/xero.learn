package com.ac.components;

import java.util.ArrayList;

import com.ac.interfaces.TrieNode;

public class Root implements TrieNode{
	
	private String msgId;
	private ArrayList children;
	
	public Root(String msgId){
		this.msgId = msgId;
		children = new ArrayList();
		
	}

}
