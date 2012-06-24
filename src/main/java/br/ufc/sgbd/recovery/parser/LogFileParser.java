/**
 * 
 */
package br.ufc.sgbd.recovery.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser of the log file.
 * 
 * 
 */
public class LogFileParser {

	Pattern logRecordPattern = Pattern.compile(LogRecord.recordRegExp);

	public String turnTextFileIntoString(String filePath) {

		StringBuilder fileContent = new StringBuilder();

		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
			try {
				String line = null;
				while ((line = fileReader.readLine()) != null) {
					fileContent.append(line);
				}
			} finally {
				fileReader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileContent.toString();
	}

	public LogRecord buildLogRecordFromString(String recordString) throws Exception {
		String[] recordParameters = recordString.split(LogRecord.recordSplitRegExp);

		if (recordParameters.length == 8) {
			OperationType operationType = OperationType.valueOf(recordParameters[4].charAt(0));
			if (operationType == OperationType.WRITE || operationType == OperationType.READ) {
				return new LogRecordBuilder().logSequenceNumber(Integer.parseInt(recordParameters[1]))
						.transactionTimestamp(Integer.parseInt(recordParameters[2])).transactionID(recordParameters[3])
						.operationType(operationType).operationTargetObject(recordParameters[5].charAt(0))
						.beforeOperationObjectImage(Integer.parseInt(recordParameters[6]))
						.afterOperationObjectImage(Integer.parseInt(recordParameters[7])).buildLogRecord();
			} else {
				return new LogRecordBuilder().logSequenceNumber(Integer.parseInt(recordParameters[1]))
						.transactionTimestamp(Integer.parseInt(recordParameters[2])).transactionID(recordParameters[3])
						.operationType(operationType).operationTargetObject(recordParameters[5].charAt(0))
						.beforeOperationObjectImage(0).afterOperationObjectImage(0).buildLogRecord();
			}

		}

		throw new Exception("The given string contains a invalid number of parameters to build a log record.");
	}

	public List<String> splitLogStringBasedOnTheRecordPattern(String logString) throws Exception {
		Matcher matcher = logRecordPattern.matcher(logString);

		List<String> recordStrings = new ArrayList<String>();

		while (matcher.find()) {
			recordStrings.add(matcher.group());
		}

		if (recordStrings.size() != 0) {
			return recordStrings;
		} else {
			throw new Exception("No log records were found");
		}
	}

	public List<LogRecord> buildRecordListFromLogString(String logString) throws Exception {
		List<String> recordStrings = splitLogStringBasedOnTheRecordPattern(logString);

		List<LogRecord> recordList = new ArrayList<LogRecord>();

		for (String recordString : recordStrings) {
			recordList.add(buildLogRecordFromString(recordString));
		}

		return recordList;
	}

	public Log parseLogFileAndReturnLogObject(String filePath) throws Exception {
		String logString = this.turnTextFileIntoString(filePath);

		List<LogRecord> logRecords = buildRecordListFromLogString(logString);

		return new Log(logRecords);
	}
}
