package tubes.tubesstrukdat.Models;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;

public class VideoUtils {
    public static void playVideoFromResources(String resourcePath) {
        try {
            URL resourceURL = VideoUtils.class.getResource(resourcePath);
            if (resourceURL == null) {
                System.out.println("Video tidak ditemukan di resources: " + resourcePath);
                return;
            }

            Media media = new Media(resourceURL.toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            MediaView mediaView = new MediaView(mediaPlayer);
            mediaView.setFitWidth(800);
            mediaView.setFitHeight(450);

            // Membuat Stage baru untuk video
            Stage videoStage = new Stage();
            videoStage.setTitle("Gimmick Video");

            StackPane root = new StackPane(mediaView);
            Scene scene = new Scene(root, 800, 450);

            videoStage.setScene(scene);
            videoStage.show();

            // Memutar video
            mediaPlayer.play();

            // Menutup otomatis ketika selesai
            mediaPlayer.setOnEndOfMedia(videoStage::close);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
