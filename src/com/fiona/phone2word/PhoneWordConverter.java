package com.fiona.phone2word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;

import org.apache.log4j.Logger;

import com.fiona.phone2word.util.CartesianProduct;
import com.fiona.phone2word.util.FoundCombinationCallback;
import com.fiona.phone2word.util.StringJointer;
import com.fiona.phone2word.util.StringProcesser;
import com.fiona.phone2word.util.SubStrCombinationFinder;

public class PhoneWordConverter implements FoundCombinationCallback {
	private static Logger log = Logger.getLogger(PhoneWordConverter.class);

	public final static String WORD_BOUNDARY = "-";
	public final static String NO_BOUNDARY = "";
	public final static int MAX_LENGTH = 200;

	private PhoneWordConvertCallback callback = null;
	private StringProcesser strProcessor = null;
	private ConvertPolicy policy;
	private StringJointer jointer = null;
	private PhoneDictionary dictionary = null;
	private long foundNumber = 0;
	private int maxLength = 200;

	public PhoneWordConverter(String dictionaryFileName, ConvertPolicy policy, PhoneWordConvertCallback callback) {
		super();
		dictionary = new PhoneDictionary(dictionaryFileName);
		this.policy = policy;
		this.callback = callback;
		strProcessor = new StringProcesser();
		jointer = new StringJointer();
	}

	public void convert(String phoneNumber) {
		foundNumber = 0;
		if (phoneNumber.length() > maxLength) {
			log.warn("Cannot support the phone number exceed max length " + maxLength);
			return;
		}
		log.debug("Begin to covert phone number : " + phoneNumber);
		//remove all punctuation and whitespace from phone number
		String processedPhoneNumber = strProcessor.processNumber(phoneNumber);

		// find all substrings combinations for input phone number.
		SubStrCombinationFinder finder = new SubStrCombinationFinder(this);
		finder.findCombinationsOneByOne(processedPhoneNumber);
	}
	
	private List<List<String>> combineLookupResults(List<String> combination) {
		List<List<String>> words4OneCombination = new ArrayList<List<String>>();
		CartesianProduct carPro = new CartesianProduct();

		for (String subStr : combination) {
			List<String> matchedWords = null;
			
			SortedSet<String> foundedWords = dictionary.lookup(subStr);
			if (foundedWords != null) {
				matchedWords = new ArrayList<String>(foundedWords);
			} else {
				matchedWords = new ArrayList<String>();
				matchedWords.add(subStr);
			}
	
			if (words4OneCombination.size() == 0) {
				for (String word : matchedWords) {
					words4OneCombination.add(new ArrayList<String>(Arrays.asList(word)));
				}
			} else {
				words4OneCombination = carPro.process(words4OneCombination, matchedWords);
			}
		}
		
		return words4OneCombination;
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
	
	public int getWordMaxLength() {
		return dictionary.getMaxLengthOfWord();
	}

	@Override
	public void handle(List<String> combination) {
		List<List<String>> word4OneCombination = combineLookupResults(combination);
		for (List<String> words : word4OneCombination) {
			if (policy.meetPolicy(jointer.join(words, NO_BOUNDARY))) {
				System.out.println(jointer.join(words, WORD_BOUNDARY));
				foundNumber++;
				if (callback != null) {
					callback.handleGeneratedWords(jointer.join(words, WORD_BOUNDARY));
				}
			}
		}
	}

	@Override
	public long getHandledNumber() {
		return foundNumber;
	}

	@Override
	public void excutionFinished() {
		log.debug("Excution finished.");
	}
}
