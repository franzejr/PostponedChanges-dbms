package br.ufc.sgbd.recovery;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import br.ufc.sgbd.recovery.parser.Log;
import br.ufc.sgbd.recovery.parser.LogFileParser;
import br.ufc.sgbd.recovery.parser.LogRecord;

public class LogFileParserTest {

	private String logFilePath = "F:\\Eclipse Workspace\\sgbd-recovery\\src\\resources\\log2";

	@Test
	public void testMatchStringWithRegExpPattern() {
		String inputString = "[0|1|T1|w|x|10|20][1|3|T2|w|x|20|10]";
		Pattern pattern = Pattern.compile(LogRecord.recordRegExp);
		Matcher matcher = pattern.matcher(inputString);
		while (matcher.find()) {
			System.out.println(matcher.group(0));
		}
	}

	@Test
	public void testSplitStringBasedOnRegex() {
		String inputString = "[0|1|T1|w|x|10|20][1|3|T2|w|x|20|10]";
		String[] stringArray = inputString.split("\\[|\\||\\]");

		for (String string : stringArray) {
			System.out.println(string);
		}

	}

	@Test
	public void testTurnTextFileIntoAString() {
		LogFileParser logFileParser = new LogFileParser();

		String fileString = logFileParser.turnTextFileIntoString(logFilePath);

		System.out.println(fileString);
	}

	@Test
	public void testBuildLogRecordFromString() {
		String recordString = "[0|1|T1|w|x|10|20]";

		LogFileParser logFileParser = new LogFileParser();
		try {
			LogRecord record = logFileParser.buildLogRecordFromString(recordString);

			System.out.println(record);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		

	}

	@Test
	public void testSplitLogStringBasedOnTheRecordPattern() {
		LogFileParser logFileParser = new LogFileParser();

		String logString = logFileParser.turnTextFileIntoString(logFilePath);

		try {
			List<String> recordStrings = logFileParser.splitLogStringBasedOnTheRecordPattern(logString);

			for (String recordString : recordStrings) {
				System.out.println(recordString);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testBuildRecordListFromLogString() {
		LogFileParser logFileParser = new LogFileParser();

		String logString = logFileParser.turnTextFileIntoString(logFilePath);

		try {
			List<LogRecord> records = logFileParser.buildRecordListFromLogString(logString);

			for (LogRecord logRecord : records) {
				System.out.println(logRecord);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testParseLogFileAndReturnLogObject() {
		LogFileParser logFileParser = new LogFileParser();

		try {
			Log log = logFileParser.parseLogFileAndReturnLogObject(logFilePath);

			System.out.println(log);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
}
