package Comparison;

public class ExactHtmlComparisonStrategy implements ComparisonStrategy {
    @Override
    public boolean isContentChanged(StringBuilder oldContent, StringBuilder newContent) {
        return !oldContent.toString().equals(newContent.toString());
    }

    @Override
    public String getStrategyName() {
        return "Exact HTML Comparison";
    }
}