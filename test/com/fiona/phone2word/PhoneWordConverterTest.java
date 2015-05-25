package com.fiona.phone2word;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class PhoneWordConverterTest {
	@Rule
	public TestCaseName currentTestCaseName = new TestCaseName(this);
	
	private PhoneWordConverter generator;
	
	@Before
	public void setUp() throws Exception {
		TestSuite.printTestCaseStart(currentTestCaseName);
		generator = new PhoneWordConverter("config/testwords",new NoTwoConsecutiveDigitsPolicy(), null);
	}

	@After
	public void tearDown() throws Exception {
		TestSuite.printTestCaseEnd(currentTestCaseName);
	}

	@Test
	public void testConvert() {
		generator.convert("2255.63");
		generator.convert("225563");
		generator.convert("2225563");
	}

}
