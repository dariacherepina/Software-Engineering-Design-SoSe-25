package Comparison;

public class TextOnlyComparisonStrategy implements ComparisonStrategy {
    @Override
    public boolean isContentChanged(StringBuilder oldContent, StringBuilder newContent) {
        String oldText = oldContent.toString().replaceAll("<[^>]*>", "").trim();
        String newText = newContent.toString().replaceAll("<[^>]*>", "").trim();
        return !oldText.equals(newText);
    }

    @Override
    public String getStrategyName() {
        return "Text-Only Comparison";
    }
}
