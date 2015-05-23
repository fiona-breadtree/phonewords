package com.fiona.phone2word.util;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.fiona.phone2word.TestCaseName;
import com.fiona.phone2word.TestSuite;

public class StringJointerTest {

	@Rule
	public TestCaseName currentTestCaseName = new TestCaseName(this);
	
	private StringJointer jointer = null;
	
	@Before
	public void setUp() throws Exception {
		TestSuite.printTestCaseStart(currentTestCaseName);
		jointer = new StringJointer();
	}

	@After
	public void tearDown() throws Exception {
		TestSuite.printTestCaseEnd(currentTestCaseName);
	}

	@Test
	public void testJoin() {
		assertEquals("aaa-bbb-ccc-ddd", jointer.join(Arrays.asList("aaa", "bbb", "ccc", "ddd"), "-"));
		assertEquals("aaabbbcccddd", jointer.join(Arrays.asList("aaa", "bbb", "ccc", "ddd"), ""));
		assertEquals("aaa===bbb===ccc===ddd", jointer.join(Arrays.asList("aaa", "bbb", "ccc", "ddd"), "==="));
	}

}
