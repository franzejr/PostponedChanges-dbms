package br.ufc.sgbd.recovery.algorithm;

import java.util.HashMap;

/*
 * This table contains one entry for each dirty page in the buffer pool, that is, 
 * each page with changes that are not yet reflected on disk. The entry contains a field recLSN, 
 * which is the LSN of the first log record that caused the page to become dirty. Note that this LSN 
 * identifies the earliest log record that might have to be redone for this page during restart from a crash.
 */
public class DirtyPageTable extends HashMap<Character, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
