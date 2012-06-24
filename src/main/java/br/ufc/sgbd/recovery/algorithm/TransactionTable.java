package br.ufc.sgbd.recovery.algorithm;

import java.util.HashMap;

/*
 * This table contains one entry for each active transaction. 
 * The entry contains (among other things) the transaction id, the status, and a 
 * field called lastLSN, which is the LSN of the most recent log record for this transaction. 
 * The status of a transaction can be that it is in progress, is committed, or is aborted. 
 * (In the latter two cases, the transaction will be removed from the table once certain ‘clean up’ 
 * steps are completed.)
 */
public class TransactionTable extends HashMap<String, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
