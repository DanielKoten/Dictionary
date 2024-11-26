package tubes.tubesstrukdat.Models;

public class Dictionary {
    private final RedBlackTree<String, String> tree;

    public Dictionary() {
        tree = new RedBlackTree<>();
    }

    public void loadDefaultTranslations() {
        tree.insert("apple", "apel");
        tree.insert("dog", "anjing");
        tree.insert("cat", "kucing");
        tree.insert("banana", "pisang");
        tree.insert("chicken", "ayam");
        // Tambahkan data lain sesuai kebutuhan
    }

    public String translate(String word) {
        String result = tree.search(word.toLowerCase());
        return result != null ? result : "Terjemahan tidak ditemukan.";
    }
}
