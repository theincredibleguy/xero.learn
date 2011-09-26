package com.ac.components;

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
	CharSequence[] branch;
}
