package tubes.tubesstrukdat.Models;


public class Translation {
    private final String word;
    private final String translation;
    private final String description;

    public Translation(String word, String translation, String description) {
        this.word = word;
        this.translation = translation;
        this.description = description;
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public String getDescription() {
        return description;
    }
}
