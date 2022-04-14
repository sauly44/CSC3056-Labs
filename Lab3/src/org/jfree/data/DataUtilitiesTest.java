package org.jfree.data;

import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.jfree.data.DataUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

import java.security.InvalidParameterException;


public class DataUtilitiesTest extends TestCase {

  private Values2D values2D;

  @Before
  public void setUp() {
    DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
    values2D = testValues;
    testValues.addValue(1, 0, 0);
    testValues.addValue(4, 1, 0);
  }

  @After
  public void tearDown() {
    values2D = null;
  }

  @Test
  public void testValidDataAndColumnColumnTotal() {
    assertEquals(5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
  }

  @Test
  public void testCalculateColumnTotalWhereTotalShouldBeZero(){
    DefaultKeyedValues2D testValues2D = new DefaultKeyedValues2D();
    testValues2D.addValue(1, 0, 0);
    testValues2D.addValue(4, 1, 0);
    testValues2D.addValue(-2, 3, 0);
    testValues2D.addValue(-3, 4, 0);

    assertEquals(0.0, DataUtilities.calculateColumnTotal(testValues2D, 0));
  }

  @Test
  public void testCalculateColumnTotalShouldIgnoreNonRequestedColumns(){
    DefaultKeyedValues2D testValues2D = new DefaultKeyedValues2D();
    testValues2D.addValue(1, 0, 0);
    testValues2D.addValue(4, 1, 1);
    testValues2D.addValue(-2, 3, 1);
    testValues2D.addValue(-3, 4, 0);

    assertEquals(2.0, DataUtilities.calculateColumnTotal(testValues2D, 1));
  }

  @Test
  public void testCalculateColumnTotalWithNullDataShouldReturn0(){
	  try {
		  DataUtilities.calculateColumnTotal(null, 0);
		  fail("No exception was thrown. InvalidParameterException was expected.");
	  } catch (Exception e) {
		  assertTrue("Correct exception type thrown", e.getClass().equals(InvalidParameterException.class)); 
	  }
  }

//calculateRowTotal Tests
  @Test
  public void testCalculateRowWithValidDataShouldReturnCorrectTotal() {
    DefaultKeyedValues2D testValues2D = new DefaultKeyedValues2D();
    testValues2D.addValue(1, 0, 0);
    testValues2D.addValue(4, 0, 1);

    assertEquals(5.0, DataUtilities.calculateRowTotal(testValues2D, 0));  }

  @Test
  public void testCalculateRowTotalWhereTotalShouldBeZero(){
    DefaultKeyedValues2D testValues2D = new DefaultKeyedValues2D();
    testValues2D.addValue(1, 0, 0);
    testValues2D.addValue(4, 0, 1);
    testValues2D.addValue(-2, 0, 2);
    testValues2D.addValue(-3, 0, 3);

    assertEquals(0.0, DataUtilities.calculateRowTotal(testValues2D, 0));
  }

  @Test
  public void testCalculateRowTotalShouldIgnoreNonRequestedRows(){
    DefaultKeyedValues2D testValues2D = new DefaultKeyedValues2D();
    testValues2D.addValue(1, 0, 0);
    testValues2D.addValue(4, 1, 1);
    testValues2D.addValue(-2, 1, 2);
    testValues2D.addValue(-3, 0, 3);

    assertEquals(2.0, DataUtilities.calculateRowTotal(testValues2D, 1));
  }

  @Test
  public void testCalculateRowTotalWithNullDataShouldReturnThrowInvalidParameterException(){
	  try {
		  DataUtilities.calculateRowTotal(null, 0);
		  fail("No exception was thrown. InvalidParameterException was expected.");
	  } catch (Exception e) {
		  assertTrue("Correct exception type thrown", e.getClass().equals(InvalidParameterException.class)); 
	  }
  }

  //createNumberArray Tests
  @Test
  public void testCreateNumberArrayWithAValidPopulatedArrayShouldReturnTheCorrectArray(){
    double[] testData = {1.1, 2.2, 3.3};
    Number[] expectedArray = {1.1, 2.2, 3.3};
    Number[] resultArray = DataUtilities.createNumberArray(testData);

    assertEquals(expectedArray.length, resultArray.length);
    for(int i = 0; i < resultArray.length; i++){
      assertEquals(expectedArray[i], resultArray[i]);
    }
  }

  @Test
  public void testCreateNumberArrayWithAValidUnpopulatedArrayShouldReturnAnEmptyNonNullArray(){
    double[] testData = {};
    Number[] resultArray = DataUtilities.createNumberArray(testData);

    assertNotNull(resultArray);
    assertEquals(0, resultArray.length);
  }

  @Test
  public void testCreateNumberArrayWithNullArrayShouldReturnThrowInvalidParameterException(){
    try {
		  DataUtilities.createNumberArray(null);
		  fail("No exception was thrown. InvalidParameterException was expected.");
	  } catch (Exception e) {
		  assertTrue("Correct exception type thrown", e.getClass().equals(InvalidParameterException.class)); 
	  }
  }

  //createNumberArray2D Tests
  @Test
  public void testCreateNumberArray2DWithAValidPopulatedArraysShouldReturnTheCorrectArrays(){
    double[][] testData = {{1.1}, {2.1, 2.2}, {3.1, 3.2, 3.3}, {4.1, 4.2, 4.3, 4.4}};
    Number[][] expectedArrays = {{1.1}, {2.1, 2.2}, {3.1, 3.2, 3.3}, {4.1, 4.2, 4.3, 4.4}};
    Number[][] resultArrays = DataUtilities.createNumberArray2D(testData);

    //checking the length of the outer array is correct
    assertEquals(expectedArrays.length, resultArrays.length);
    for (int j = 0; j < resultArrays.length; j++){
      //checking the length of the inner arrays
      assertEquals(expectedArrays[j].length, resultArrays[j].length);
      for (int i = 0; i < resultArrays[j].length; i++){
        //checking each element of the array is as expected
        assertEquals(expectedArrays[j][i], resultArrays[j][i]);
      }
    }
  }

  @Test
  public void testCreateNumberArray2DWithAValidUnpopulatedArrayShouldReturnTheCorrectEmptyNonNullArrays(){
    double[][] testData = {};
    Number[][] resultArray = DataUtilities.createNumberArray2D(testData);

    assertNotNull(resultArray);
    assertEquals(0, resultArray.length);
  }

  @Test
  public void testCreateNumberArray2DWithNullArrayShouldReturnThrowInvalidParameterException(){
	  try {
		  DataUtilities.createNumberArray2D(null);
		  fail("No exception was thrown. InvalidParameterException was expected.");
	  } catch (Exception e) {
		  assertTrue("Correct exception type thrown", e.getClass().equals(InvalidParameterException.class)); 
	  }
  }

  //getCumulativePercentages Tests
  @Test
  public void testGetCumulativePercentagesWithValidKeyedValuesShouldReturnCorrectKeyedValue(){
    //example values taken from javadocs
    DefaultKeyedValues testValues = new DefaultKeyedValues();
    testValues.addValue(0, (Number)5);
    testValues.addValue(1, (Number)9);
    testValues.addValue(2, (Number)2);

    DefaultKeyedValues expectedCumulativePercentages = new DefaultKeyedValues();
    expectedCumulativePercentages.addValue(0, (Number)0.3125);
    expectedCumulativePercentages.addValue(1, (Number)0.875);
    expectedCumulativePercentages.addValue(2, (Number)1.0);

    KeyedValues result = DataUtilities.getCumulativePercentages(testValues);

    assertEquals(expectedCumulativePercentages.getItemCount(), result.getItemCount());
    assertEquals(expectedCumulativePercentages.getKeys(), result.getKeys());
    for (int i = 0; i < result.getItemCount(); i ++){
      assertEquals(expectedCumulativePercentages.getIndex(i), result.getIndex(i));
      assertEquals(expectedCumulativePercentages.getValue(i), result.getValue(i));
    }
  }

  @Test
  public void testGetCumulativePercentagesShouldReturnCorrectKeyedValue(){
    DefaultKeyedValues testValues = new DefaultKeyedValues();
    testValues.addValue(0, (Number)5);

    DefaultKeyedValues expectedCumulativePercentages = new DefaultKeyedValues();
    expectedCumulativePercentages.addValue(0, (Number)1.0);

    KeyedValues result = DataUtilities.getCumulativePercentages(testValues);

    assertEquals(expectedCumulativePercentages.getItemCount(), result.getItemCount());
    assertEquals(expectedCumulativePercentages.getKeys(), result.getKeys());
    for (int i = 0; i < result.getItemCount(); i ++){
      assertEquals(expectedCumulativePercentages.getIndex(i), result.getIndex(i));
      assertEquals(expectedCumulativePercentages.getValue(i), result.getValue(i));
    }
  }

  @Test
  public void testGetCumulativePercentagesWithEmptyKeyedValueReturnsEmptyKeyedValue(){
    DefaultKeyedValues testValues = new DefaultKeyedValues();
    assertEquals(0, testValues.getItemCount());

    KeyedValues result = DataUtilities.getCumulativePercentages(testValues);
    assertEquals(0, result.getItemCount());
  }

  @Test
  public void testGetCumulativePercentagesWithNullDataThrowsInvalidParameterException(){
	  try {
		  DataUtilities.getCumulativePercentages(null);
		  fail("No exception was thrown. InvalidParameterException was expected.");
	  } catch (Exception e) {
		  assertTrue("Correct exception type thrown", e.getClass().equals(InvalidParameterException.class)); 
	  }
  }
}
