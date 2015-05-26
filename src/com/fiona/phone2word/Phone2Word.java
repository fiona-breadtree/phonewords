package com.fiona.phone2word;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.fiona.phone2word.util.FileReader;
import com.fiona.phone2word.util.LineReaderCallBack;
import com.fiona.phone2word.util.StringProcesser;

public class Phone2Word implements LineReaderCallBack, PhoneWordConvertCallback {
	private static Logger log = Logger.getLogger(Phone2Word.class);
	
	private final static String COMMAND_LOAD_DICTIONARY = "-d";
	private final static String COMMAND_LOAD_PHONES = "-p";
	
	private boolean isRunning = false;
	private StringProcesser strProcessor = null;
	private PhoneWordConverter converter = null;
	
	public Phone2Word() {
		strProcessor = new StringProcesser();
		ConvertPolicy policy = new NoTwoConsecutiveDigitsPolicy();
		converter = new PhoneWordConverter(null, policy, this);
	}

	public void excute(String[] args) {
		isRunning = true;
		
		FileReader reader = null;
		excuteFromInputParames(args, reader);
		
		Scanner in = null;
		try {
			in = new Scanner(System.in);
			while (isRunning) {
				showMenu();
				try {
					String command = in.nextLine().trim();
					if (command.equals("0")) {
						isRunning = false;
					} else if (command.equals("1")) {
						System.out.println("Enter a file name for phones:");
						String phoneFileName = in.nextLine().trim();
						excutePhoneFileLoad(phoneFileName, reader);
					} else if (command.equals("2")) {
						System.out.println("Enter a file name for dictionary:");
						String dictionaryName = in.nextLine().trim();
						excuteDictionaryLoad(dictionaryName);
					} else if (command.equals("3")) {
						System.out.println("Enter the max length for phone numbers:");
						String maxLengthStr = in.nextLine().trim();
						if (maxLengthStr.matches("[0-9]*")) {
							converter.setPhoneNumberMaxLength(Integer.parseInt(maxLengthStr));
						} else {
							System.out.println("Not a valid number, please retry");
						}
					} else if (command.equals("4")) {
						System.out.println("The max length of words in Dictionary is : " + converter.getWordMaxLength());
					} else {
						System.out.println("Cannot parse the input, please retry.");
					}
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	private void excuteDictionaryLoad(String dictionaryName) {
		System.out.println("==== Begin to load new dictionary with file " + dictionaryName + " ====");
		converter.loadDictionary(dictionaryName);
		System.out.println("==== End to load new dictionary with file " + dictionaryName + " ====");
	}

	private void excuteFromInputParames(String[] args, FileReader reader) {
		String phoneFileName = null;
		String dictionaryName = null;
		
		if (args.length < 2) {
			return;
		}
		int i = 0;
		while (i < args.length) {
			log.debug(args[i]);
			if (args[i].equals(COMMAND_LOAD_PHONES)) {
				phoneFileName = args[i+1];
			} else if (args[i].equals(COMMAND_LOAD_DICTIONARY)) {
				dictionaryName = args[i+1];
			} else {
				System.out.println("Cannot parse the command, please try.");
				break;
			}			
			i += 2;
		}
		
		if (dictionaryName != null && !dictionaryName.isEmpty() ) {
			excuteDictionaryLoad(dictionaryName);
		}
		
		if (phoneFileName != null) {
			try {
				excutePhoneFileLoad(phoneFileName, reader);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				System.out.println("Cannot read Phone file : " + phoneFileName);
			}
		}
	}

	private void excutePhoneFileLoad(String phoneFileName,
			FileReader reader) throws Exception {
		System.out.println("==== Begin to generate words for phone with file " + phoneFileName + " ====");
		if (reader == null) {
			reader = new FileReader(this);
		}
		reader.readFileByLine(phoneFileName);
		System.out.println("==== End to generate words for phone with file " + phoneFileName + " ====");
	}

	private void showMenu() {
		System.out.println("Phone2Word MenuList:");
		System.out.println("----1: Load phone file");
		System.out.println("----2: Load dictionary file");
		System.out.println("----3: Set max length of phone number");
		System.out.println("----4: Get max length of word in Dictionary");
		System.out.println("----0: Quit");
		System.out.print("Your input here: ");
	}

	@Override
	public void processLine(String strLine) {
		String processedStr = strProcessor.processNumber(strLine);
		if (processedStr == null || processedStr.isEmpty()) {
			return; //do nothing
		}
		
		converter.convert(strLine);

		if (converter.getHandledNumber() == 0) {
			System.out.println("No Match for : " + strProcessor.processNumber(strLine));
		} else {
			System.out.println(converter.getHandledNumber() + " words are found for  " + strProcessor.processNumber(strLine));
		}
	}

	public static void main(String[] args) {
		PropertyConfigurator.configure("config/log4j.properties");

		Phone2Word phone2Word = new Phone2Word();
		phone2Word.excute(args);
	}

	@Override
	public void handleGeneratedWords(String words) {
		System.out.println("----" + words);
	}

}
