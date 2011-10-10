package com.ac.components;

import com.ac.interfaces.TrieNode;

public class Leaf implements TrieNode{
	
	Leaf parent, child;
	String value;
	LeafType type;
	
	public Leaf(){}
	
	public Leaf(Leaf parent, Leaf child, String value, LeafType type){
		this.parent = parent;
		this.child = child;
		this.value = value;
		this.type = type;
	}
}
