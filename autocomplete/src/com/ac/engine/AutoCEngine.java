package com.ac.engine;

import java.util.HashMap;

import com.ac.components.CustomTrie;
import com.ac.components.TriePool;
import com.ac.exceptions.AutoCException;
import com.ac.interfaces.Engine;
import com.ac.util.TrieFactory;
/**
 * this should probably be a Process .. but let's see
 * @author Administrator
 *
 */
public class AutoCEngine implements Engine {
	
	private TriePool triePool;
	private HashMap l1Map;

	public AutoCEngine(){
		triePool = TriePool.getInstance();
		initialize();
	}
	
	private void initialize(){
		loadObjects();
		
	}
	
	/**
	 * this will prepare the pool by loading trie objects and L1 trie nodes. Loading will only happen on initialization of the engine.
	 */
	private void loadObjects(){
		// TODO loadObjects -- probably from dump 
		// may have to come up with custom class loader for the same
		// as of now creating objects from random strings at runtime for testing
		
		//constructing random trie objects
		
		String temp1 = "hello there, how are you, hope you had a good night sleep today !!";
		String temp2 = "Hey!, I'll be bit late today, kindly proceed.";
		
		CustomTrie ct;
		ct = TrieFactory.generateTrie(1,temp1);
		triePool.addCustomTrie(ct);
		ct = TrieFactory.generateTrie(2,temp2);
		triePool.addCustomTrie(ct);
		
		// constructing an L1 node structure.. this should either be loaded from class loader.. (not sure yet)
		
		for(CustomTrie msg: triePool.getAllTries()){
			
		}
		
	}
	
	@Override
	public void start() throws AutoCException {
		initialize();
	}

	@Override
	public void stop() throws AutoCException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() throws AutoCException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() throws AutoCException {
		// TODO Auto-generated method stub
		
	}

	

}
