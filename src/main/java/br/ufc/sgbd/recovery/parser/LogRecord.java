package br.ufc.sgbd.recovery.parser;

/**
 * Class that defines the structure of a record of the log file.
 * Basically, this is our main structure to represent the data and
 * transactions.
 * 
 */
public class LogRecord {
	/*
	 * Regular Expression from a Record
	 */
	public static final String recordRegExp = "(\\[[0-9]+\\|[0-9]+\\|T[0-9]+\\|(w|r)\\|[a-z]\\|[0-9]+\\|[0-9]+\\])|(\\[[0-9]+\\|[0-9]+\\|T[0-9]+\\|(a|c)\\|_\\|_\\|_\\])";
	/*
	 * Way to do split on the Record
	 */
	public static final String recordSplitRegExp = "\\[|\\||\\]";
	/*
	 * Log Sequence Number
	 */
	private int logSequenceNumber;
	/*
	 * Timestamp from transaction
	 */
	private int transactionTimestamp;
	private String transactionId;
	/*
	 * The OperationType may be:
	 * READ('r'), WRITE('w'), ABORT('a'), COMMIT('c');
	 */
	private OperationType operationType;
	private char targetObject;
	/*
	 * Before to do Operation, the result was it.
	 */
	private int beforeOperationObjectImage;
	/*
	 * After to do Operation, the resul is it.
	 */
	private int afterOperationObjectImage;

	public LogRecord(int logSequenceNumber, int transactionTimestamp, String transactionID,
			OperationType operationType, char operationTargetObject, int beforeOperationObjectImage,
			int afterOperationObjectImage) {
		super();
		this.logSequenceNumber = logSequenceNumber;
		this.transactionTimestamp = transactionTimestamp;
		this.transactionId = transactionID;
		this.operationType = operationType;
		this.targetObject = operationTargetObject;
		this.beforeOperationObjectImage = beforeOperationObjectImage;
		this.afterOperationObjectImage = afterOperationObjectImage;
	}

	public int getLogSequenceNumber() {
		return logSequenceNumber;
	}

	public void setLogSequenceNumber(int logSequenceNumber) {
		this.logSequenceNumber = logSequenceNumber;
	}

	public int getTransactionTimestamp() {
		return transactionTimestamp;
	}

	public void setTransactionTimestamp(int transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
	}

	public String getTransactionID() {
		return transactionId;
	}

	public void setTransactionID(String transactionID) {
		this.transactionId = transactionID;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public char getTargetObject() {
		return targetObject;
	}

	public void setTargetObject(char operationTargetObject) {
		this.targetObject = operationTargetObject;
	}

	public int getBeforeOperationObjectImage() {
		return beforeOperationObjectImage;
	}

	public void setBeforeOperationObjectImage(int beforeOperationObjectImage) {
		this.beforeOperationObjectImage = beforeOperationObjectImage;
	}

	public int getAfterOperationObjectImage() {
		return afterOperationObjectImage;
	}

	public void setAfterOperationObjectImage(int afterOperationObjectImage) {
		this.afterOperationObjectImage = afterOperationObjectImage;
	}

	public static String getRecordregexp() {
		return recordRegExp;
	}

	public static String getRecordsplitregexp() {
		return recordSplitRegExp;
	}

	@Override
	public String toString() {
		return "LSN: " + logSequenceNumber + " | Transaction Timestamp: " + transactionTimestamp
				+ " | Transaction ID: " + transactionId + " | Operation Type: " + operationType.value()
				+ " | Operation Target Object: " + targetObject + " | Before Object Image: "
				+ beforeOperationObjectImage + " | After Object Image: " + afterOperationObjectImage;
	}
}
