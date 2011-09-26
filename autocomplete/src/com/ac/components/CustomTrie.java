package com.ac.components;

import java.util.ArrayList;

/**
 * represents a trie structure trie structure with message id as root and every string of the message as branch e.g for a message HEY THERE following will be the structure
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
	private ArrayList<ArrayList> branches ;
	
	public CustomTrie() {
		branches = new ArrayList<ArrayList>();
	}
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	
	public void addBranch(String branch){
		//TODO to convert String to a branch of chars -- might have to reimplement CharSequence
//		branches.add(branch);
	}
}