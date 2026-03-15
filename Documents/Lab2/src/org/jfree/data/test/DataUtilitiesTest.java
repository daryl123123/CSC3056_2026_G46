package org.jfree.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test suite for {@link DataUtilities}.
 * <p>
 * Covers the following methods using Equivalence Class Partitioning (ECP),
 * Boundary Value Analysis (BVA), and Robustness testing:
 * <ul>
 *   <li>{@code calculateColumnTotal(Values2D, int)}</li>
 *   <li>{@code calculateRowTotal(Values2D, int)}</li>
 *   <li>{@code createNumberArray(double[])}</li>
 *   <li>{@code createNumberArray2D(double[][])}</li>
 *   <li>{@code getCumulativePercentages(KeyedValues)}</li>
 * </ul>
 *
 * @author Group 46
 * @version 1.0
 * @see DataUtilities
 */
@SuppressWarnings("rawtypes")
public class DataUtilitiesTest {

    /**
     * One-time setup executed before any test in this class runs.
     *
     * @throws Exception if setup fails
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * Setup executed before each individual test method.
     *
     * @throws Exception if setup fails
     */
    @Before
    public void setUp() throws Exception {
    }

    // -------------------------------------------------------------------------
    // 3.1.1 calculateColumnTotal(Values2D data, int column)
    // -------------------------------------------------------------------------

    /**
     * TC1 (ECP) – Verifies that two positive values in column 0 are summed correctly.
     * Expected: 2.5 + 2.5 = 5.0
     */
    @Test
    public void calculateColumnTotal_TwoPositiveRows() {
        // TC1: ECP - sum of two positive values in column 0
        Values2D data = new Values2D() {
            public int getRowCount() {
                return 2;
            }

            public int getColumnCount() {
                return 1;
            }

            public Number getValue(int row, int column) {
                if (row == 0)
                    return 2.5;
                if (row == 1)
                    return 2.5;
                return null;
            }
        };
        assertEquals(5.0, DataUtilities.calculateColumnTotal(data, 0), 0.000000001d);
    }

    /**
     * TC2 (BVA) – Single-row table; column total should equal the single cell value.
     * Expected: 10.0
     */
    @Test
    public void calculateColumnTotal_SingleElement() {
        // TC2: BVA - single element lower bound
        Values2D data = new Values2D() {
            public int getRowCount() {
                return 1;
            }

            public int getColumnCount() {
                return 1;
            }

            public Number getValue(int row, int column) {
                return 10.0;
            }
        };
        assertEquals(10.0, DataUtilities.calculateColumnTotal(data, 0), 0.000000001d);
    }

    /**
     * TC3 (BVA) – Passing an invalid negative column index should return 0.0
     * because no valid rows are accumulated.
     */
    @Test
    public void calculateColumnTotal_NegativeColumnIndex() {
        // TC3: BVA - invalid negative column index
        Values2D data = new Values2D() {
            public int getRowCount() {
                return 1;
            }

            public int getColumnCount() {
                return 1;
            }

            public Number getValue(int row, int column) {
                return 5.0;
            }
        };
        assertEquals(0.0, DataUtilities.calculateColumnTotal(data, -1), 0.000000001d);
    }

    /**
     * TC4 (Robustness) – A {@code null} cell value should be treated as 0;
     * the non-null value 2.5 is the expected total.
     */
    @Test
    public void calculateColumnTotal_NullValue() {
        // TC4: Robustness - null value in column treated as 0
        Values2D data = new Values2D() {
            public int getRowCount() {
                return 2;
            }

            public int getColumnCount() {
                return 1;
            }

            public Number getValue(int row, int column) {
                if (row == 0)
                    return 2.5;
                return null;
            }
        };
        assertEquals(2.5, DataUtilities.calculateColumnTotal(data, 0), 0.000000001d);
    }

