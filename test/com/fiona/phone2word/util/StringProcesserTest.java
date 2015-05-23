package com.fiona.phone2word.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.fiona.phone2word.TestCaseName;
import com.fiona.phone2word.TestSuite;
import com.fiona.phone2word.util.StringProcesser;

public class StringProcesserTest {
	@Rule
	public TestCaseName currentTestCaseName = new TestCaseName(this);
	
	private StringProcesser processor = null;
	
	@Before
	public void setUp() throws Exception {
		TestSuite.printTestCaseStart(currentTestCaseName);
		processor = new StringProcesser();
	}

	@After
	public void tearDown() throws Exception {
		TestSuite.printTestCaseEnd(currentTestCaseName);
	}

	@Test
	public void testProcessNumber() {
		assertEquals("843489990", processor.processNumber("  fadg   8a4348999...f.da]     ]fpap[][]0fd  a   "));
		assertEquals("", processor.processNumber(" df   "));
		assertEquals("000", processor.processNumber(" 0  0     0   "));
		assertEquals(null, processor.processNumber(null));
		assertEquals("", processor.processNumber(""));
	}

	@Test
	public void testProcessPunctuation() {
		assertEquals("fdafdapo", processor.processPunctuation("fda?+fda=   !@po"));
		assertEquals(null, processor.processPunctuation(null));
		assertEquals("", processor.processPunctuation(""));
	}
	
	@Test
	public void testHasConsecutiveTwoNumbers() {
		assertEquals(true, processor.hasConsecutiveDigits("23355", 2));
		assertEquals(false, processor.hasConsecutiveDigits("23355", 6));
		assertEquals(false, processor.hasConsecutiveDigits("2CALLME", 2));
		assertEquals(true, processor.hasConsecutiveDigits("2CALL63", 2));
		assertEquals(false, processor.hasConsecutiveDigits("2CALL63", 3));
		assertEquals(false, processor.hasConsecutiveDigits(null, 2));
		assertEquals(false, processor.hasConsecutiveDigits("", 2));
	}
}
