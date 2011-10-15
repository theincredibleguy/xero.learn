package com.ac.components;

import java.util.ArrayList;

import com.ac.interfaces.Node;


public class Root implements Node{
	
	private int msgId;
	private ArrayList children;
	private LeafType type;
	
	public Root(int msgId){
		this.msgId = msgId;
		children = new ArrayList();
		type = LeafType.ROOT;
	}
	
	public void addChild(Node child){
		children.add(child);
	}

	@Override
	public LeafType getType() {
		return type;
	}

	@Override
	public String getValue() {
		return msgId+"";
	}
}
