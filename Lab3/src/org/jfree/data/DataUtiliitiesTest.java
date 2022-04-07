package org.jfree.data;

import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.jfree.data.DataUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class DataUtiliitiesTest extends TestCase {

	private Values2D values2D;
	
	@Before
	protected void setUp() throws Exception {
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values2D = testValues;
		testValues.addValue(1, 0, 0);
		testValues.addValue(4, 1, 0);
	}

	@After
	protected void tearDown() throws Exception {
		values2D = null;
	}

	@Test
	public void testValidDataAndColumnColumnTotal() {
		assertEquals("Wrong sum returned. It should be 5.0", 5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.000000001d);
	}

}
