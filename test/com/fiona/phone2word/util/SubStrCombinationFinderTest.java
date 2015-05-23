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
	
	@Before
	public void setUp() throws Exception {
		TestSuite.printTestCaseStart(currentTestCaseName);
		finder = new SubStrCombinationFinder();
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
	public void testfindCombinationsByPace1() {
		assertEquals(Arrays.asList(Arrays.asList("2")), finder.findCombinationsByPace("2", 1));
		assertEquals(Arrays.asList(
				Arrays.asList("2", "3")), 
				finder.findCombinationsByPace("23", 1));

		assertEquals(Arrays.asList(
				Arrays.asList("2", "3", "4")), 
				finder.findCombinationsByPace("234", 1));
		
		assertEquals(Arrays.asList(
				Arrays.asList("2", "2", "5", "5", "6", "3")),
				finder.findCombinationsByPace("225563", 1));
	}
	
	@Test
	public void testfindCombinationsByPace2() {
		assertEquals(Arrays.asList(
				Arrays.asList("23")), 
				finder.findCombinationsByPace("23", 2));

		assertEquals(Arrays.asList(
				Arrays.asList("23", "4"),
				Arrays.asList("2", "34")), 
				finder.findCombinationsByPace("234", 2));
		
		assertEquals(Arrays.asList(
				Arrays.asList("22", "55", "63"),
				Arrays.asList("2", "25", "56", "3")
				),
				finder.findCombinationsByPace("225563", 2));
		
		assertEquals(Arrays.asList(
				Arrays.asList("22", "44", "55", "66", "77"),
				Arrays.asList("2", "24", "45", "56", "67", "7")
				),
				finder.findCombinationsByPace("2244556677", 2));
	}
	
	@Test
	public void testfindCombinationsByPace3() {
		assertEquals(Arrays.asList(
				Arrays.asList("234")), 
				finder.findCombinationsByPace("234", 3));
		
		assertEquals(Arrays.asList(
				Arrays.asList("225", "563"),
				Arrays.asList("2", "255", "63"),
				Arrays.asList("22", "556", "3")
				),
				finder.findCombinationsByPace("225563", 3));
		
		assertEquals(Arrays.asList(
				Arrays.asList("224", "455", "667", "7"),
				Arrays.asList("2", "244", "556", "677"),
				Arrays.asList("22", "445", "566", "77")
				),
				finder.findCombinationsByPace("2244556677", 3));
	}
	
	@Test
	public void testfindCombinationsByPace5() {
		assertEquals(Arrays.asList(
				Arrays.asList("234")), 
				finder.findCombinationsByPace("234", 5));
		
		assertEquals(Arrays.asList(
				Arrays.asList("22556", "3"),
				Arrays.asList("2", "25563"),
				Arrays.asList("22", "5563")
				),
				finder.findCombinationsByPace("225563", 5));
		
		assertEquals(Arrays.asList(
				Arrays.asList("22445", "56677"),
				Arrays.asList("2", "24455", "6677"),
				Arrays.asList("22", "44556", "677"),
				Arrays.asList("224", "45566", "77"),
				Arrays.asList("2244", "55667", "7")
				),
				finder.findCombinationsByPace("2244556677", 5));
		
	}
}
