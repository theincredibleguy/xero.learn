package com.ac.components;

import java.util.ArrayList;

public class CustomTrieUsingLeaf {
	
	private int msgId;
	private Root root;
	private ArrayList<Leaf> branchHeads;
	
	public CustomTrieUsingLeaf(int msgId){
		root = new Root(msgId);
		branchHeads = new ArrayList<Leaf>();
	}

	public void addBranch(String string) {
		Leaf leaf = null, prev=null;
		for(int i=0;i<string.length();i++){
			if(prev == null){
				leaf = new Leaf(root,string.charAt(i));
				root.addChild(leaf);
			}else{
				leaf = new Leaf(prev,string.charAt(i));
				leaf.setParent(prev);
				if(prev.getType()==LeafType.LEAF)
					prev.setChild(leaf);
			}
			prev = leaf;
			
			if(i==0)
				branchHeads.add(leaf);
		}
	}
	
	public void showTree(){
		System.out.println("ROOT - " + root.getValue());
		System.out.print("MESSAGE - ");
		for(int i=0;i<branchHeads.size();i++){
			Leaf l = branchHeads.get(i);
			System.out.print(" ");
			while(l!=null){
				System.out.print(l.value);
				l=(Leaf) l.getChild();
			}
		}
	}
}
