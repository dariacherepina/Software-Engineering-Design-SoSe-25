package Comparison;

public interface ComparisonStrategy {
    boolean isContentChanged(StringBuilder oldContent, StringBuilder newContent);
    String getStrategyName();
}
