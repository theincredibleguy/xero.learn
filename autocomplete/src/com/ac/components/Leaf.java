package com.ac.components;

import com.ac.interfaces.Node;


public class Leaf implements Node{
	
	Node parent, child;
	char value;
	LeafType type;
	
	public Leaf(Node parent, char value){
		this.parent = parent;
		this.value = value;
		this.type = LeafType.LEAF;
	}
	

	@Override
	public LeafType getType() {
		return type;
	}
}
