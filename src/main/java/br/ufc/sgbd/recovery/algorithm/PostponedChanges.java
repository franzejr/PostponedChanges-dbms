package br.ufc.sgbd.recovery.algorithm;

import java.util.ArrayList;
import java.util.List;

import br.ufc.sgbd.recovery.parser.Log;
import br.ufc.sgbd.recovery.parser.LogRecord;
import br.ufc.sgbd.recovery.parser.OperationType;

/*
 * This algorithm is a implementation from the ARIES protocol. 
 * 
 * Only the redo phase is implemented. So, it's a
 * NO-UNDO/REDO implementation
 */
public class PostponedChanges implements ARIES {

	/*
	 * Return the Minimum Log Sequence Number for Dirty Page
	 * 
	 * @return minLSN
	 */
	private int getMinLSNFromDirtyPageTable() {
		// Min Log Sequence Number
		int minLSN = Integer.MAX_VALUE;

		for (char key : dirtyPageTable.keySet()) {
			int value = dirtyPageTable.get(key);
			if (value < minLSN) {
				minLSN = value;
			}
		}

		return minLSN;
	}

	/*
	 *Return a DiskImage. This is the main point of our algorithm.
	 *We return, basically, a redo-list. Remembering that the ARIES
	 *protocol return two list: and undo and other redo, but here
	 *we are returning only the redo list. In our algorithm, we can
	 *redo only the transaction which have been committed. 
	 *
	 * @return diskImage
	 */
	@Override
	public DiskImage redo(List<LogRecord> recordList) throws Exception {
		DiskImage diskImage = new DiskImage();
		for (LogRecord logRecord : recordList) {
			char currentPage = logRecord.getTargetObject();
			int afterOperationPageValue = logRecord
					.getAfterOperationObjectImage();
			diskImage.put(currentPage, afterOperationPageValue);
		}

		return diskImage;
	}
	/*
	 * Build a Redo RecordList  from a log
	 * 
	 * @return redoRecorList
	 */
	public List<LogRecord> buildRedoRecordList(Log log) throws Exception {
		int startPoint = getMinLSNFromDirtyPageTable();

		List<LogRecord> logRecords = log.getRecords();

		List<LogRecord> redoRecordList = new ArrayList<LogRecord>();

		for (int i = startPoint; i < logRecords.size(); i++) {
			LogRecord currentRecord = logRecords.get(i);

			if (currentRecord.getOperationType().equals(OperationType.WRITE)) {

				String currentTransactionId = currentRecord.getTransactionID();
				Integer transanctionLastLSN = transactionTable
						.get(currentTransactionId);

				if (transanctionLastLSN != null) {
					LogRecord lastTransactionRecord = logRecords
							.get(transanctionLastLSN);
					OperationType operationType = lastTransactionRecord
							.getOperationType();

					if (operationType.equals(OperationType.COMMIT)) {
						redoRecordList.add(currentRecord);
					}

				} else {
					throw new Exception("transactionId not found.");
				}

			}
		}

		return redoRecordList;
	}

	/*
	 * 
	 */
	@Override
	public void analyze(Log log) throws Exception {
		// List<LogRecord> redo = new ArrayList<LogRecord>();

		for (LogRecord record : log.getRecords()) {

			String transactionId = record.getTransactionID();
			int logSequenceNumber = record.getLogSequenceNumber();
			OperationType operationType = record.getOperationType();
			char targetObject = record.getTargetObject();

			if (transactionTable.get(transactionId) == null) {
				transactionTable.put(transactionId, logSequenceNumber);
			} else {
				transactionTable.put(transactionId, logSequenceNumber);
			}

			if (operationType.equals(OperationType.WRITE)) {
				if (dirtyPageTable.get(targetObject) == null) {
					dirtyPageTable.put(targetObject, logSequenceNumber);
				}

			}
		}
	}

	/*
	 * There are no undo phase
	 */
	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	public DiskImage recoverDisk(Log log) throws Exception {
		DiskImage finalDiskImage = new DiskImage();

		analyze(log);

		List<LogRecord> recordList = buildRedoRecordList(log);

		finalDiskImage = redo(recordList);

		return finalDiskImage;
	}
}
