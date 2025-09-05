import java.util.*;

public class NumberRangeSummarizerImplementation implements NumberRangeSummarizer {
    private final RangeFormatter rangeFormatter;
    private final RangeFormatter formatter;

    public NumberRangeSummarizerImplementation(RangeFormatter rangeFormatter, String formatter) {
        this.rangeFormatter = rangeFormatter;
        this.formatter = formatter;
    }

    @Override
    public Collection<Integer> collect(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Collections.emptyList();
        }

        String[] parts = input.split(",");
        List<Integer> number = new ArrayList<>();

        for (String part : parts) {
            try {
                number.add(Integer.parseInt(part.trim()));
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format: " + part);
            }
        }
        return number;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        List<Range> ranges = new ArrayList<>();
        TreeSet<Integer> sortedInput = new TreeSet<>(input);
        Integer currentNumber = null;
        Integer previousNumber = null;

        for (Integer number : sortedInput) {
            if (currentNumber == null) {
                currentNumber = number;
            } else if (number != previousNumber + 1) {
                previousNumber = number;
            } 
            previousNumber = number;
        }

        ranges.add(new Range(currentNumber, previousNumber));
        List<String> formattedRanges = new ArrayList<>();
        for (Range range : ranges) {
            formattedRanges.add(rangeFormatter.format(range));
        }
        return String.join(", ", formattedRanges);
    }
    
}
