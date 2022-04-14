package org.jfree.data;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

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
	
	  //constrain() Tests
	  @Test
	  public void testConstrainWithValueWithinRangeReturnsSpecifiedValue(){
	    Range testRange = new Range(1, 8);
	    double testValue = 5;
	    assertEquals(testValue, testRange.constrain(testValue));
	  }

	  @Test
	  public void testConstrainWithValueEqualToUpperBoundReturnsSpecifiedValue(){
	    Range testRange = new Range(1, 8);
	    double testValue = 8;
	    assertEquals(testValue, testRange.constrain(testValue));
	  }

	  @Test
	  public void testConstrainWithValueEqualToLowerBoundReturnsSpecifiedValue(){
	    Range testRange = new Range(1, 8);
	    double testValue = 1;
	    assertEquals(testValue, testRange.constrain(testValue));
	  }

	  @Test
	  public void testConstrainWithValueGreaterThanUpperBoundReturnsUpperBound(){
	    Range testRange = new Range(1, 8);
	    double testValue = 9;
	    assertEquals(testRange.getUpperBound(), testRange.constrain(testValue));
	  }

	  @Test
	  public void testConstrainWithValueLessThanLowerBoundReturnsLowerBound(){
	    Range testRange = new Range(1, 8);
	    double testValue = -5;
	    assertEquals(testRange.getLowerBound(), testRange.constrain(testValue));
	  }

	  //expandToInclude() Tests
	  @Test
	  public void testExpandToIncludeWithNullRangeReturnsNewRangeWithLowerAndUpperBoundOfValue(){
	    double testValue = 5;
	    Range expectedRange = new Range(testValue, testValue);
	    assertEquals(expectedRange, Range.expandToInclude(null, testValue));
	  }

	  @Test
	  public void testExpandToIncludeWithValueWithinRangeReturnsOriginalRange(){
	    Range testRange = new Range(1, 8);
	    double testValue = 5;
	    assertEquals(testRange, Range.expandToInclude(testRange, testValue));
	  }

	  @Test
	  public void testExpandToIncludeWithValueEqualToUpperBoundReturnsOriginalRange(){
	    Range testRange = new Range(1, 8);
	    double testValue = 8;
	    assertEquals(testRange, Range.expandToInclude(testRange, testValue));
	  }

	  @Test
	  public void testExpandsToIncludeWithValueEqualToLowerBoundReturnsOriginalRange(){
	    Range testRange = new Range(1, 8);
	    double testValue = 1;
	    assertEquals(testRange, Range.expandToInclude(testRange, testValue));
	  }

	  @Test
	  public void testExpandToIncludeWithValueGreaterThanUpperBoundReturnsRangeWithNewUpperBound(){
	    Range testRange = new Range(1, 8);
	    double testValue = 9;
	    Range expectedRange = new Range(1, testValue);
	    assertEquals(expectedRange, Range.expandToInclude(testRange, testValue));
	  }

	  @Test
	  public void testExpandToIncludeWithValueLessThanLowerBoundReturnsRangeWithNewLowerBound(){
	    Range testRange = new Range(1, 8);
	    double testValue = -5;
	    Range expectedRange = new Range(testValue, 8);
	    assertEquals(expectedRange, Range.expandToInclude(testRange, testValue));
	  }

	  //expand() Tests
	  @Test
	  public void testExpandWithNullRangeThrowsIllegalArgumentException(){
	    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Range.expand(null, 0, 0));
	    assertEquals("Null 'range' argument.", exception.getMessage());
	  }

	  @Test
	  public void testExpandWithLowerMarginAndUpperMarginOf0ReturnsOriginalRange(){
	    Range testRange = new Range(1, 8);
	    double lowerMargin = 0;
	    double upperMargin = 0;

	    Range resultRange = Range.expand(testRange, lowerMargin, upperMargin);
	    assertEquals(testRange, resultRange);
	  }

	  @Test
	  public void testExpandWithRangeOfLength0ReturnsUnchangedRangeRegardlessOfMarginValues(){
	    Range testRange = new Range(1,1);
	    double lowerMargin = 100;
	    double upperMargin = -0.65;

	    Range resultRange = Range.expand(testRange, lowerMargin, upperMargin);
	    assertEquals(0, testRange.getLength());
	    assertEquals(testRange, resultRange);
	    //Note: As both lowerMargin and upperMargin are expressed as percentages of the Range length (as per the javadocs),
	    //      a range with the same upper and lower bound will not change with the expand method
	  }

	  @Test
	  public void testExpandWithPositiveReturnsExpectedRange(){
	    Range testRange = new Range(2, 6);
	    double lowerMargin = 0.25;
	    double upperMargin = 0.5;

	    Range expectedRange = new Range(1,8);
	    Range resultRange = Range.expand(testRange, lowerMargin, upperMargin);

	    assertEquals(expectedRange, resultRange);
	  }

	  @Test
	  public void testExpandWithNegativeReturnsExpectedRange(){
	    Range testRange = new Range(2, 6);
	    double lowerMargin = -0.25;
	    double upperMargin = -0.5;

	    Range expectedRange = new Range(3,4);
	    Range resultRange = Range.expand(testRange, lowerMargin, upperMargin);

	    assertEquals(expectedRange, resultRange);
	  }

	  @Test
	  public void testExpandWithValuesGreaterThan1ReturnsExpectedRange(){
	    Range testRange = new Range(1, 2);
	    double lowerMargin = 1.5 ;
	    double upperMargin = 10;

	    Range expectedRange = new Range(-0.5,12);
	    Range resultRange = Range.expand(testRange, lowerMargin, upperMargin);

	    assertEquals(expectedRange, resultRange);
	  }

	  @Test
	  public void testExpandWithValuesOfMinus1ReturnsRangeWithUpperAndLowerBoundSwapped(){
	    Range testRange = new Range(2, 6);
	    double lowerMargin = -1;
	    double upperMargin = -1;

	    Range resultRange = Range.expand(testRange, lowerMargin, upperMargin);
	    Range expectedRange = new Range(6,2);

	    assertEquals(expectedRange, resultRange);
	  }

	  //equals() Tests
	  @Test
	  public void testEqualsWithNullReturnsFalse(){
	    Range comparisonRange = new Range(1, 8);

	    assertFalse(comparisonRange.equals(null));
	  }

	  @Test
	  public void testEqualsWithNonRangeObjectReturnsFalse(){
	    Range comparisonRange = new Range(1, 8);
	    String nonRangeObject = "This is not a Range Object";

	    assertFalse(comparisonRange.equals(nonRangeObject));
	  }

	  @Test
	  public void testEqualsWithTestRangeWithDifferentLowerBoundReturnsFalse(){
	    double comparisonLowerBound = 1;
	    double comparisonUpperBound = 8;
	    Range comparisonRange = new Range(comparisonLowerBound, comparisonUpperBound);
	    Range testRange = new Range(0, comparisonUpperBound);

	    assertFalse(comparisonRange.equals(testRange));
	  }

	  @Test
	  public void testEqualsWithTestRangeWithDifferentUpperBoundReturnsFalse(){
	    double comparisonLowerBound = 1;
	    double comparisonUpperBound = 8;
	    Range comparisonRange = new Range(comparisonLowerBound, comparisonUpperBound);
	    Range testRange = new Range(comparisonLowerBound, 9);

	    assertNotSame(testRange, comparisonRange);
	    assertFalse(comparisonRange.equals(testRange));
	  }

	  @Test
	  public void testEqualsComparingARangeWithItselfReturnsTrue(){
	    double comparisonLowerBound = 1;
	    double comparisonUpperBound = 8;
	    Range comparisonRange = new Range(comparisonLowerBound, comparisonUpperBound);

	    assertSame(comparisonRange, comparisonRange);
	    assertTrue(comparisonRange.equals(comparisonRange));
	  }

	  @Test
	  public void testEqualsComparingEquivalentRangeReturnsTrue(){
	    double comparisonLowerBound = 1;
	    double comparisonUpperBound = 8;
	    Range comparisonRange = new Range(comparisonLowerBound, comparisonUpperBound);
	    Range testRange = new Range(comparisonLowerBound, comparisonUpperBound);

	    assertTrue(comparisonRange.equals(testRange));
	  }

	  //hashCode() tests
	  @Test
	  public void testHashCodeTestingThatTwoDifferentRangeObjectsWithSameRangesReturnSameHashCodes(){
	    Range testRange = new Range(1, 8);
	    Range testRange2 = new Range(1, 8);

	    assertNotSame(testRange, testRange2);
	    assertEquals(testRange, testRange2);
	    assertEquals(testRange.hashCode(), testRange2.hashCode());
	  }

	  @Test
	  public void testHashCodeTestingThatTwoDifferentRangeObjectsWithDifferentRangesReturnDifferentHashCodes(){
	    Range testRange = new Range(1, 8);
	    Range testRange2 = new Range(2, 9);

	    assertNotSame(testRange, testRange2);
	    assertNotEquals(testRange, testRange2);
	    assertNotEquals(testRange.hashCode(), testRange2.hashCode());
	  }	
	
	//toString() Tests
	@Test
	public void testToStringReturnsStringAsExpected() {
		Range testRange = new Range(1, 8);
		String expected = "Range[1.0,8.0]";
		assertEquals(expected, testRange.toString());
	}	
}
