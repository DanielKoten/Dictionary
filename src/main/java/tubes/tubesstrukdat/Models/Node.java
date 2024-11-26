package tubes.tubesstrukdat.Models;

public class Node {
    private String key; // Kata asli
    private String value; // Terjemahan
    private String description; // Penjelasan dalam bahasa key
    private String translationDescription; // Penjelasan dalam bahasa value
    private Node left, right, parent;
    private boolean isRed;

    public Node(String key, String value, String description, String translationDescription) {
        this.key = key;
        this.value = value;
        this.description = description;
        this.translationDescription = translationDescription;
        this.isRed = true; // Default setiap node baru adalah merah
    }

    // Getter dan Setter
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTranslationDescription() {
        return translationDescription;
    }

    public void setTranslationDescription(String translationDescription) {
        this.translationDescription = translationDescription;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }
}
