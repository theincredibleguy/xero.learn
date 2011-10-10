package com.ac.components;

public class CustomTrieUsingLeaf {
	
	private int msgId;
	private Leaf root;
	
	public CustomTrieUsingLeaf(int msgId){
		root = new Leaf(null,null,msgId+"",LeafType.ROOT);
	}
}
