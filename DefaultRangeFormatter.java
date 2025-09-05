public class DefaultRangeFormatter {
    private final String rangeSymbol;

    public DefaultRangeFormatter(String rangeSymbol) {
        this.rangeSymbol = rangeSymbol;
    }

    @Override
    public String format(Range range) {
        if (range.isSingle()) {
            return String.valueOf(range.getStart());
        } else {
            return range.getStart() + rangeSymbol + range.getEnd();
        }
    }
}
