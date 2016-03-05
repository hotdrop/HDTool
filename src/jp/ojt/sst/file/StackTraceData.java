package jp.ojt.sst.file;

/**
 * StackTraceData
 * This class store a one-stackTrace information read from the Stack Trace File.
 *
 */
public class StackTraceData {

	/** A string representation of read stackTrace date */
	private String dateStr;
	
	/** A string representation of this exception */
	private String exceptionStr;
	
	/** Messages */
	private String message;
	
	/** The first line was found in the search word */
	private String findLine;
	
	/** Count of the same kinds of exceptions */
	private int count = 1;
	
	/**
	 * Constructor 
	 * 
	 * @param argDateStr
	 * @param argExceptionStr
	 * @param argMessage
	 * @param argLine
	 */
	public StackTraceData(String argDateStr, String argExceptionStr, String argMessage, String argLine) {
		dateStr =  argDateStr;
		exceptionStr = argExceptionStr;
		message = argMessage;
		findLine = argLine;
	}
	
	/**
	 * Add one to the count field of this class. 
	 */
	public void addCount() {
		count++;
	}

	/**
	 * Returns a csv string representation of this stackTraceData.
	 * Fields of this class are separated by the characters comma(,).
	 * And each item doesn't put double quotation marks.   
	 * @return a csv string representation of this stackTraceData
	 */
	public String toCSVString() {
		String[] arr = {dateStr, exceptionStr, message, findLine, String.valueOf(count)};
		return String.join(",", arr); 
	}
}
