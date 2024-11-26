package tubes.tubesstrukdat.Models;

public class TranslationResult {
    private final String translation;
    private final String explanation;

    public TranslationResult(String translation, String explanation) {
        this.translation = translation;
        this.explanation = explanation;
    }

    public String getTranslation() {
        return translation;
    }

    public String getExplanation() {
        return explanation;
    }
}
