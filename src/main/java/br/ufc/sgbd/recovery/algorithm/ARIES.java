package br.ufc.sgbd.recovery.algorithm;

import java.util.List;

import br.ufc.sgbd.recovery.parser.Log;
import br.ufc.sgbd.recovery.parser.LogRecord;

/**
 * Interface that represents the AIRES protocol.
 * 
 * The recovery works in three phases. The first phase, Analysis, computes all
 * the necessary information from the logfile. The Redo phase restores the
 * database to the exact state at the crash, including all the changes of
 * uncommited transactions that were running at that point in time. The Undo
 * phase then undoes all uncommited changes, leaving the database in a
 * consistent state.
 * 
 */
public interface ARIES {

	DirtyPageTable dirtyPageTable = new DirtyPageTable();
	TransactionTable transactionTable = new TransactionTable();

	/*
	 * During the Analysis phase we restore the DPT and the TT as they were at
	 * the time of the crash.
	 */
	public void analyze(Log log) throws Exception;

	/*
	 * From the DPT we can compute the minimal Sequence Number of a dirty page.
	 * From there we have to start redoing the actions until the crash, in case
	 * they weren't persisted already.
	 */
	public DiskImage redo(List<LogRecord> recordList) throws Exception;

	/*
	 * After the Redo phase the database reflects the exact state at the crash.
	 * However the changes of uncommited transactions have to be undone to
	 * restore the database to a consistent state.
	 */
	public void undo();

	public DiskImage recoverDisk(Log log) throws Exception;

}
