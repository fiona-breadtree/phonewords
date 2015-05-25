package com.fiona.phone2word;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class PhoneDictionaryTest {
	@Rule
	public TestCaseName currentTestCaseName = new TestCaseName(this);

	private PhoneDictionary dictionary;

	@Before
	public void setUp() throws Exception {
		TestSuite.printTestCaseStart(currentTestCaseName);
		dictionary = new PhoneDictionary(null);
	}

	@After
	public void tearDown() throws Exception {
		TestSuite.printTestCaseEnd(currentTestCaseName);
	}

	@SuppressWarnings("unchecked")
	@Test 
	public void testLoadDictionary() throws Exception {
		dictionary.loadDictionary("config/testwords");
		Field instance = PhoneDictionary.class.getDeclaredField("numberWordsMap");
		instance.setAccessible(true);
		Map<String, SortedSet<String>> numberWordsMap = (Map<String, SortedSet<String>>) instance.get(dictionary);

		assertEquals(new TreeSet<String>(Arrays.asList("BALL", "CALL")), numberWordsMap.get("2255"));
		assertEquals(new TreeSet<String>(Arrays.asList("ME")), numberWordsMap.get("63"));
	}
	
	@Test
	public void testLookupFromDictionary() {
		dictionary.loadDictionary("config/testwords");
		assertEquals(new TreeSet<String>(Arrays.asList("BALL", "CALL")),
				dictionary.lookup("2255"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testWordsShortList() throws Exception, SecurityException {

		Method methodInstance = PhoneDictionary.class.getDeclaredMethod("initWordsInShortList", new Class[] {});
		methodInstance.setAccessible(true);
		methodInstance.invoke(dictionary,  new Object[] {});
		assertEquals(new TreeSet<String>(Arrays.asList("ACE")),
				dictionary.lookup("223"));
		
		Field instance = PhoneDictionary.class.getDeclaredField("numberWordsMap");
		instance.setAccessible(true);
		Map<String, SortedSet<String>> numberWordsMap = (Map<String, SortedSet<String>>) instance.get(dictionary);
		
		/*
		8428679--[VICTORY]
		7378378--[REQUEST]
		223--[ACE]
		968--[YOU]
		93--[WE]
		328--[EAT]
		966--[ZOO]
		9627--[XMAS]
		46--[IN]
		633--[ODD]
		663--[NOD]
		78425--[QUICK]
		4475--[GIRL]
		348--[FIT]
		729--[SAY]
		28746377--[BUSINESS]
		2255--[BALL, CALL]
		468--[HOT]
		8326--[TEAM]
		269--[BOY]
		5867--[JUMP]
		66639--[MONEY]
		63--[ME]
		86466--[UNION]
		7874--[PUSH]
		*/
		assertEquals(new TreeSet<String>(Arrays.asList("VICTORY")), numberWordsMap.get("8428679"));
		assertEquals(new TreeSet<String>(Arrays.asList("JUMP")), numberWordsMap.get("5867"));
		assertEquals(new TreeSet<String>(Arrays.asList("BALL", "CALL")), numberWordsMap.get("2255"));
	}

}
