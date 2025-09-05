package numberrangesummarizer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import numberrangesummarizer.DefaultRangeFormatter;
import numberrangesummarizer.NumberRangeSummarizerImplementation;
import numberrangesummarizer.RangeFormatter;
import java.util.*;
import java.beans.Transient;

/**
 * Test Narration:
 *
 * This test suite validates the behavior of the NumberRangeSummarizerImplementation class.
 * The following scenarios were considered:
 * 1. Empty input collection.
 * 2. Single number input.
 * 3. Multiple numbers, including unsorted sequences.
 * 4. Inputs with spaces.
 * 5. Invalid numbers (non-numeric input).
 * 6. Duplicate numbers.
 * 7. Negative numbers.
 * 8. Consecutive and non-consecutive numbers for range summarization.
 * 9. Large datasets to evaluate performance and scalability.
 *
 * The tests check both the raw collection of numbers (i.e. Input=> "1,3,6,7,8,12,13,14,15,21,22,23,24,31") and the formatted summarized ranges,
 * ensuring correct ordering, deduplication, and proper range formatting. (i.e Output =>"1,3,6-8,12-15,21-24,31").
 *
 * Author: Khayalethu Mthethwa
 */


public class NumberRangeSummerizerImplementationTest {
    private final RangeFormatter formatter = new DefaultRangeFormatter("-");
    private final NumberRangeSummarizerImplementation summarizer = new NumberRangeSummarizerImplementation(formatter);

    @Test
    public void testCollectionEmptyString(){
        Collection<Integer> result = summarizer.collect("");
        assertTrue(result.isEmpty(), "Empty string should return an empty collection");
    }

    @Test
    public void testCollectSingleNumber() {
        Collection<Integer> result = summarizer.collect("5");
        assertEquals(List.of(5), new ArrayList<>(result));
    }

    @Test
    public void testCollectWithDuplicates() {
        Collection<Integer> result = summarizer.collect("1,2,2,3,3,3,5,5");
        assertEquals(List.of(1,2,3,5), new ArrayList<>(result));
    }
    
    @Test
    public void testCollectMultipleNumbers() {
        Collection<Integer> result = summarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        List<Integer> expected = List.of(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
        assertEquals(expected, new ArrayList<>(result), "Numbers should be collected and sorted");
    }

    @Test
    //This is meant to test that the input can be converted into an ordered List of integers
    public void testCollectWithSpaces() {
        Collection<Integer> result = summarizer.collect(" 10 , 5 , 7 ");
        assertEquals(List.of(5,7,10), new ArrayList<>(result));
    }

    @Test
    public void testCollectInvalidNumbers() {
        Collection<Integer> result = summarizer.collect("1,2,abc,4");
        assertEquals(List.of(1,2,4), new ArrayList<>(result), "Invalid numbers should be ignored");
    }

    @Test
    public void testSummarizeEmptyCollection() {
        String result = summarizer.summarizeCollection(Collections.emptyList());
        assertEquals("", result);
    }

    @Test
    public void testSummarizeSingleNumber() {
        String result = summarizer.summarizeCollection(List.of(5));
        assertEquals("5", result);
    }

    @Test
    public void testSummarizeAllConsecutive() {
        String result = summarizer.summarizeCollection(List.of(1,2,3,4,5));
        assertEquals("1-5", result);
    }

    @Test
    public void testSummarizeNoConsecutiveNumbers() {
        String result = summarizer.summarizeCollection(List.of(1,3,5,7));
        assertEquals("1, 3, 5, 7", result);
    }

    @Test
    public void testSummarizeWithDuplicates() {
        String result = summarizer.summarizeCollection(List.of(1,2,2,3,5,5,5));
        assertEquals("1-3, 5", result, "Duplicates should be collapsed");
    }

    @Test
    public void testSummarizeMixedRanges() {
        String result = summarizer.summarizeCollection(List.of(1,2,3,5,6,7,9,11,12));
        assertEquals("1-3, 5-7, 9, 11-12", result);
    }

    @Test
    public void testSummarizeUnsortedInput() {
        String result = summarizer.summarizeCollection(List.of(12,11,9,7,6,5,3,2,1));
        assertEquals("1-3, 5-7, 9, 11-12", result);
    }

    @Test
    public void testSummarizeWithNegativeNumbers() {
        String result = summarizer.summarizeCollection(List.of(-3,-2,-1,2,3,4));
        assertEquals("-3--1, 2-4", result);
    }

    @Test
    public void testSummarizeLargeRangePerformance() {
        List<Integer> bigList = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) bigList.add(i);
        String result = summarizer.summarizeCollection(bigList);
        assertEquals("0-999999", result);
    }

    @Test
    public void testCollectLargeDataSetWithDuplicates() {
        // Create 100,000 numbers with duplicates
        List<Integer> bigList = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            bigList.add(i % 1000);
        }

        Collection<Integer> collected = summarizer.collect(String.join(",", bigList.stream().map(String::valueOf).toArray(String[]::new)));

        assertEquals(1000, collected.size());
        assertEquals(0, Collections.min(collected));
        assertEquals(999, Collections.max(collected));
    }
}
