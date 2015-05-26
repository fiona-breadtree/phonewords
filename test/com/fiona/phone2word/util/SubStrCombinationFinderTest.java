package com.fiona.phone2word.util;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.fiona.phone2word.TestCaseName;
import com.fiona.phone2word.TestSuite;
import com.fiona.phone2word.util.SubStrCombinationFinder;

public class SubStrCombinationFinderTest {
	@Rule
	public TestCaseName currentTestCaseName = new TestCaseName(this);
	
	private SubStrCombinationFinder finder ; 
	private FoundCombinationCallback callback;
	
	@Before
	public void setUp() throws Exception {
		TestSuite.printTestCaseStart(currentTestCaseName);
		callback = new FoundCombinationCallbackTestImpl();
		finder = new SubStrCombinationFinder(callback);
	}

	@After
	public void tearDown() throws Exception {
		TestSuite.printTestCaseEnd(currentTestCaseName);
	}

	@Test
	public void testCombinationNoRe() {
			assertEquals(Arrays.asList(Arrays.asList("2")), finder.findAllCombinationNoRecursion("2"));
			assertEquals(Arrays.asList(
					Arrays.asList("2", "3"), 
					Arrays.asList("23")), 
					finder.findAllCombinationNoRecursion("23"));

			assertEquals(Arrays.asList(
					Arrays.asList("2", "3", "4"), 
					Arrays.asList("2", "34"),
					Arrays.asList("23", "4"), 
					Arrays.asList("234")), 
					finder.findAllCombinationNoRecursion("234"));
			
			assertEquals(Arrays.asList(
					Arrays.asList("2", "3", "4", "5"), 
					Arrays.asList("2", "3", "45"),
					Arrays.asList("2", "34", "5"),
					Arrays.asList("2", "345"),
					Arrays.asList("23", "4", "5"), 
					Arrays.asList("23", "45"), 
					Arrays.asList("234", "5"), 
					Arrays.asList("2345")), 
					finder.findAllCombinationNoRecursion("2345"));
			
			assertEquals(Arrays.asList(
					Arrays.asList("2", "2", "5", "5", "6", "3"), 
					Arrays.asList("2", "2", "5", "5", "63"),
					Arrays.asList("2", "2", "5", "56", "3"),
					Arrays.asList("2", "2", "5", "563"),
					Arrays.asList("2", "2", "55", "6", "3"),
					Arrays.asList("2", "2", "55", "63"),
					Arrays.asList("2", "2", "556", "3"),
					Arrays.asList("2", "2", "5563"),
					Arrays.asList("2", "25", "5", "6", "3"),
					Arrays.asList("2", "25", "5", "63"),
					Arrays.asList("2", "25", "56", "3"), 
					Arrays.asList("2", "25", "563"),
					Arrays.asList("2", "255", "6", "3"), 
					Arrays.asList("2", "255", "63"), 
					Arrays.asList("2", "2556", "3"), 
					Arrays.asList("2", "25563"), 
					Arrays.asList("22", "5", "5", "6", "3"), 
					Arrays.asList("22", "5", "5", "63"),
					Arrays.asList("22", "5", "56", "3"),
					Arrays.asList("22", "5", "563"),
					Arrays.asList("22", "55", "6", "3"),
					Arrays.asList("22", "55", "63"),
					Arrays.asList("22", "556", "3"),
					Arrays.asList("22", "5563"),
					Arrays.asList("225", "5" ,"6", "3"), 
					Arrays.asList("225", "5", "63"),
					Arrays.asList("225", "56", "3"),
					Arrays.asList("225", "563"),
					Arrays.asList("2255", "6", "3"),
					Arrays.asList("2255", "63"),
					Arrays.asList("22556", "3"),
					Arrays.asList("225563")),
					finder.findAllCombinationNoRecursion("225563"));
	}
	
	@Test
	public void testFindCombinationsOneByOne() {
		finder.findCombinationsOneByOne("1234");
		finder.findCombinationsOneByOne("1234567890");
		finder.findCombinationsOneByOne("1234567890abc");
		finder.findCombinationsOneByOne("1234567890abcdefghij12345");
		System.out.println(callback.getHandledNumber());
	}
}
