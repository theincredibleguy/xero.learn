package com.ac.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class LeafTriePool {
	private static LeafTriePool leafTriePool;
	private ArrayList<Leaf> heapOfLeaves;
	private ArrayList<Leaf> processedLeaves;
	private TriePoolProcess poolProcess;
	private TrieInputProcessor inputProcessor;
	private HashMap<String, ArrayList<Leaf>> leafPool;
	private StringBuilder bufferedCriteria;
	
	private LeafTriePool(){
		heapOfLeaves = new ArrayList<Leaf>();
		poolProcess = new TriePoolProcess();
		inputProcessor = new TrieInputProcessor();
		leafPool = new HashMap<String,ArrayList<Leaf>>();
		processedLeaves = new ArrayList<Leaf>();
	}
	
	public static synchronized LeafTriePool getInstance(){
		if(leafTriePool==null)
			leafTriePool = new LeafTriePool();
		return leafTriePool;
	}
	
	public void addLeaves(Collection<Leaf> leaves){
		this.heapOfLeaves.addAll(leaves);
		poolProcess.notify();
	}
	
	public void addLeaves(Leaf leaf){
		this.heapOfLeaves.add(leaf);
		poolProcess.notify();
	}
	
	public ArrayList<Leaf> searchFor(String string){
		if(string.equals(""))
			bufferedCriteria.delete(0, bufferedCriteria.length()-1);
		else{
			inputProcessor.notify();
		}
		return processedLeaves;
	}
	
	
	private class TriePoolProcess extends Thread {
		
		@Override
		public void run() {
			synchronized (leafPool) {
				while (true) {
						for(Leaf l:heapOfLeaves){
							String key = l.getValue();
							ArrayList<Leaf> list = leafPool.get(key);
							if(list!=null)
								list.add(l);
							else{
								ArrayList<Leaf> newList = new ArrayList<Leaf>();
								newList.add(l);
								leafPool.put(key, newList);
							}
						heapOfLeaves.remove(l);
					}
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private class TrieInputProcessor extends Thread {
		
		@Override
		public void run() {
			synchronized(this){
				while(true){
					
				}
			}
		}
		
		private ArrayList<Leaf> filter(String str, ArrayList<Leaf> filteredPool){
			for(int i=0;i<str.length();i++){
//				filteredPool = filteredPool.get(str.charAt(i));
				
			}
			return null;
		}
		
	}
}
