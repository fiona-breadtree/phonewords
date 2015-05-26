package com.fiona.phone2word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;

import org.apache.log4j.Logger;

import com.fiona.phone2word.util.CartesianProduct;
import com.fiona.phone2word.util.StringJointer;
import com.fiona.phone2word.util.StringProcesser;
import com.fiona.phone2word.util.SubStrCombinationFinder;

public class PhoneWordConverter {
	private static Logger log = Logger.getLogger(PhoneWordConverter.class);

	public final static String WORD_BOUNDARY = "-";
	public final static String NO_BOUNDARY = "";
	public final static int MAX_LENGTH = 20;

	private StringProcesser strProcessor = null;
	private StringJointer jointer = null;
	private PhoneDictionary dictionary = null;
	private int maxLength = 15;

	public PhoneWordConverter(String dictionaryFileName) {
		super();
		dictionary = new PhoneDictionary(dictionaryFileName);
		strProcessor = new StringProcesser();
		jointer = new StringJointer();
	}

	public List<String> convert(String phoneNumber) {
		if (phoneNumber.length() > maxLength) {
			log.warn("Cannot support the phone number exceed max length " + maxLength);
			return null;
		}
		log.debug("Begin to covert phone number : " + phoneNumber);
		//remove all punctuation and whitespace from phone number
		String processedPhoneNumber = strProcessor.processNumber(phoneNumber);

		List<String> generatedWords = new ArrayList<String>();
		List<List<String>> foundWordsCombinations = new ArrayList<List<String>>();

		// find all substrings combinations for input phone number.
		SubStrCombinationFinder finder = new SubStrCombinationFinder();
		List<List<String>> subStrCombinations = finder.findAllCombinationNoRecursion(processedPhoneNumber);

		// look up all words for combinations
		for (List<String> combination : subStrCombinations) {
			List<List<String>> word4OneCombination = lookupWordsFromDictionary(combination);
			for (List<String> words : word4OneCombination) {
				if (!hasConsecutiveTwoNumbers(words)) {
					// add results which don't have two consecutive numbers 
					foundWordsCombinations.add(words);
				}
			}
		}

		for (List<String> words : foundWordsCombinations) {
			generatedWords.add(jointer.join(words, WORD_BOUNDARY));
		}
		
		log.debug("End to covert phone number : " + phoneNumber);
		return generatedWords;
	}

	public List<List<String>> lookupWordsFromDictionary(List<String> subset) {
		List<List<String>> seperatedWords4OneSubset = new ArrayList<List<String>>();
		for (String subStr : subset) {
			List<String> matchedWords = null;
			SortedSet<String> foundWords = dictionary.lookup(subStr);
			
			if (foundWords != null) {
				matchedWords = new ArrayList<String>(foundWords);

			} else {
				matchedWords = new ArrayList<String>();
				matchedWords.add(subStr);
			}

			if (seperatedWords4OneSubset.size() == 0) {
				for (String word : matchedWords) {
					seperatedWords4OneSubset.add(new ArrayList<String>(Arrays.asList(word)));
				}
			} else {
				CartesianProduct carPro = new CartesianProduct();
				seperatedWords4OneSubset = carPro.process(seperatedWords4OneSubset, matchedWords);
			}
		}
		return seperatedWords4OneSubset;
	}
	
	private boolean hasConsecutiveTwoNumbers(List<String> inputWords) {
		String printedString = jointer.join(inputWords, NO_BOUNDARY);
		return strProcessor.hasConsecutiveDigits(printedString, 2);
	}

	public void loadDictionary(String dictionaryFileName) {
		this.dictionary.loadDictionary(dictionaryFileName);
	}

	public void setPhoneNumberMaxLength(int maxLength) {
		if (maxLength > MAX_LENGTH) {
			return;
		}
		this.maxLength = maxLength;
	}
	
}
