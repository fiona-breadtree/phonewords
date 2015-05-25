package com.fiona.phone2word;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.fiona.phone2word.util.FileReader;
import com.fiona.phone2word.util.LineReaderCallBack;
import com.fiona.phone2word.util.StringProcesser;

public class PhoneDictionary implements LineReaderCallBack {
	private static Logger log = Logger.getLogger(PhoneDictionary.class);

	private final static String DEFAULT_WORDS_FILE = "config/words";
	private static Map<Character, String> charNumberMap = null;
	
	private Map<String, SortedSet<String>> numberWordsMap = null;
	
	Map<String, SortedSet<String>> loadedWords = null;
	
	private StringProcesser preProcessor = null;
	
	private int maxLengthOfWord = 0;
	private int maxLengthOfWordWhenLoading = 0;
	
	static {
		charNumberMap = new HashMap<Character, String>();
		charNumberMap.put(new Character('A'), "2");
		charNumberMap.put(new Character('B'), "2");
		charNumberMap.put(new Character('C'), "2");
		charNumberMap.put(new Character('D'), "3");
		charNumberMap.put(new Character('E'), "3");
		charNumberMap.put(new Character('F'), "3");
		charNumberMap.put(new Character('G'), "4");
		charNumberMap.put(new Character('H'), "4");
		charNumberMap.put(new Character('I'), "4");
		charNumberMap.put(new Character('J'), "5");
		charNumberMap.put(new Character('K'), "5");
		charNumberMap.put(new Character('L'), "5");
		charNumberMap.put(new Character('M'), "6");
		charNumberMap.put(new Character('N'), "6");
		charNumberMap.put(new Character('O'), "6");
		charNumberMap.put(new Character('P'), "7");
		charNumberMap.put(new Character('Q'), "7");
		charNumberMap.put(new Character('R'), "7");
		charNumberMap.put(new Character('S'), "7");
		charNumberMap.put(new Character('T'), "8");
		charNumberMap.put(new Character('U'), "8");
		charNumberMap.put(new Character('V'), "8");
		charNumberMap.put(new Character('W'), "9");
		charNumberMap.put(new Character('X'), "9");
		charNumberMap.put(new Character('Y'), "9");
		charNumberMap.put(new Character('Z'), "9");
	}

	public PhoneDictionary(String fileName) {
		preProcessor = new StringProcesser();
		
		if (fileName == null || fileName.isEmpty()) {
			numberWordsMap = loadDefaultWords();
		} else {
			try {
				numberWordsMap = doLoadDictionaryFile(fileName);
			} catch (Exception e) {
				log.error("Cannot load dictionay file with: " + fileName, e);
				numberWordsMap = loadDefaultWords();
			}
		}
	}

	public void loadDictionary(String fileName) {
		try {
			numberWordsMap = this.doLoadDictionaryFile(fileName);
		} catch (Exception e) {
			log.error("Cannot load file with name : " + fileName
					+ " continue to use previous dictionary.");
		}
	}

	private Map<String, SortedSet<String>> loadDefaultWords() {
		try {
			return doLoadDictionaryFile(DEFAULT_WORDS_FILE);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			initWordsInShortList();
		}
		return null;
	}
	
	@Override
	public void processLine(String strLine) {
		processWord(strLine, loadedWords);	
	}

	private Map<String, SortedSet<String>> doLoadDictionaryFile(String fileName)
			throws Exception {
		if (fileName == null || fileName.isEmpty()) {
			throw new Exception("File name cannot be null or empty string");
		}
		
		loadedWords = new HashMap<String, SortedSet<String>>();
		maxLengthOfWordWhenLoading = 0;
		
		FileReader fileReader = new FileReader(this);
		fileReader.readFileByLine(fileName);
		
		maxLengthOfWord = maxLengthOfWordWhenLoading;
		return loadedWords;
	}

	private void processWord(String word, Map<String, SortedSet<String>> loadedWords) {
		word = preProcessor.processPunctuation(word);
		if (!word.matches("^[a-zA-Z]*")) {// if still contains other chars, ignore it.
			return;
		}

		word = word.toUpperCase();

		if (word.length() == 1 && !(word.equals("A") || word.equals("I"))) {
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int index = 0; index < word.length(); index++) {
			sb.append(charNumberMap.get(word.charAt(index)));
		}

		String numberStr = sb.toString();
		if (maxLengthOfWordWhenLoading < numberStr.length()) {
			maxLengthOfWordWhenLoading = numberStr.length();
		}
		if (loadedWords.containsKey(numberStr)) {
			loadedWords.get(numberStr).add(word);
		} else {
			loadedWords.put(numberStr,
					new TreeSet<String>(Arrays.asList(word)));
		}
	}
	
	public SortedSet<String> lookup(String word) {
		return numberWordsMap.get(word);
	}

	private void initWordsInShortList() {
		if (numberWordsMap != null) {
			numberWordsMap.clear();
		}
		numberWordsMap = new HashMap<String, SortedSet<String>>();
		processWord("ACE", this.numberWordsMap);
		processWord("BALL", this.numberWordsMap);
		processWord("BOY", this.numberWordsMap);
		processWord("BUSINESS", this.numberWordsMap);
		processWord("CALL", this.numberWordsMap);
		processWord("EAT", this.numberWordsMap);
		processWord("FIT", this.numberWordsMap);
		processWord("GIRL", this.numberWordsMap);
		processWord("HOT", this.numberWordsMap);
		processWord("IN", this.numberWordsMap);
		processWord("JUMP", this.numberWordsMap);
		processWord("ME", this.numberWordsMap);
		processWord("MONEY", this.numberWordsMap);
		processWord("NOD", this.numberWordsMap);
		processWord("ODD", this.numberWordsMap);
		processWord("PUSH", this.numberWordsMap);
		processWord("QUICK", this.numberWordsMap);
		processWord("REQUEST", this.numberWordsMap);
		processWord("SAY", this.numberWordsMap);
		processWord("TEAM", this.numberWordsMap);
		processWord("UNION", this.numberWordsMap);
		processWord("VICTORY", this.numberWordsMap);
		processWord("WE", this.numberWordsMap);
		processWord("XMAS", this.numberWordsMap);
		processWord("YOU", this.numberWordsMap);
		processWord("ZOO", this.numberWordsMap);
		
		maxLengthOfWord = maxLengthOfWordWhenLoading;
	}

	public int getMaxLengthOfWord() {
		return maxLengthOfWord;
	}
	
}
