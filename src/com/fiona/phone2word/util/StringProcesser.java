package com.fiona.phone2word.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringProcesser {
	
	private final String NON_NUMBER_REG = "[^0-9]";
	private final String PUNCTUATION_WHITEPACE_REG = "[\\p{Punct}\\p{Space}]";
	private final String REPLACEMENT_STRING = "";
	
	
	/**
	 * Handle the input string to include only 0-9 digits.
	 * @param input String
	 * @return
	 */
	public String processNumber(String input) {
		return this.process(input, NON_NUMBER_REG);
	}
	
	/**
	 * Handle the input String to remove all punctuations and white spaces.
	 * @param input
	 * @return
	 */
	public String processPunctuation(String input) {
		return this.process(input, PUNCTUATION_WHITEPACE_REG);
	}

	private String process(String input, String regEx) {
		if (input == null || input.isEmpty()) {
			return input;
		}
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(input);
		return matcher.replaceAll(REPLACEMENT_STRING);
	}
	
	/**
	 * Check if the input string contains consecutive digits.
	 * @param input
	 * @param consecutiveNum
	 * @return
	 */
	public boolean hasConsecutiveDigits(String input, int consecutiveNum) {
		if (input == null || input.isEmpty()) {
			return false;
		}
		if (consecutiveNum < 1) {
			return false;
		}
		
		boolean result = false;
		String regEx = "\\d{" + consecutiveNum + "}";

		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			result = true;
		}
		return result;
	}

}
