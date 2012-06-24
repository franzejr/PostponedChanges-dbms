package br.ufc.sgbd.recovery.parser;


/**
 * Types of query operations.
 * 
 * 
 */
public enum OperationType {
	READ('r'), WRITE('w'), ABORT('a'), COMMIT('c');

	private final char representativeChar;

	private OperationType(char representativeChar) {
		this.representativeChar = representativeChar;
	}

	public char value() {
		return representativeChar;
	}

	public static OperationType valueOf(char representativeChar) {
		switch (representativeChar) {
		case 'r':
			return OperationType.READ;
		case 'w':
			return OperationType.WRITE;
		case 'a':
			return OperationType.ABORT;
		case 'c':
			return OperationType.COMMIT;
		default:
			return null;
		}
	}
}
