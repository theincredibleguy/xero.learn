package com.ac.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.ac.components.CustomTrie;
import com.ac.components.CustomTrieUsingLeaf;
import com.ac.util.TrieFactory;

public class Test {
	
	public static void main(String[] args) {
		String message = "hello there";
		CustomTrie ct = TrieFactory.generateTrie(112, message);
		System.out.println("message " + ct.getMsgId());
		ArrayList<LinkedList> branches = (ArrayList) ct.getBranches();
		for(LinkedList<String> list: branches){ 
			for(String s: list){
				System.out.print(s);
			}
			System.out.print(" ");
		}
		
		CustomTrieUsingLeaf ctul = TrieFactory.generateLeafTrie(1234,"the quick brown fox jumped over the lazy dog!!!");
		ctul.showTree();
		
	}

}
