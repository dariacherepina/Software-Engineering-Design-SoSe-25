package Comparison;

public class SizeComparisonStrategy implements ComparisonStrategy {
    @Override
    public boolean isContentChanged(StringBuilder oldContent, StringBuilder newContent) {
        return oldContent.length() != newContent.length();
    }

    @Override
    public String getStrategyName() {
        return "Content Size Comparison";
    }
}
