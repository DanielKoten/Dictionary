package tubes.tubesstrukdat.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import tubes.tubesstrukdat.Models.Dictionary;
import tubes.tubesstrukdat.Models.TranslationResult;

public class DictionaryController {
    @FXML
    public TextFlow textFlow;
    @FXML
    private TextField inputField;
    @FXML
    private VBox resultField;
    @FXML
    private VBox resultField1;
    @FXML
    private ListView<String> riwayatListView;  // Menggunakan tipe data String untuk riwayat

    private Dictionary dictionary;
    private String selectedLanguage = "id";  // Default language is Indonesian

    public void initialize() {
        dictionary = new Dictionary();
        dictionary.loadDefaultTranslations();

        riwayatListView.setCellFactory(listView -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("-fx-background-color: transparent;");
                } else {
                    setText(item);
                    // Set font untuk item ListView
                    setFont(Font.font("Arial", 16)); // Ubah font di sini (ganti "Arial" dan ukuran 16 sesuai kebutuhan)
                    setStyle("-fx-text-fill: blue; -fx-background-color: transparent;"); // Mengubah warna teks jika diperlukan

                    setOnMouseEntered(event -> {
                        setStyle("-fx-text-fill: blue; -fx-border-color: #2f6dae; -fx-border-width: 2px; -fx-background-color: transparent;");
                    });

                    setOnMouseExited(event -> {
                        setStyle("-fx-text-fill: blue; -fx-border-color: transparent; -fx-border-width: 0px; -fx-background-color: transparent;");
                    });
                }
            }
        });
    }

    // Event handler untuk tombol ID
    @FXML
    private void onIdButtonClick() {
        selectedLanguage = "id";  // Pilih Bahasa Indonesia
    }

    // Event handler untuk tombol EN
    @FXML
    private void onEnButtonClick() {
        selectedLanguage = "en";  // Pilih Bahasa Inggris
    }

    // Fungsi untuk menangani pencarian kata
    @FXML
    private void onSearchButtonClick() {
        String word = inputField.getText().trim();
        if (!word.isEmpty()) {
            // Lakukan terjemahan sesuai bahasa yang dipilih
            TranslationResult translationResult = dictionary.translate(word, selectedLanguage);
            displayResult(translationResult);

            // Menambahkan hasil terjemahan ke dalam riwayat
            addToHistory(word, translationResult);
        }
    }

    private void displayResult(TranslationResult translationResult) {
        resultField.getChildren().clear();
        resultField1.getChildren().clear();

        // Menampilkan hasil terjemahan
        Text result = new Text(selectedLanguage.equals("en") ? "Translation: " + translationResult.getTranslation() : "Terjemahan: " + translationResult.getTranslation());
        result.setStyle("-fx-font-size: 25px; -fx-fill: white; -fx-font-family: 'Tw Cen MT Condensed Extra Bold';");
        resultField.getChildren().add(result);

        // Menampilkan penjelasan
        Text explanationText = new Text(selectedLanguage.equals("en") ? "Explanation:\n" + translationResult.getExplanation() : "Penjelasan:\n" + translationResult.getExplanation());
        explanationText.setStyle("-fx-font-size: 18px; -fx-fill: white; -fx-font-family: 'Arial';");

        resultField1.setMaxHeight(101);
        resultField1.setMaxWidth(314);
        explanationText.setWrappingWidth(314);
        resultField1.getChildren().add(explanationText);
    }

    // Menambahkan hasil terjemahan ke riwayat
    private void addToHistory(String word, TranslationResult translationResult) {
        String historyItem = word + " - " + translationResult.getTranslation();

        // Menambahkan item ke ListView riwayat
        riwayatListView.getItems().add(0, historyItem);  // Menambah ke awal riwayat

        // Membatasi riwayat hanya 5 item terbaru
        if (riwayatListView.getItems().size() > 5) {
            riwayatListView.getItems().remove(riwayatListView.getItems().size() - 1);  // Menghapus item terakhir jika melebihi 5
        }
    }
}
