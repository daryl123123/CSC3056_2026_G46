package org.jfree.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings("rawtypes")
public class DataUtilitiesTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    // -------------------------------------------------------------------------
    // 3.1.1 calculateColumnTotal(Values2D data, int column)
    // -------------------------------------------------------------------------

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

    @Test
    public void createNumberArray_SingleElement() {
        // TC2: BVA - single element array
        double[] input = { 5.5 };
        Number[] result = DataUtilities.createNumberArray(input);
        assertEquals(1, result.length);
        assertEquals(5.5, result[0].doubleValue(), 0.000000001d);
    }

    @Test
    public void createNumberArray_Empty() {
        // TC3: BVA - empty array
        double[] input = {};
        Number[] result = DataUtilities.createNumberArray(input);
        assertEquals(0, result.length);
    }

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

    @Test
    public void createNumberArray2D_SingleElement() {
        // TC2: BVA - 1x1 array
        double[][] input = { { 9.9 } };
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertEquals(1, result.length);
        assertEquals(9.9, result[0][0].doubleValue(), 0.000000001d);
    }

    @Test
    public void createNumberArray2D_EmptyInner() {
        // TC3: BVA - empty inner array
        double[][] input = { {} };
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertEquals(1, result.length);
        assertEquals(0, result[0].length);
    }

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

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
