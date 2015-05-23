package com.fiona.phone2word.util;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.fiona.phone2word.TestCaseName;
import com.fiona.phone2word.TestSuite;

public class CartesianProductTest {
	@Rule
	public TestCaseName currentTestCaseName = new TestCaseName(this);
	
	private CartesianProduct product;
	
	@Before
	public void setUp() throws Exception {
		TestSuite.printTestCaseStart(currentTestCaseName);
		product = new CartesianProduct();
	}

	@After
	public void tearDown() throws Exception {
		TestSuite.printTestCaseEnd(currentTestCaseName);
	}

	@Test
	public void test() {
		assertEquals(Arrays.asList(Arrays.asList("2", "3", "4", "apple"), 
				Arrays.asList("2", "3", "4", "pear"), 
				Arrays.asList("2", "34", "apple"),
				Arrays.asList("2", "34", "pear")),
				product.process(Arrays.asList(Arrays.asList("2", "3", "4"), Arrays.asList("2", "34")),
						Arrays.asList("apple", "pear")));
	}

}
