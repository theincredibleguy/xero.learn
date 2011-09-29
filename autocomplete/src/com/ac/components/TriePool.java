package com.ac.components;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * object managing all custom trie objects
 * @author Administrator
 *
 */
public class TriePool {

	private static TriePool triePool;
	private HashMap<Integer, CustomTrie> poolTrieList = new HashMap<Integer, CustomTrie>();
	private TriePool(){}

	public static synchronized TriePool getInstance(){
		if(triePool==null)
			triePool = new TriePool();
		
		return triePool;
	}
	
	public void addCustomTrie(CustomTrie trie){
		poolTrieList.put(trie.getMsgId(), trie);
	}
	
	public ArrayList<CustomTrie> getTries(ArrayList<Integer> ids){
		ArrayList<CustomTrie> trieList = new ArrayList<CustomTrie>();
		CustomTrie ct = null;
		for(int id: ids){
			ct = poolTrieList.get(id);
			if(ct!=null)
				trieList.add(ct);			
		}
		return trieList;
	}
	
	public ArrayList<CustomTrie> getAllTries(){
		ArrayList<CustomTrie> trieList = null;
		trieList = (ArrayList)poolTrieList.values();
		return trieList;
	}
}
