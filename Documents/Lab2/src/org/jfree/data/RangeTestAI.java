package org.jfree.data;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RangeTestAI {

    /*
     * Method Tested: contains(double value)
     * Black-box method: Equivalence Partitioning + Boundary Value Analysis
     *
     * Partitions:
     * 1. Value inside range
     * 2. Value equal to lower bound
     * 3. Value equal to upper bound
     * 4. Value below lower bound
     * 5. Value above upper bound
     */

    @Test
    public void testContainsInsideRange() {
        Range r = new Range(5.0, 10.0);
        assertTrue(r.contains(7.0));
    }

    @Test
    public void testContainsAtLowerBoundary() {
        Range r = new Range(5.0, 10.0);
        assertTrue(r.contains(5.0));
    }

    @Test
    public void testContainsAtUpperBoundary() {
        Range r = new Range(5.0, 10.0);
        assertTrue(r.contains(10.0));
    }

    @Test
    public void testContainsBelowRange() {
        Range r = new Range(5.0, 10.0);
        assertFalse(r.contains(4.9));
    }

    @Test
    public void testContainsAboveRange() {
        Range r = new Range(5.0, 10.0);
        assertFalse(r.contains(10.1));
    }


    /*
     * Method Tested: intersects(double lower, double upper)
     * Black-box method: Equivalence Partitioning
     *
     * Partitions:
     * 1. Overlapping ranges
     * 2. Touching at boundary
     * 3. Completely outside below
     * 4. Completely outside above
     */

    @Test
    public void testIntersectsOverlap() {
        Range r = new Range(5.0, 10.0);
        assertTrue(r.intersects(8.0, 12.0));
    }

    @Test
    public void testIntersectsTouchBoundary() {
        Range r = new Range(5.0, 10.0);
        assertTrue(r.intersects(10.0, 15.0));
    }

    @Test
    public void testIntersectsOutsideBelow() {
        Range r = new Range(5.0, 10.0);
        assertFalse(r.intersects(1.0, 4.0));
    }

    @Test
    public void testIntersectsOutsideAbove() {
        Range r = new Range(5.0, 10.0);
        assertFalse(r.intersects(11.0, 15.0));
    }


    /*
     * Method Tested: constrain(double value)
     * Black-box method: Equivalence Partitioning + Boundary Testing
     *
     * Partitions:
     * 1. Value within range
     * 2. Value below range
     * 3. Value above range
     * 4. Value exactly at boundaries
     */

    @Test
    public void testConstrainWithinRange() {
        Range r = new Range(5.0, 10.0);
        assertEquals(7.0, r.constrain(7.0), 0.0001);
    }

    @Test
    public void testConstrainBelowRange() {
        Range r = new Range(5.0, 10.0);
        assertEquals(5.0, r.constrain(3.0), 0.0001);
    }

    @Test
    public void testConstrainAboveRange() {
        Range r = new Range(5.0, 10.0);
        assertEquals(10.0, r.constrain(15.0), 0.0001);
    }

    @Test
    public void testConstrainAtBoundary() {
        Range r = new Range(5.0, 10.0);
        assertEquals(5.0, r.constrain(5.0), 0.0001);
    }


    /*
     * Method Tested: combine(Range range1, Range range2)
     * Black-box method: Equivalence Partitioning
     *
     * Partitions:
     * 1. Both ranges non-null
     * 2. First range null
     * 3. Second range null
     * 4. Both ranges null
     */

    @Test
    public void testCombineTwoRanges() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(3.0, 10.0);

        Range result = Range.combine(r1, r2);

        assertEquals(1.0, result.getLowerBound(), 0.0001);
        assertEquals(10.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testCombineFirstNull() {
        Range r2 = new Range(3.0, 10.0);

        Range result = Range.combine(null, r2);

        assertEquals(r2, result);
    }

    @Test
    public void testCombineSecondNull() {
        Range r1 = new Range(1.0, 5.0);

        Range result = Range.combine(r1, null);

        assertEquals(r1, result);
    }

    @Test
    public void testCombineBothNull() {
        Range result = Range.combine(null, null);
        assertNull(result);
    }


    /*
     * Method Tested: expandToInclude(Range range, double value)
     * Black-box method: Equivalence Partitioning + Boundary Testing
     *
     * Partitions:
     * 1. Value inside range
     * 2. Value below lower bound
     * 3. Value above upper bound
     * 4. Range is null
     */

    @Test
    public void testExpandToIncludeInsideRange() {
        Range r = new Range(5.0, 10.0);

        Range result = Range.expandToInclude(r, 7.0);

        assertEquals(5.0, result.getLowerBound(), 0.0001);
        assertEquals(10.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandToIncludeBelowRange() {
        Range r = new Range(5.0, 10.0);

        Range result = Range.expandToInclude(r, 2.0);

        assertEquals(2.0, result.getLowerBound(), 0.0001);
        assertEquals(10.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandToIncludeAboveRange() {
        Range r = new Range(5.0, 10.0);

        Range result = Range.expandToInclude(r, 15.0);

        assertEquals(5.0, result.getLowerBound(), 0.0001);
        assertEquals(15.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandToIncludeNullRange() {
        Range result = Range.expandToInclude(null, 8.0);

        assertEquals(8.0, result.getLowerBound(), 0.0001);
        assertEquals(8.0, result.getUpperBound(), 0.0001);
    }
}

