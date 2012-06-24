package br.ufc.sgbd.recovery.parser;
/*
 * Buidl a LogRecord from List
 */
public class LogRecordBuilder {

	private int logSequenceNumber;
	private int transactionTimestamp;
	private String transactionID;
	private OperationType operationType;
	private char operationTargetObject;
	private int beforeOperationObjectImage;
	private int afterOperationObjectImage;

	public LogRecordBuilder logSequenceNumber(int logSequenceNumber) {
		this.logSequenceNumber = logSequenceNumber;
		return this;
	}

	public LogRecordBuilder transactionTimestamp(int transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
		return this;
	}

	public LogRecordBuilder transactionID(String transactionID) {
		this.transactionID = transactionID;
		return this;
	}

	public LogRecordBuilder operationType(OperationType operationType) {
		this.operationType = operationType;
		return this;
	}

	public LogRecordBuilder operationTargetObject(char operationTargetObject) {
		this.operationTargetObject = operationTargetObject;
		return this;
	}

	public LogRecordBuilder beforeOperationObjectImage(int beforeOperationObjectImage) {
		this.beforeOperationObjectImage = beforeOperationObjectImage;
		return this;
	}

	public LogRecordBuilder afterOperationObjectImage(int afterOperationObjectImage) {
		this.afterOperationObjectImage = afterOperationObjectImage;
		return this;
	}

	public LogRecord buildLogRecord() {
		return new LogRecord(logSequenceNumber, transactionTimestamp, transactionID, operationType,
				operationTargetObject, beforeOperationObjectImage, afterOperationObjectImage);
	}
}
