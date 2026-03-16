package org.jfree.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;



/**
 * Unit test suite for {@link Range}.
 * <p>
 * Tests the following methods using ECP, BVA, and edge-case analysis:
 * {@code contains}, {@code constrain}, {@code getLowerBound},
 * {@code getUpperBound}, and {@code getLength}.
 *
 * @author Group 46
 * @version 1.0
 */
public class RangeTest {

    private Range exampleRange;

    /** Initialises a {@code Range(-1, 1)} used by most tests. */
    @Before
    public void setUp() {
        exampleRange = new Range(-1, 1);
    }

    // -------------------------------------------------------------------------
    // 3.2.1 contains(double value) - Range(-1.0, 1.0)
    // -------------------------------------------------------------------------

    /** TC1 (ECP) - Value 0.0 is inside Range(-1,1); expects {@code true}. */
    @Test
    public void test_TC1_contains_MidRange() {
        // ECP - value inside range
        assertTrue(exampleRange.contains(0.0));
    }

    /** TC2 (BVA) - Value -1.0 is exactly on the lower bound; expects {@code true}. */
    @Test
    public void test_TC2_contains_ExactlyLowerBound() {
        // BVA - exactly on lower bound
        assertTrue(exampleRange.contains(-1.0));
    }

    /** TC3 (BVA) - Value 1.0 is exactly on the upper bound; expects {@code true}. */
    @Test
    public void test_TC3_contains_ExactlyUpperBound() {
        // BVA - exactly on upper bound
        assertTrue(exampleRange.contains(1.0));
    }

    /** TC4 (BVA) - Value -1.1 is just below the lower bound; expects {@code false}. */
    @Test
    public void test_TC4_contains_JustBelowLowerBound() {
        // BVA - just below lower bound
        assertFalse(exampleRange.contains(-1.1));
    }

    /** TC5 (BVA) - Value 1.1 is just above the upper bound; expects {@code false}. */
    @Test
    public void test_TC5_contains_JustAboveUpperBound() {
        // BVA - just above upper bound
        assertFalse(exampleRange.contains(1.1));
    }

    // -------------------------------------------------------------------------
    // 3.2.2 constrain(double value) - Range(-1.0, 1.0)
    // -------------------------------------------------------------------------

    /** TC1 (ECP) - Value 0.5 is inside range; constrain should return 0.5 unchanged. */
    @Test
    public void test_TC1_constrain_InsideRange() {
        // ECP - value already inside range
        assertEquals(0.5, exampleRange.constrain(0.5), 0.000000001d);
    }

    /** TC2 (BVA) - Value -1.0 is exactly on the lower bound; expected -1.0. */
    @Test
    public void test_TC2_constrain_ExactlyLowerBound() {
        // BVA - exactly on lower bound
        assertEquals(-1.0, exampleRange.constrain(-1.0), 0.000000001d);
    }

    /** TC3 (BVA) - Value 1.0 is exactly on the upper bound; expected 1.0. */
    @Test
    public void test_TC3_constrain_ExactlyUpperBound() {
        // BVA - exactly on upper bound
        assertEquals(1.0, exampleRange.constrain(1.0), 0.000000001d);
    }

    /** TC4 (BVA) - Value -1.1 is slightly below range; clamped to lower bound -1.0. */
    @Test
    public void test_TC4_constrain_SlightlyBelowRange() {
        // BVA - slightly below range, clamped to lower bound
        assertEquals(-1.0, exampleRange.constrain(-1.1), 0.000000001d);
    }

    /** TC5 (BVA) - Value 1.1 is slightly above range; clamped to upper bound 1.0. */
    @Test
    public void test_TC5_constrain_SlightlyAboveRange() {
        // BVA - slightly above range, clamped to upper bound
        assertEquals(1.0, exampleRange.constrain(1.1), 0.000000001d);
    }

    /** TC6 (ECP) - Value 100.0 is far above range; clamped to upper bound 1.0. */
    @Test
    public void test_TC6_constrain_FarAboveRange() {
        // ECP - far above range, clamped to upper bound
        assertEquals(1.0, exampleRange.constrain(100.0), 0.000000001d);
    }

    // -------------------------------------------------------------------------
    // 3.2.3 getLowerBound()
    // -------------------------------------------------------------------------

    /** TC1 (ECP) - Range(5,10); lower bound should be 5.0. */
    @Test
    public void test_TC1_getLowerBound_PositiveLower() {
        // ECP - positive lower bound
        Range r = new Range(5.0, 10.0);
        assertEquals(5.0, r.getLowerBound(), 0.000000001d);
    }