    /**
     * TC5 (Robustness) – Passing a {@code null} {@link Values2D} argument must
     * throw an {@link IllegalArgumentException}.
     */
    @Test
    public void calculateColumnTotal_NullData() {
        // TC5: Robustness - null data object throws IllegalArgumentException
        try {
            DataUtilities.calculateColumnTotal(null, 0);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    // -------------------------------------------------------------------------
    // 3.1.2 calculateRowTotal(Values2D data, int row)
    // -------------------------------------------------------------------------

    /**
     * TC1 (ECP) – Verifies that two positive column values in row 0 are summed correctly.
     * Expected: 5.0 + 10.0 = 15.0
     */
    @Test
    public void calculateRowTotal_TwoPositiveColumns() {
        // TC1: ECP - sum of two positive values in row 0
        Values2D data = new Values2D() {
            public int getRowCount() {
                return 1;
            }

            public int getColumnCount() {
                return 2;
            }

            public Number getValue(int row, int column) {
                if (column == 0)
                    return 5.0;
                if (column == 1)
                    return 10.0;
                return null;
            }
        };
        assertEquals(15.0, DataUtilities.calculateRowTotal(data, 0), 0.000000001d);
    }

    /**
     * TC2 (BVA) – Single-column table; row total should equal the single cell value.
     * Expected: 7.5
     */
    @Test
    public void calculateRowTotal_SingleElement() {
        // TC2: BVA - single element lower bound
        Values2D data = new Values2D() {
            public int getRowCount() {
                return 1;
            }

            public int getColumnCount() {
                return 1;
            }

            public Number getValue(int row, int column) {
                return 7.5;
            }
        };
        assertEquals(7.5, DataUtilities.calculateRowTotal(data, 0), 0.000000001d);
    }

    /**
     * TC3 (BVA) – An invalid negative row index should return 0.0
     * because no valid columns are accumulated.
     */
    @Test
    public void calculateRowTotal_NegativeRowIndex() {
        // TC3: BVA - invalid negative row index
        Values2D data = new Values2D() {
            public int getRowCount() {
                return 1;
            }

            public int getColumnCount() {
                return 2;
            }

            public Number getValue(int row, int column) {
                if (column == 0)
                    return 3.0;
                if (column == 1)
                    return 4.0;
                return null;
            }
        };
        assertEquals(0.0, DataUtilities.calculateRowTotal(data, -1), 0.000000001d);
    }

    /**
     * TC4 (BVA) – A row index equal to the row count (out-of-bounds) should
     * return 0.0 since no data exists at that index.
     */
    @Test
    public void calculateRowTotal_RowIndexExceedsSize() {
        // TC4: BVA - row index beyond data size
        Values2D data = new Values2D() {
            public int getRowCount() {
                return 1;
            }

            public int getColumnCount() {
                return 2;
            }

            public Number getValue(int row, int column) {
                if (column == 0)
                    return 3.0;
                if (column == 1)
                    return 4.0;
                return null;
            }
        };
        assertEquals(0.0, DataUtilities.calculateRowTotal(data, 1), 0.000000001d);
    }

    /**
     * TC5 (ECP) – Row containing a mix of positive and negative values;
     * the signed sum should be computed accurately.
     * Expected: -5.0 + 2.0 = -3.0
     */
    @Test
    public void calculateRowTotal_NegativeValues() {
        // TC5: ECP - row with mixed positive/negative values
        Values2D data = new Values2D() {
            public int getRowCount() {
                return 1;
            }

            public int getColumnCount() {
                return 2;
            }

            public Number getValue(int row, int column) {
                if (column == 0)
                    return -5.0;
                if (column == 1)
                    return 2.0;
                return null;
            }
        };
        assertEquals(-3.0, DataUtilities.calculateRowTotal(data, 0), 0.000000001d);
    }

    // -------------------------------------------------------------------------
    // 3.1.3 createNumberArray(double[] data)
    // -------------------------------------------------------------------------

    /**
     * TC1 (ECP) – Converts a standard multi-element {@code double[]} to a
     * {@code Number[]} and verifies each element's value.
     */
    @Test
    public void createNumberArray_MultiElement() {
        // TC1: ECP - standard multi-element array
        double[] input = { 1.1, 2.2, 3.3 };
        Number[] result = DataUtilities.createNumberArray(input);
        assertEquals(3, result.length);
        assertEquals(1.1, result[0].doubleValue(), 0.000000001d);
        assertEquals(2.2, result[1].doubleValue(), 0.000000001d);
        assertEquals(3.3, result[2].doubleValue(), 0.000000001d);
    }

    /**
     * TC2 (BVA) – Single-element array; resulting {@code Number[]} must have
     * length 1 and preserve the original value.
     */
    @Test
    public void createNumberArray_SingleElement() {
        // TC2: BVA - single element array
        double[] input = { 5.5 };
        Number[] result = DataUtilities.createNumberArray(input);
        assertEquals(1, result.length);
        assertEquals(5.5, result[0].doubleValue(), 0.000000001d);
    }

    /**
     * TC3 (BVA) – Empty array input; resulting {@code Number[]} must have length 0.
     */
    @Test
    public void createNumberArray_Empty() {
        // TC3: BVA - empty array
        double[] input = {};
        Number[] result = DataUtilities.createNumberArray(input);
        assertEquals(0, result.length);
    }

    /**
     * TC4 (Robustness) – A {@code null} input must throw an
     * {@link IllegalArgumentException}.
     */
    @Test
    public void createNumberArray_Null() {
        // TC4: Robustness - null input throws IllegalArgumentException
        try {
            DataUtilities.createNumberArray(null);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    // -------------------------------------------------------------------------
    // 3.1.4 createNumberArray2D(double[][] data)
    // -------------------------------------------------------------------------

    /**
     * TC1 (ECP) – Converts a standard 2×2 {@code double[][]} to a {@code Number[][]}
     * and verifies every element.
     */
    @Test
    public void createNumberArray2D_Standard2x2() {
        // TC1: ECP - standard 2x2 array
        double[][] input = { { 1.0, 2.0 }, { 3.0, 4.0 } };
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertEquals(2, result.length);
        assertEquals(1.0, result[0][0].doubleValue(), 0.000000001d);
        assertEquals(2.0, result[0][1].doubleValue(), 0.000000001d);
        assertEquals(3.0, result[1][0].doubleValue(), 0.000000001d);
        assertEquals(4.0, result[1][1].doubleValue(), 0.000000001d);
    }

    /**
     * TC2 (BVA) – 1×1 array; the single element must be preserved.
     */
    @Test
    public void createNumberArray2D_SingleElement() {
        // TC2: BVA - 1x1 array
        double[][] input = { { 9.9 } };
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertEquals(1, result.length);
        assertEquals(9.9, result[0][0].doubleValue(), 0.000000001d);
    }

    /**
     * TC3 (BVA) – Outer array has one row, but the inner array is empty;
     * the resulting inner {@code Number[]} must have length 0.
     */
    @Test
    public void createNumberArray2D_EmptyInner() {
        // TC3: BVA - empty inner array
        double[][] input = { {} };
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertEquals(1, result.length);
        assertEquals(0, result[0].length);
    }

    /**
     * TC4 (Robustness) – A {@code null} input must throw an
     * {@link IllegalArgumentException}.
     */
    @Test
    public void createNumberArray2D_Null() {
        // TC4: Robustness - null input throws IllegalArgumentException
        try {
            DataUtilities.createNumberArray2D(null);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    // -------------------------------------------------------------------------
    // 3.1.5 getCumulativePercentages(KeyedValues data)
    // -------------------------------------------------------------------------

    /**
     * TC1 (ECP) – Three entries with values 5, 5, 10 (total 20);
     * cumulative percentages must be 0.25, 0.50, and 1.0 respectively.
     */
    @Test
    public void getCumulativePercentages_Standard() {
        // TC1: ECP - 0:5, 1:5, 2:10 -> 0:0.25, 1:0.5, 2:1.0
        KeyedValues data = new KeyedValues() {
            private final Integer[] keys = { 0, 1, 2 };
            private final double[] values = { 5.0, 5.0, 10.0 };

            public int getItemCount() {
                return 3;
            }

            public Number getValue(int index) {
                return values[index];
            }

            public Comparable getKey(int index) {
                return keys[index];
            }

            public int getIndex(Comparable key) {
                return (Integer) key;
            }

            public List getKeys() {
                return Arrays.asList(keys);
            }

            public Number getValue(Comparable key) {
                return values[(Integer) key];
            }
        };
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.25, result.getValue(0).doubleValue(), 0.000000001d);
        assertEquals(0.50, result.getValue(1).doubleValue(), 0.000000001d);
        assertEquals(1.0, result.getValue(2).doubleValue(), 0.000000001d);
    }

    /**
     * TC2 (BVA) – A dataset with a single entry must yield a cumulative
     * percentage of 1.0 (100%).
     */
    @Test
    public void getCumulativePercentages_SingleEntry() {
        // TC2: BVA - single entry should be 100%
        KeyedValues data = new KeyedValues() {
            public int getItemCount() {
                return 1;
            }

            public Number getValue(int index) {
                return 10.0;
            }

            public Comparable getKey(int index) {
                return 0;
            }

            public int getIndex(Comparable key) {
                return 0;
            }

            public List getKeys() {
                return Arrays.asList(new Integer[] { 0 });
            }

            public Number getValue(Comparable key) {
                return 10.0;
            }
        };
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertEquals(1.0, result.getValue(0).doubleValue(), 0.000000001d);
    }

    /**
     * TC3 (BVA) – An empty dataset must return a {@link KeyedValues} result
     * with an item count of 0.
     */
    @Test
    public void getCumulativePercentages_Empty() {
        // TC3: BVA - empty dataset
        KeyedValues data = new KeyedValues() {
            public int getItemCount() {
                return 0;
            }

            public Number getValue(int index) {
                return null;
            }

            public Comparable getKey(int index) {
                return null;
            }

            public int getIndex(Comparable key) {
                return -1;
            }

            public List getKeys() {
                return Arrays.asList();
            }

            public Number getValue(Comparable key) {
                return null;
            }
        };
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertEquals(0, result.getItemCount());
    }

    /**
     * TC4 (Robustness) – A {@code null} entry in the dataset should be treated
     * as 0.0; entries 0 and 1 each contribute 5 out of a total of 10,
     * giving cumulative percentages of 0.5, 0.5, and 1.0.
     */
    @Test
    public void getCumulativePercentages_NullValue() {
        // TC4: Robustness - null value treated as 0.0
        // 0:5, 1:null, 2:5 -> 0:0.5, 1:0.5, 2:1.0
        KeyedValues data = new KeyedValues() {
            private final Integer[] keys = { 0, 1, 2 };
            private final Number[] values = { 5.0, null, 5.0 };

            public int getItemCount() {
                return 3;
            }

            public Number getValue(int index) {
                return values[index];
            }

            public Comparable getKey(int index) {
                return keys[index];
            }

            public int getIndex(Comparable key) {
                return (Integer) key;
            }

            public List getKeys() {
                return Arrays.asList(keys);
            }

            public Number getValue(Comparable key) {
                return values[(Integer) key];
            }
        };
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.5, result.getValue(0).doubleValue(), 0.000000001d);
        assertEquals(0.5, result.getValue(1).doubleValue(), 0.000000001d);
        assertEquals(1.0, result.getValue(2).doubleValue(), 0.000000001d);
    }

    /**
     * Teardown executed after each individual test method.
     *
     * @throws Exception if teardown fails
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * One-time teardown executed after all tests in this class have run.
     *
     * @throws Exception if teardown fails
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
