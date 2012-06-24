package br.ufc.sgbd.recovery;

import java.util.List;

import org.junit.Test;

import br.ufc.sgbd.recovery.algorithm.ARIES;
import br.ufc.sgbd.recovery.algorithm.DiskImage;
import br.ufc.sgbd.recovery.algorithm.PostponedChanges;
import br.ufc.sgbd.recovery.parser.Log;
import br.ufc.sgbd.recovery.parser.LogFileParser;
import br.ufc.sgbd.recovery.parser.LogRecord;

public class PostponedChangesTest {

	private String logFilePath = "/Users/franzejr/programming/workspace-java/sgbd-recovery/src/resources/log3";

	@Test
	public void testAnalyze() {

		LogFileParser logFileParser = new LogFileParser();

		PostponedChanges postponedChanges = new PostponedChanges();

		try {
			Log log = logFileParser.parseLogFileAndReturnLogObject(logFilePath);

			postponedChanges.analyze(log);

			System.out.println(ARIES.dirtyPageTable);
			System.out.println(ARIES.transactionTable);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testBuildRedoRecordList() {
		LogFileParser logFileParser = new LogFileParser();

		PostponedChanges postponedChanges = new PostponedChanges();

		try {
			Log log = logFileParser.parseLogFileAndReturnLogObject(logFilePath);

			postponedChanges.analyze(log);

			List<LogRecord> records = postponedChanges.buildRedoRecordList(log);

			for (LogRecord logRecord : records) {
				System.out.println(logRecord);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testRedo() {
		LogFileParser logFileParser = new LogFileParser();

		PostponedChanges postponedChanges = new PostponedChanges();

		try {
			Log log = logFileParser.parseLogFileAndReturnLogObject(logFilePath);

			postponedChanges.analyze(log);

			List<LogRecord> recordList = postponedChanges.buildRedoRecordList(log);

			DiskImage finalDiskImage = postponedChanges.redo(recordList);

			System.out.println(finalDiskImage);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
