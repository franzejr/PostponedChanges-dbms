package br.ufc.sgbd.recovery.algorithm;

import java.util.HashMap;
/*
 * Abstraction to a Disk Image
 */
public class DiskImage extends HashMap<Character, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer getPageValue(char pageId) {
		return super.get(pageId);
	}

	public void putPage(char pageId, int value) {
		super.put(pageId, value);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (char pageId : keySet()) {
			buffer.append(pageId + " = " + get(pageId) + "\n");
		}

		return buffer.toString();
	}
}
