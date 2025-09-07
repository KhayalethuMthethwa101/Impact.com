package numberrangesummarizer;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String input = "1,2,3,5,7,8,9,10,12";
        NumberRangeSummarizer summarizer = new NumberRangeSummarizerImplementation("-", ",");

        Collection<Integer> numbers = summarizer.collect(input);
        String summary = summarizer.summarizeCollection(numbers);

        System.out.println("Input: " + input);
        System.out.println("Summary: " + summary);
    }
    
}
