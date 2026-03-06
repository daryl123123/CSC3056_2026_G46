package org.jfree.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RangeTest {

    private Range exampleRange;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        exampleRange = new Range(-1, 1);
    }

    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
                0, exampleRange.getCentralValue(), .000000001d);
    }

    // -------------------------------------------------------------------------
    // 3.2.1 contains(double value) - Range(-1.0, 1.0)
    // -------------------------------------------------------------------------

    @Test
    public void contains_MidRangeValue() {
        // TC1: ECP - value inside range
        assertTrue(exampleRange.contains(0.0));
    }

    @Test
    public void contains_ExactlyLowerBound() {
        // TC2: BVA - exactly on lower bound
        assertTrue(exampleRange.contains(-1.0));
    }

    @Test
    public void contains_ExactlyUpperBound() {
        // TC3: BVA - exactly on upper bound
        assertTrue(exampleRange.contains(1.0));
    }

    @Test
    public void contains_JustBelowLowerBound() {
        // TC4: BVA - just below lower bound
        assertFalse(exampleRange.contains(-1.1));
    }

    @Test
    public void contains_JustAboveUpperBound() {
        // TC5: BVA - just above upper bound
        assertFalse(exampleRange.contains(1.1));
    }

    // -------------------------------------------------------------------------
    // 3.2.2 constrain(double value) - Range(-1.0, 1.0)
    // -------------------------------------------------------------------------

    @Test
    public void constrain_InsideRange() {
        // TC1: ECP - value already inside range
        assertEquals(0.5, exampleRange.constrain(0.5), 0.000000001d);
    }

    @Test
    public void constrain_ExactlyLowerBound() {
        // TC2: BVA - exactly on lower bound
        assertEquals(-1.0, exampleRange.constrain(-1.0), 0.000000001d);
    }

    @Test
    public void constrain_ExactlyUpperBound() {
        // TC3: BVA - exactly on upper bound
        assertEquals(1.0, exampleRange.constrain(1.0), 0.000000001d);
    }

    @Test
    public void constrain_SlightlyBelowRange() {
        // TC4: BVA - slightly below range, clamped to lower bound
        assertEquals(-1.0, exampleRange.constrain(-1.1), 0.000000001d);
    }

    @Test
    public void constrain_SlightlyAboveRange() {
        // TC5: BVA - slightly above range, clamped to upper bound
        assertEquals(1.0, exampleRange.constrain(1.1), 0.000000001d);
    }

    @Test
    public void constrain_FarAboveRange() {
        // TC6: ECP - far above range, clamped to upper bound
        assertEquals(1.0, exampleRange.constrain(100.0), 0.000000001d);
    }

    // -------------------------------------------------------------------------
    // 3.2.3 getLowerBound()
    // -------------------------------------------------------------------------

    @Test
    public void getLowerBound_PositiveLower() {
        // TC1: ECP - positive lower bound
        Range r = new Range(5.0, 10.0);
        assertEquals(5.0, r.getLowerBound(), 0.000000001d);
    }

    @Test
    public void getLowerBound_NegativeLower() {
        // TC2: ECP - negative lower bound
        Range r = new Range(-5.0, 10.0);
        assertEquals(-5.0, r.getLowerBound(), 0.000000001d);
    }

    @Test
    public void getLowerBound_ZeroLower() {
        // TC3: BVA - zero as lower bound
        Range r = new Range(0.0, 10.0);
        assertEquals(0.0, r.getLowerBound(), 0.000000001d);
    }

    @Test
    public void getLowerBound_IdenticalBounds() {
        // TC4: Edge Case - point range
        Range r = new Range(5.0, 5.0);
        assertEquals(5.0, r.getLowerBound(), 0.000000001d);
    }

    @Test
    public void getLowerBound_LargeNegative() {
        // TC5: ECP - large negative lower bound
        Range r = new Range(-100.0, 0.0);
        assertEquals(-100.0, r.getLowerBound(), 0.000000001d);
    }

    // -------------------------------------------------------------------------
    // 3.2.4 getUpperBound()
    // -------------------------------------------------------------------------

    @Test
    public void getUpperBound_PositiveUpper() {
        // TC1: ECP - positive upper bound
        Range r = new Range(-10.0, 5.0);
        assertEquals(5.0, r.getUpperBound(), 0.000000001d);
    }

    @Test
    public void getUpperBound_NegativeUpper() {
        // TC2: ECP - negative upper bound
        Range r = new Range(-10.0, -2.0);
        assertEquals(-2.0, r.getUpperBound(), 0.000000001d);
    }

    @Test
    public void getUpperBound_ZeroUpper() {
        // TC3: BVA - zero as upper bound
        Range r = new Range(-5.0, 0.0);
        assertEquals(0.0, r.getUpperBound(), 0.000000001d);
    }

    @Test
    public void getUpperBound_IdenticalBounds() {
        // TC4: Edge Case - point range
        Range r = new Range(5.0, 5.0);
        assertEquals(5.0, r.getUpperBound(), 0.000000001d);
    }

    @Test
    public void getUpperBound_LargePositive() {
        // TC5: ECP - large positive upper bound
        Range r = new Range(0.0, 100.0);
        assertEquals(100.0, r.getUpperBound(), 0.000000001d);
    }

    // -------------------------------------------------------------------------
    // 3.2.5 getLength()
    // -------------------------------------------------------------------------

    @Test
    public void getLength_NegativeToPositive() {
        // TC1: ECP - span across origin
        Range r = new Range(-5.0, 5.0);
        assertEquals(10.0, r.getLength(), 0.000000001d);
    }

    @Test
    public void getLength_EntirelyNegative() {
        // TC2: ECP - entirely negative span
        Range r = new Range(-10.0, -2.0);
        assertEquals(8.0, r.getLength(), 0.000000001d);
    }

    @Test
    public void getLength_ZeroAtOrigin() {
        // TC3: BVA - zero length at origin
        Range r = new Range(0.0, 0.0);
        assertEquals(0.0, r.getLength(), 0.000000001d);
    }

    @Test
    public void getLength_ZeroAtPositivePoint() {
        // TC4: BVA - zero length at positive point
        Range r = new Range(1.0, 1.0);
        assertEquals(0.0, r.getLength(), 0.000000001d);
    }

    @Test
    public void getLength_StandardPositive() {
        // TC5: ECP - standard positive span
        Range r = new Range(2.0, 10.0);
        assertEquals(8.0, r.getLength(), 0.000000001d);
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
