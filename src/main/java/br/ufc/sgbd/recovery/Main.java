package br.ufc.sgbd.recovery;

import java.util.List;

import br.ufc.sgbd.recovery.gui.MainFrame;

public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		/*If you want to run this algorithm at Command Line */
//		String filePath = "/Users/franzejr/programming/workspace-java/sgbd-recovery/src/resources/log3";
//
//		LogFileParser fileParser = new LogFileParser();
//
//		Log log = fileParser.parseLogFileAndReturnLogObject(filePath);
//
//		PostponedChanges algorithm = new PostponedChanges();
//		
//		algorithm.analyze(log);
//		
//		System.out.println(ARIES.dirtyPageTable);
//		System.out.println(ARIES.transactionTable);
//		
//		List<LogRecord> recordList = algorithm.buildRedoRecordList(log);
//		
//		for (LogRecord logRecord : recordList) {
//			System.out.println(logRecord);
//		}
//		
//		DiskImage finalDiskImage = algorithm.redo(recordList);
//
//		System.out.println(finalDiskImage);
		
		/*Using GUI */
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}

}
