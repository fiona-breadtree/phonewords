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
		generator = new PhoneWordConverter("config/testwords");
	}

	@After
	public void tearDown() throws Exception {
		TestSuite.printTestCaseEnd(currentTestCaseName);
	}

	@Test
	public void testConvert() {
		assertEquals(Arrays.asList("BALL-ME","CALL-ME"), generator.convert("2255.63"));
		assertEquals(Arrays.asList("BALL-ME","CALL-ME"), generator.convert("225563"));
		assertEquals(Arrays.asList("2-BALL-ME","2-CALL-ME"), generator.convert("2225563"));
	}

}
