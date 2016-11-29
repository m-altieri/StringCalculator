package it.uniba.tdd;

public class StringCalculator {
	
	/**
	 * 
	 * @param numbersStr - the numbers to sum separated by comma
	 * @return the sum
	 */
	public int add(String numbersStr) throws StringCalculatorException {
		
		String regex = "(,|\\n)"; //used to split numbersStr at commas or newlines
		String[] delimiters = null; //null if not specified in numbersStr
		int sum = 0;
		String[] subs = null;
		
		//extract custom delimiter from numberStr
		if (numbersStr.contains("//")) {
			String delSub = "";
			if (numbersStr.charAt(2) != '[')
				regex = "(,|\\n|" + numbersStr.substring(2, 3) + ")";
			else {
				delSub = numbersStr.substring(2, numbersStr.indexOf("\n"));
				delimiters = delSub.split("\\[|\\]");
				regex = "(,|\\n";
				for (int i = 0; i < delimiters.length; i++)
					if (!delimiters[i].equals(""))
						regex += "|" + delimiters[i];
				regex += ")";
			}
			
			
			//fix special characters bug
			regex = regex.replace("*", "\\*");
			regex = regex.replace("?", "\\?");
		}
		
		//get the numbers
		subs = numbersStr.split(regex);
		
		if (numbersStr == "")
			sum = 0;
		
		for (int i = 0; i < subs.length; i++) {
			try {
				if (Integer.parseInt(subs[i]) < 0) //negatives throw exception
					throw new StringCalculatorException("The " + i + "th number is negative.");
				if (Integer.parseInt(subs[i]) <= 1000) //bigger than 1000 are ignored
					sum += Integer.parseInt(subs[i]);	
			} catch (NumberFormatException e) {
				; //ignore
			}
		}
		
		return sum;
	}
}