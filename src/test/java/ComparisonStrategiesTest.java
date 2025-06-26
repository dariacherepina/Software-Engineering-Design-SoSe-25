import Comparison.ExactHtmlComparisonStrategy;
import Comparison.SizeComparisonStrategy;
import Comparison.TextOnlyComparisonStrategy;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ComparisonStrategiesTest {
    private ExactHtmlComparisonStrategy exactStrategy;
    private SizeComparisonStrategy sizeStrategy;
    private TextOnlyComparisonStrategy textStrategy;

    @BeforeEach
    void setUp() {
        exactStrategy = new ExactHtmlComparisonStrategy();
        sizeStrategy = new SizeComparisonStrategy();
        textStrategy = new TextOnlyComparisonStrategy();
    }

    @Test
    void testExactHtmlComparison() {
        StringBuilder oldContent = new StringBuilder("<html><body>Test</body></html>");
        StringBuilder sameContent = new StringBuilder("<html><body>Test</body></html>");
        StringBuilder diffContent = new StringBuilder("<html><body>Changed</body></html>");

        assertFalse(exactStrategy.isContentChanged(oldContent, sameContent));
        assertTrue(exactStrategy.isContentChanged(oldContent, diffContent));
    }

    @Test
    void testSizeComparison() {
        StringBuilder oldContent = new StringBuilder("12345");
        StringBuilder sameSize = new StringBuilder("67890");
        StringBuilder diffSize = new StringBuilder("123");

        assertFalse(sizeStrategy.isContentChanged(oldContent, sameSize));
        assertTrue(sizeStrategy.isContentChanged(oldContent, diffSize));
    }

    @Test
    void testTextOnlyComparison() {
        StringBuilder oldContent = new StringBuilder("<html><body>Test</body></html>");
        StringBuilder sameText = new StringBuilder("<div><p>Test</p></div>");
        StringBuilder diffText = new StringBuilder("<html><body>Changed</body></html>");

        assertFalse(textStrategy.isContentChanged(oldContent, sameText));
        assertTrue(textStrategy.isContentChanged(oldContent, diffText));
    }
}
