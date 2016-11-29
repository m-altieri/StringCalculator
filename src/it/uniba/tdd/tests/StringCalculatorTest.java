package it.uniba.tdd.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.uniba.tdd.StringCalculator;
import it.uniba.tdd.StringCalculatorException;

public class StringCalculatorTest {

	//Arrange
	StringCalculator sc = new StringCalculator();
	
	@Test
	public void emptyStringReturnsZero() throws StringCalculatorException {
		
		assertEquals(0, sc.add(""));
	}
	
	@Test
	public void singleDigitReturnsItself() throws StringCalculatorException {
		
		assertEquals(0, sc.add("0"));
		assertEquals(9, sc.add("9"));
	}
	
	@Test
	public void twoDigitsReturnsTheirSum() throws StringCalculatorException {
		
		assertEquals(0, sc.add("0,0"));
		assertEquals(10, sc.add("9,1"));
		assertEquals(18, sc.add("9,9"));
	}
	
	@Test
	public void singleNumberReturnsItself() throws StringCalculatorException {
		
		assertEquals(10, sc.add("10"));
		assertEquals(99 ,sc.add("99"));
		assertEquals(523, sc.add("523"));
	}
	
	@Test
	public void twoNumbersReturnsTheirSum() throws StringCalculatorException {
		
		assertEquals(20, sc.add("10,10"));
		assertEquals(100, sc.add("99,1"));
	}

	@Test
	public void arbitraryAmountOfNumbers() throws StringCalculatorException {

		assertEquals(0, sc.add("0,0,0,0,0,0"));
		assertEquals(6, sc.add("1,1,1,1,1,1"));
		assertEquals(100, sc.add("5,5,10,10,20,20,30"));
		assertEquals(5, sc.add("1002,1003,1500,5"));
	}
	
	@Test
	public void extraCases() throws StringCalculatorException {
		
		assertEquals(0, sc.add("0"));
		assertEquals(0, sc.add(""));
		assertEquals(999, sc.add("999"));
		assertEquals(10, sc.add("1,1,1,1,1,1,1,1,1,1"));
	}
	
	@Test
	public void newLinesBetweenNumberAreAllowed() throws StringCalculatorException {
		
		assertEquals(10, sc.add("5\n5"));
		assertEquals(999, sc.add("100,500\n300,99"));
	}
	
	/*
	 * To change a delimiter, the beginning of the string will contain a separate line 
	 * that looks like this: "//[delimiter]\n[numbers…]". 
	 * For example, "//;\n1;2" should return 3 where the default delimiter is ";". 
	 * The first line is optional.
	 */
	@Test
	public void customDelimitersAreAllowed() throws StringCalculatorException {
		
		assertEquals(3, sc.add("//;\n1;2"));
		assertEquals(10, sc.add("//-\n3-4,3"));
		assertEquals(35, sc.add("//d\n9d1d20d3\n1,1"));
	}
	
	@Test(expected = StringCalculatorException.class)
	public void negativeNumbersThrowStringCalculatorException() throws StringCalculatorException {
		
		sc.add("5,-5");
	}
	
	@Test
	public void numbersBiggerThan1000AreIgnored() throws StringCalculatorException {
		
		assertEquals(2, sc.add("2,1001"));
		assertEquals(1002, sc.add("2,1000"));
	}
	
	//Delimiters can be of any length with the following format: "//[delimiter]\n". 
	//For example: "//[***]\n1***2***3" should return 6
	@Test
	public void longCumstomDelimitersComesInBrackets() throws StringCalculatorException {
		
		assertEquals(6, sc.add("//[***]\n1***2***3"));
		assertEquals(10, sc.add("//[*?*]\n5*?*2*?*3"));
	}
	
	//Allow multiple delimiters like this: "//[delim1][delim2]\n". For example "//[*][%]\n1*2%3" should return 6
	@Test
	public void multipleDelimitersInSeparatedBrackets() throws StringCalculatorException {
		
		assertEquals(6, sc.add("//[*][%]\n1*2%3"));
	}
	
	@Test
	public void multipleLongDelimitersAllowed() throws StringCalculatorException {
		
		assertEquals(5, sc.add("//[;;][,,]\n1;;2,,2"));
	}
}
