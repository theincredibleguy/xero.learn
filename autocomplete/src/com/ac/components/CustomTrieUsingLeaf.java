package com.ac.components;

public class CustomTrieUsingLeaf {
	
	private int msgId;
	private Root root;
	
	public CustomTrieUsingLeaf(int msgId){
		root = new Root(msgId);
	}

	public void addBranch(String string) {
		Leaf leaf = null, prev=null;
		for(int i=0;i<string.length();i++){
			if(prev == null){
				leaf = new Leaf(root,string.charAt(i));
				root.addChild(leaf);
				prev = leaf;
			}else{
				leaf = new Leaf(prev,string.charAt(i));
				prev = leaf;
			}
		}
	}
	
	
}
