package numberrangesummarizer;

import java.util.*;

public class NumberRangeSummarizerImplementation implements NumberRangeSummarizer {
    private String rangeSeparator;
    private String delimiter;

    public NumberRangeSummarizerImplementation(String rangeSeparator, String delimiter) {
        this.rangeSeparator = rangeSeparator;
        this.delimiter = delimiter;
    }

    @Override
    public Collection<Integer> collect(String input) {
        Set<Integer> numbers = new TreeSet<>();
        if (input == null || input.trim().isEmpty()) {
            return numbers;
        }

        String[] parts = input.split(delimiter);
        for (String part : parts) {
            try {
                numbers.add(Integer.parseInt(part.trim()));
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format: " + part);
            }
        }
        return numbers;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        // The assumption is that input is sorted and unique due to the collect() method
        List<Integer> inputList = new ArrayList<>(input);
        List<String> ranges = new ArrayList<>();
        Range currentRange = new Range(inputList.get(0), inputList.get(0), rangeSeparator);

        for (int i = 1; i < inputList.size(); i++) {
            int current = inputList.get(i);
            if (current == currentRange.getEnd() + 1) {
                currentRange.setEnd(current);
            } else {
                ranges.add(currentRange.toString());
                currentRange.reset(current, current); 
            }
        }
        ranges.add(currentRange.toString());
        
        //The assumption here, is that we join the ranges with a ", " separator, as per output in the instructions
        return String.join(delimiter + " ", ranges);
    }
}
