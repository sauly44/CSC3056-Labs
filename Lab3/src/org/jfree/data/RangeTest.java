package org.jfree.data;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class RangeTest extends TestCase {
	
	private Range rangeObjectUnderTest;

	@Before
	public void setUp() throws Exception {
		rangeObjectUnderTest = new Range(-1, 1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCentralvalueShouldZero() {
		assertEquals("The central vale od -1 and 1 should be 0", 0, rangeObjectUnderTest.getCentralValue(), 0.000000001d);
	}
	
	//Contains
	@Test
	public void testContainsBelowLowerBounds() {
		Range r1 = new Range (-2,6);
		assertFalse("Range -2:6 should not contain -3, it is below the lower bounds",
				r1.contains(-3));
	}
	@Test
	public void testContainsAboveUpperBounds() {
		Range r1 = new Range (-2,6);
		assertFalse("Range -2:6 should not contain 7, it is above upper bounds", r1.contains(7));
	}
	@Test
	public void testContainsWithinBounds() {
		Range r1 = new Range (-2,6);
		assertTrue("Range -2:6 should contain 0", r1.contains(0));
	}
	@Test
	public void testContainsLowerBounds() {
		Range r1 = new Range (-2,6);
		assertTrue("Range -2:6 should contain -2 as it is the lower bounds",
				r1.contains(-2));
	}
	@Test
	public void testContainsUpperBounds() {
		Range r1 = new Range (-2,6);
		assertTrue("Range -2:6 should contain 6 as it is the upper bounds",
				r1.contains(6));
	}
	
	//Intersects
	@Test
	public void testIntersectsWhenOverlap() {
		Range r1 = new Range (-2,6);
		assertTrue("the ranges -2:6 and 5:8 should overlap.",r1.intersects(5, 8));
	}
	@Test
	public void testIntersectsWhenOverlapOnBounds() {
		Range r1 = new Range (-2,6);
		assertTrue("the ranges -2:6 and 6:8 should overlap.",r1.intersects(6, 8));
	}
	@Test
	public void testIntersectsWhenNoOverlap() {
		Range r1 = new Range (-2,6);
		assertFalse("the ranges -2:6 and 7:8 shouldnt overlap.",r1.intersects(7, 8));
	}
	
	//GetCentralValue
	@Test
	public void testGetCentralValueCentralNo() {
		Range r1 = new Range (-2,6);
		double centralValue = r1.getCentralValue();
		assertEquals("The central value of -2 and 6 should be 2"
				, 2, centralValue, 0.000000001d);
	}
	@Test
	public void testGetCentralValueMedianNo() {
		Range r1 = new Range (-2,7);
		double centralValue = r1.getCentralValue();
		assertEquals("The central value of -2 and 7 should be 2.5"
				, 2.5, centralValue, 0.000000001d);
	}
	@Test
	public void testGetCentralValueSameUpperAndLower() {
		Range r1 = new Range (2,2);
		double centralValue = r1.getCentralValue();
		assertEquals("The central value of 2 and 2 should be 2"
				, 2, centralValue, 0.000000001d);
	}
	@Test
	public void testGetCentralValueUpperAndLowerNegative() {
		Range r1 = new Range (-6,-2);
		double centralValue = r1.getCentralValue();
		assertEquals("The central value of -6 and -2 should be -4"
				, -4, centralValue, 0.000000001d);
	}
	@Test
	public void testGetCentralValueUpperAndLowerSameNegative() {
		Range r1 = new Range (-2,-2);
		double centralValue = r1.getCentralValue();
		assertEquals("The central value of -2 and -2 should be -2"
				, -2, centralValue, 0.000000001d);
	}
	
	//GetLength
	@Test
	public void testGetLengthPositive() {
		Range r1 = new Range(4,8);
		double length = r1.getLength();
		assertEquals("Length of 4 to 8 should be 5",
				5, length, 0.000000001d);
	}
	@Test
	public void testGetLengthPositiveSameUpperLower() {
		Range r1 = new Range(4,4);
		double length = r1.getLength();
		assertEquals("Length of 4 to 4 should be 1",
				1, length, 0.000000001d);
	}
	@Test
	public void testGetLengthNegative() {
		Range r1 = new Range(-12,-10);
		double length = r1.getLength();
		assertEquals("Length of -12 to -10 should be 3",
				3, length, 0.000000001d);
	}
	@Test
	public void testGetLengthNegativeSameUpperLower() {
		Range r1 = new Range(-12,-12);
		double length = r1.getLength();
		assertEquals("Length of -12 to -12 should be 1",
				1, length, 0.000000001d);
	}
	@Test
	public void testGetLengthMixed() {
		Range r1 = new Range(-2,2);
		double length = r1.getLength();
		assertEquals("Length of -2 to 2 should be 5",
				5, length, 0.000000001d);
	}
	
	//GetUpperBound
	@Test
	public void testGetUpperBoundPositive() {
		Range r1 = new Range(-2,2);
		double upperbound = r1.getUpperBound();
		assertEquals("Upper bound of -2 to 2 should be 2",
				2, upperbound, 0.000000001d);
	}
		@Test
		public void testGetUpperBoundNegative() {
			Range r1 = new Range(-4,-2);
			double upperbound = r1.getUpperBound();
			assertEquals("Upper bound of -4 to -2 should be -2",
					-2, upperbound, 0.000000001d);
	}
		@Test
		public void testGetUpperBoundNeutral() {
			Range r1 = new Range(-2,0);
			double upperbound = r1.getUpperBound();
			assertEquals("Upper bound of -2 to 0 should be 0",
					0, upperbound, 0.000000001d);
	}	
	
}
