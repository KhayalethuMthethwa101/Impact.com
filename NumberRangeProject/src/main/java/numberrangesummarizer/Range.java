package numberrangesummarizer;

public class Range {
    private int start;
    private int end;
    private final String separator;

    public Range(int start, int end, String separator) {
        this.start = start;
        this.end = end;
        this.separator = separator;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getSeparator() {
        return separator;
    }

    public void setEnd(int newEnd) {
        end = newEnd;
    }

    public void setStart(int newStart) {
        start = newStart;
    }

    public void reset(int newStart, int newEnd) {
        this.start = newStart;
        this.end = newEnd;
    }

    public boolean isSingle() {
        return start == end;
    }

    @Override
    public String toString() {
        if (start == end) {
            return String.valueOf(start);
        } else {
            return start + separator + end;
        }
    }
}