    /** TC2 (ECP) - Range(-5,10); lower bound should be -5.0. */
    @Test
    public void test_TC2_getLowerBound_NegativeLower() {
        // ECP - negative lower bound
        Range r = new Range(-5.0, 10.0);
        assertEquals(-5.0, r.getLowerBound(), 0.000000001d);
    }

    /** TC3 (BVA) - Range(0,10); lower bound should be 0.0. */
    @Test
    public void test_TC3_getLowerBound_ZeroLower() {
        // BVA - zero as lower bound
        Range r = new Range(0.0, 10.0);
        assertEquals(0.0, r.getLowerBound(), 0.000000001d);
    }

    /** TC4 (Edge) - Point range Range(5,5); lower bound should equal 5.0. */
    @Test
    public void test_TC4_getLowerBound_IdenticalBounds() {
        // Edge Case - point range
        Range r = new Range(5.0, 5.0);
        assertEquals(5.0, r.getLowerBound(), 0.000000001d);
    }

    /** TC5 (ECP) - Range(-100,0); lower bound should be -100.0. */
    @Test
    public void test_TC5_getLowerBound_LargeNegative() {
        // ECP - large negative lower bound
        Range r = new Range(-100.0, 0.0);
        assertEquals(-100.0, r.getLowerBound(), 0.000000001d);
    }

    // -------------------------------------------------------------------------
    // 3.2.4 getUpperBound()
    // -------------------------------------------------------------------------

    /** TC1 (ECP) - Range(-10,5); upper bound should be 5.0. */
    @Test
    public void test_TC1_getUpperBound_PositiveUpper() {
        // ECP - positive upper bound
        Range r = new Range(-10.0, 5.0);
        assertEquals(5.0, r.getUpperBound(), 0.000000001d);
    }

    /** TC2 (ECP) - Range(-10,-2); upper bound should be -2.0. */
    @Test
    public void test_TC2_getUpperBound_NegativeUpper() {
        // ECP - negative upper bound
        Range r = new Range(-10.0, -2.0);
        assertEquals(-2.0, r.getUpperBound(), 0.000000001d);
    }

    /** TC3 (BVA) - Range(-5,0); upper bound should be 0.0. */
    @Test
    public void test_TC3_getUpperBound_ZeroUpper() {
        // BVA - zero as upper bound
        Range r = new Range(-5.0, 0.0);
        assertEquals(0.0, r.getUpperBound(), 0.000000001d);
    }

    /** TC4 (Edge) - Point range Range(5,5); upper bound should equal 5.0. */
    @Test
    public void test_TC4_getUpperBound_IdenticalBounds() {
        // Edge Case - point range
        Range r = new Range(5.0, 5.0);
        assertEquals(5.0, r.getUpperBound(), 0.000000001d);
    }

    /** TC5 (ECP) - Range(0,100); upper bound should be 100.0. */
    @Test
    public void test_TC5_getUpperBound_LargePositive() {
        // ECP - large positive upper bound
        Range r = new Range(0.0, 100.0);
        assertEquals(100.0, r.getUpperBound(), 0.000000001d);
    }

    // -------------------------------------------------------------------------
    // 3.2.5 getLength()
    // -------------------------------------------------------------------------

    /** TC1 (ECP) - Range(-5,5) spans origin; length should be 10.0. */
    @Test
    public void test_TC1_getLength_NegativeToPositive() {
        // ECP - span across origin
        Range r = new Range(-5.0, 5.0);
        assertEquals(10.0, r.getLength(), 0.000000001d);
    }

    /** TC2 (ECP) - Range(-10,-2) entirely negative; length should be 8.0. */
    @Test
    public void test_TC2_getLength_EntirelyNegative() {
        // ECP - entirely negative span
        Range r = new Range(-10.0, -2.0);
        assertEquals(8.0, r.getLength(), 0.000000001d);
    }

    /** TC3 (BVA) - Point range Range(0,0); length should be 0.0. */
    @Test
    public void test_TC3_getLength_ZeroAtOrigin() {
        // BVA - zero length at origin
        Range r = new Range(0.0, 0.0);
        assertEquals(0.0, r.getLength(), 0.000000001d);
    }

    /** TC4 (BVA) - Point range Range(1,1); length should be 0.0. */
    @Test
    public void test_TC4_getLength_ZeroAtPositivePoint() {
        // BVA - zero length at positive point
        Range r = new Range(1.0, 1.0);
        assertEquals(0.0, r.getLength(), 0.000000001d);
    }

    /** TC5 (ECP) - Range(2,10); length should be 8.0. */
    @Test
    public void test_TC5_getLength_StandardPositive() {
        // ECP - standard positive span
        Range r = new Range(2.0, 10.0);
        assertEquals(8.0, r.getLength(), 0.000000001d);
    }
}
