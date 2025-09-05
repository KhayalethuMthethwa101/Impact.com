public class Range {
    private final int start;
    private final int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public boolean isSingle() {
        return start == end;
    }

    @Override
    public String toString() {
        if (start == end) {
            return String.valueOf(start);
        } else {
            return start + "-" + end;
        }
    }
}
