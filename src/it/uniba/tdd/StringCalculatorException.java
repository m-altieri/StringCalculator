package it.uniba.tdd;

public class StringCalculatorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	@Override
	public String getMessage()
	{
		return message;
	}
	
	public StringCalculatorException()
	{
		this("");
	}
	
	public StringCalculatorException(String s)
	{
		message = s;
	}
}