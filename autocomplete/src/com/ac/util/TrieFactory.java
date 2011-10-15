package com.ac.util;

import java.util.StringTokenizer;

import com.ac.components.CustomTrie;
import com.ac.components.CustomTrieUsingLeaf;

/**
 * this factory will create a CustomTrie out of a String message
 * @author sachin
 *
 */
public class TrieFactory {
	
	public static CustomTrie generateTrie(int messageId, String message){
		CustomTrie ct = new CustomTrie(messageId);
		StringTokenizer st = new StringTokenizer(message);
		while(st.hasMoreElements()){
			ct.addBranch(st.nextToken());
		}
		return ct;
	}
	
	public static CustomTrieUsingLeaf generateLeafTrie(int messageId, String message){
		CustomTrieUsingLeaf ctul = new CustomTrieUsingLeaf(messageId);
		StringTokenizer st = new StringTokenizer(message);
		while(st.hasMoreElements()){
			ctul.addBranch(st.nextToken());
		}
		return ctul;
	}
}
