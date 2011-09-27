package com.ac.components;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * represents a trie structure with message id as root and every string of the message as branch e.g for a message HEY THERE following will be the structure
 * 						
 * 								msgid
 * 							/			\
 * 						  /			      \
 * 						H					T
 * 						|					|
 * 						E					H
 * 						|					|
 * 						Y					E
 * 						|					|
 * 						EOS					R
 * 											|
 * 											E
 * 											|
 * 											EOS
 * @author sachin
 *
 */
public class CustomTrie {
	
	private int msgId;
	private List<LinkedList<String>> branches ;
	
	public List<LinkedList<String>> getBranches() {
		return branches;
	}

	public CustomTrie(int msgId) {
		this.msgId = msgId;
		branches = new ArrayList<LinkedList<String>>();
	}
	
	public int getMsgId() {
		return msgId;
	}
	
	
	public void addBranch(String branch){
		LinkedList<String> list = new LinkedList<String>();
		for(int i=0;i<branch.length();i++){
			list.add(branch.charAt(i)+"");
		}
		branches.add(list);
	}
}