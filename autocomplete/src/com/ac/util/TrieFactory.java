package com.ac.util;

import java.util.StringTokenizer;

import com.ac.components.CustomTrie;

/**
 * this factory will create a CustomTrie out of a String message
 * @author sachin
 *
 */
public class TrieFactory {
	
	public static CustomTrie generateTrie(int messageId, String message){
		CustomTrie ct = new CustomTrie();
		ct.setMsgId(messageId);
		StringTokenizer st = new StringTokenizer(message);
		while(st.hasMoreElements()){
			ct.addBranch(st.nextToken());
		}
		return ct;
	}
}
