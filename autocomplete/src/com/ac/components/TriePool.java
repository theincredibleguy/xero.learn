package com.ac.components;

import java.util.HashMap;

/**
 * object managing all custom trie objects
 * @author Administrator
 *
 */
public class TriePool {

	private static TriePool triePool;
	private HashMap<Integer, CustomTrie> trieList = new HashMap<Integer, CustomTrie>();
	private TriePool(){}

	public static synchronized TriePool getInstance(){
		if(triePool==null)
			triePool = new TriePool();
		
		return triePool;
	}
	
	public void addCustomTrie(CustomTrie trie){
		trieList.put(trie.getMsgId(), trie);
	}
}
