package com.ac.components;

/**
 * represents a structure containing the message id along with level node elements of all tries
 * @author sachin
 *
 */

public class L1Hash {
	
	private String msgId;
	private char[] l1node;
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public char[] getL1node() {
		return l1node;
	}
	public void setL1node(char[] l1node) {
		this.l1node = l1node;
	}
}
