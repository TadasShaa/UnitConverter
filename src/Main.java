import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.text.DecimalFormat;

/*
*
* a simple project after first semester summer vacation, it was done during fee time
*
*/

public class Main extends Application{

    ComboBox comboBoxUnits = new ComboBox(FXCollections.observableArrayList("Temperature", "Length", "Weight"));

    ComboBox comboBoxFrom = new ComboBox();
    ComboBox comboBoxTo = new ComboBox();
    TextField text1 = new TextField();
    Label answer = new Label();
    private String fromUnit = new String();
    private String toUnit = new String();
    private Controller controller = new Controller();

    DecimalFormat df = new DecimalFormat("####0.0000");



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Unit Converter");

        GridPane gridPane = new GridPane();
        GridPane gridPane1 = new GridPane();
        gridPane.setPadding(new Insets(5, 5, 5, 10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        Label labelUnits = new Label("Measurement type:");
        Label labelFrom = new Label("Choose units:");
        Label labelTo = new Label("To:");
        Label labelequal = new Label("=");
        labelequal.setTextAlignment(TextAlignment.CENTER);
        labelequal.setFont(new Font("Arial", 30));

        answer.setFont(new Font("Arial", 30));

        Button buttonEnglish = new Button("EN");
        Button buttonDanish = new Button("DK");
        Button buttonClear = new Button("Clear/Reset");
        buttonClear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        buttonEnglish.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setTitle("Unit Converter");
                comboBoxUnits.setPromptText("Select measurement..");
                labelUnits.setText("Measurement type:");
                labelFrom.setText("Choose units:");
                labelTo.setText("To:");
                buttonClear.setText("Clear/Reset");
            }
        });

        buttonDanish.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setTitle("Konverter");
                labelUnits.setText("Måling:");
                labelFrom.setText("Vælg enheder:");
                labelTo.setText("Til:");
                buttonClear.setText("Ren/Tøm alt");
                comboBoxUnits.setPromptText("Vælg måling..");
            }
        });

        buttonClear.setOnAction(event1 -> {
            controller.clear(this);

        });

        gridPane.add(labelUnits, 0, 1);
        gridPane.add(labelFrom, 0, 2);
        gridPane.add(labelequal, 2, 3);
        gridPane.add(labelTo, 2, 2);
        gridPane1.add(buttonDanish, 0, 0);
        gridPane1.add(buttonEnglish, 1, 0);
        gridPane.add(gridPane1, 0, 0);
        gridPane.add(comboBoxFrom, 1, 2);
        gridPane.add(buttonClear, 0, 4);
        gridPane.add(comboBoxUnits, 1, 1);
        gridPane.add(comboBoxTo, 3, 2);
        gridPane.add(text1, 1, 3);
        gridPane.add(answer, 3, 3);

        comboBoxUnits.setPromptText("Select measurement..");


        comboBoxUnits.setOnAction(event -> comboBoxAction());

        comboBoxFrom.setOnAction(event -> readFrom());
        comboBoxTo.setOnAction(event -> readTo());

        text1.textProperty().addListener((observable, oldValue, newValue) -> {
            double value = Double.parseDouble(newValue);

            if (comboBoxUnits.getSelectionModel().getSelectedItem().equals("Length") || comboBoxUnits.getSelectionModel().getSelectedItem().equals("Weight")) {

                LengthConverter fromm = new LengthConverter(fromUnit);
                LengthConverter to = new LengthConverter(toUnit);

                double meters = fromm.toMeters(value);
                double converted = to.fromMeters(meters);

                System.out.println(converted);

                answer.setText(df.format(converted));

            } else {

                TempConverter tempConverter = new TempConverter();
                if (comboBoxFrom.getSelectionModel().getSelectedItem().equals("celsius") && comboBoxTo.getSelectionModel().getSelectedItem().equals("fahrenheit")) {
                    answer.setText(df.format(tempConverter.celsiusToFahrenheit(value)));

                } else if (comboBoxFrom.getSelectionModel().getSelectedItem().equals("fahrenheit") && comboBoxTo.getSelectionModel().getSelectedItem().equals("celsius")) {

                    answer.setText(df.format(tempConverter.fahrenheitToCelsius(value)));

                } else {

                    answer.setText(df.format(value));
                }
            }
        });

        Scene scene = new Scene(gridPane, 500, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void readFrom() {
        fromUnit = String.valueOf(comboBoxFrom.getSelectionModel().getSelectedItem());
    }
    public void readTo() {
        toUnit = String.valueOf(comboBoxTo.getSelectionModel().getSelectedItem());

    }
    public void comboBoxAction() {
        if (comboBoxUnits.getSelectionModel().getSelectedItem().equals("Temperature")) {
            comboBoxFrom.getItems().clear();
            comboBoxTo.getItems().clear();
            comboBoxFrom.getItems().addAll("celsius", "fahrenheit");
            comboBoxTo.getItems().addAll("celsius", "fahrenheit");
        } else if (comboBoxUnits.getSelectionModel().getSelectedItem().equals("Length")) {
            comboBoxFrom.getItems().clear();
            comboBoxTo.getItems().clear();
            comboBoxFrom.getItems().addAll("inch", "foot", "mile", "mm", "cm", "m", "km", "yard");
            comboBoxTo.getItems().addAll("inch", "foot", "mile", "mm", "cm", "m", "km", "yard");
        } else if (comboBoxUnits.getSelectionModel().getSelectedItem().equals("Weight")) {
            comboBoxFrom.getItems().clear();
            comboBoxTo.getItems().clear();
            comboBoxFrom.getItems().addAll("kg", "g", "pounds", "stones");
            comboBoxTo.getItems().addAll("kg", "g", "pounds", "stones");
        } else {
            comboBoxFrom.getItems().clear();
            comboBoxTo.getItems().clear();
        }
    }
}
