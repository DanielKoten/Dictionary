package tubes.tubesstrukdat.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import tubes.tubesstrukdat.Models.Dictionary;

public class DictionaryController {
    @FXML
    private TextField inputField;
    @FXML
    private Pane resultPane;

    private Dictionary dictionary;

    public void initialize() {
        dictionary = new Dictionary();
        dictionary.loadDefaultTranslations();
    }

    @FXML
    private void onSearchButtonClick() {
        String word = inputField.getText().trim();
        if (!word.isEmpty()) {
            String translation = dictionary.translate(word);
            displayResult(word, translation);
        }
    }

    private void displayResult(String word, String translation) {
        resultPane.getChildren().clear();
        Text result = new Text(word + ": " + translation);
        result.setStyle("-fx-font-size: 18px; -fx-fill: white;");
        resultPane.getChildren().add(result);
    }
}