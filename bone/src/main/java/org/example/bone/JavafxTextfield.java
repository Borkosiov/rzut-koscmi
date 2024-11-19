package org.example.bone;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;

public class JavafxTextfield extends Application {

    private int sumaKosci = 0;
    private Label Wynikl = new Label("Suma wyników: 0");

    @Override
    public void start(Stage stage) {

        TextField textField1 = new TextField("");
        Label label1 = new Label("Ile razy chcesz rzucić kostką (3 - 10): ");
        Button rzut = new Button("Rzuć kośćmi");
        Button reset = new Button("Reset");

        VBox kostki = new VBox(10);
        kostki.setPadding(new Insets(10));


        rzut.setOnAction(e -> {
            String str = textField1.getText();
            int ilosc_roll;

            try {
                ilosc_roll = Integer.parseInt(str);
            } catch (NumberFormatException ex) {
                showAlert("Wpisz liczbę całkowitą.");
                return;
            }

            if (ilosc_roll < 3 || ilosc_roll > 10) {
                showAlert("Wpisz liczbę między 3 a 10.");
                return;
            }

            kostki.getChildren().clear();

            LinkedList<Integer> rolls = new LinkedList<>();
            Random generator = new Random();

            int rollSum = 0;
            for (int i = 0; i < ilosc_roll; i++) {
                int roll = generator.nextInt(6) + 1;
                rolls.add(roll);
                rollSum += roll;
            }

            System.out.println(rolls);
            sumaKosci += rollSum;
            Wynikl.setText("Suma wyników: " + sumaKosci);

            for (int roll : rolls) {
                Image image = loadImageForRoll(roll);
                if (image != null) {
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    kostki.getChildren().add(imageView);
                }
            }
        });

        reset.setOnAction(e -> {
            sumaKosci = 0; // Reset sumy
            Wynikl.setText("Suma wyników: 0");
            kostki.getChildren().clear();
            textField1.clear();
        });

        VBox box = new VBox(5);
        box.setPadding(new Insets(25, 5, 5, 50));
        box.getChildren().addAll(label1, textField1, rzut, reset, kostki, Wynikl);

        Scene scene = new Scene(box, 595, 300, Color.BEIGE);
        stage.setTitle("Rzut kością");
        stage.setScene(scene);
        stage.show();
    }

    private Image loadImageForRoll(int roll) {
        String imagePath = "C:\\Users\\qba\\IdeaProjects\\bone\\bone" + roll + ".png";
        try {
            return new Image(new FileInputStream(imagePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            showAlert("Nie znaleziono obrazka dla wyniku: " + roll);
            return null;
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
