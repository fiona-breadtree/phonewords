package com.fiona.phone2word;

import com.fiona.phone2word.util.StringProcesser;

public class NoTwoConsecutiveDigitsPolicy implements ConvertPolicy {

	private StringProcesser strProcessor = null;
	
	public NoTwoConsecutiveDigitsPolicy() {
		strProcessor = new StringProcesser();
	}
	
	@Override
	public boolean meetPolicy(String generatedWord) {
		if (strProcessor.hasConsecutiveDigits(generatedWord, 2)) {
			return false;
		} else {
			return true;
		}
	}

}
