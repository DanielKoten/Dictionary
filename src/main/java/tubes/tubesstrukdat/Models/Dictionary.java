package tubes.tubesstrukdat.Models;

public class Dictionary {
    private final RedBlackTree dictionaryTree;

    public Dictionary() {
        dictionaryTree = new RedBlackTree();
    }

    public void loadDefaultTranslations() {
        // Menambahkan terjemahan dengan penjelasan dalam bahasa Inggris dan Indonesia
        dictionaryTree.insert("apple", "apel", "A fruit that is often red, green, or yellow.", "Buah yang sering berwarna merah, hijau, atau kuning.");
        dictionaryTree.insert("dog", "anjing", "A domesticated carnivorous mammal that typically has a long snout and barks.", "Mamalia karnivora yang biasanya memiliki moncong panjang dan menggonggong.");
        dictionaryTree.insert("cat", "kucing", "A small domesticated carnivorous mammal with soft fur, a short snout, and retractile claws.", "Mamalia karnivora kecil yang memiliki bulu lembut, moncong pendek, dan cakar yang dapat ditarik.");
        dictionaryTree.insert("banana", "pisang", "A long curved fruit that grows in clusters and has soft pulpy flesh and yellow skin when ripe.", "Buah panjang yang tumbuh berkelompok dengan daging buah lembut dan kulit kuning saat matang.");
        dictionaryTree.insert("chicken", "ayam", "A domestic fowl kept for its eggs or meat, especially a young one.", "Unggas yang dipelihara untuk telurnya atau dagingnya, terutama yang muda.");
        dictionaryTree.insert("hedgehog", "landak", "A rodents that have sharp spines and thick hair", "Hewan pengerat yang memiliki duri tajam dan rambut tebal");
    }

    public TranslationResult translate(String word, String language) {
        Node result = null;

        // Jika bahasa yang dipilih adalah ID, artinya terjemahkan dari Bahasa Indonesia ke Bahasa Inggris
        if ("id".equalsIgnoreCase(language)) {
            result = dictionaryTree.searchByValue(word.toLowerCase()); // Mencari kata dalam Bahasa Indonesia
            if (result != null) {
                return new TranslationResult(result.getKey(), result.getTranslationDescription());  // Terjemahkan ke Bahasa Inggris
            } else {
                return new TranslationResult("Tidak ditemukan.", "Tidak ditemukan.");
            }
        }
        // Jika bahasa yang dipilih adalah EN, artinya terjemahkan dari Bahasa Inggris ke Bahasa Indonesia
        else if ("en".equalsIgnoreCase(language)) {
            result = dictionaryTree.search(word.toLowerCase()); // Mencari kata dalam Bahasa Inggris
            if (result != null) {
                return new TranslationResult(result.getValue(), result.getDescription());  // Terjemahkan ke Bahasa Indonesia
            } else {
                return new TranslationResult("Not found.", "Not found.");
            }
        }
        return null;
    }
}
