package numberrangesummarizer;

import java.util.*;

public class NumberRangeSummarizerImplementation implements NumberRangeSummarizer {
    private final RangeFormatter rangeFormatter;

    public NumberRangeSummarizerImplementation(RangeFormatter rangeFormatter) {
        this.rangeFormatter = rangeFormatter;
    }

    @Override
    public Collection<Integer> collect(String input) {
        Set<Integer> numbers = new TreeSet<>();
        if (input == null || input.trim().isEmpty()) {
            return numbers;
        }

        String[] parts = input.split(",");
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

        TreeSet<Integer> numbersSet = new TreeSet<>(input);
        List<Integer> numbers = new ArrayList<>(numbersSet);

        List<String> ranges = new ArrayList<>();
        int start = numbers.get(0);
        int end = start;

        for (int i = 1; i < numbers.size(); i++) {
            int current = numbers.get(i);
            if (current == end + 1) {
                end = current;
            } else {
                ranges.add(formatRange(start, end));
                start = end = current; 
            }
        }
        ranges.add(formatRange(start, end));

        return String.join(", ", ranges);
    }
    private String formatRange(int start, int end) {
        return start == end ? String.valueOf(start) : start + "-" + end;
    }
}
