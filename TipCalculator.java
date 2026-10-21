import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TipCalculator extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Tip Calculator");

        Label billLabel = new Label("Bill Amount:");
        TextField billField = new TextField();
        billField.setPromptText("Enter bill amount");

        Label tipLabel = new Label("Tip Percentage:");
        TextField tipField = new TextField();
        tipField.setPromptText("Enter tip percentage");

        Button calculateButton = new Button("Calculate Tip");
        Button resetButton = new Button("Reset");

        Label resultLabel = new Label("Tip Amount: ₹0.00");
        Label messageLabel = new Label();

        calculateButton.setOnAction(e -> {
            try {
                double bill = Double.parseDouble(billField.getText());
                double percentage = Double.parseDouble(tipField.getText());

                if (bill < 0 || percentage < 0) {
                    messageLabel.setText(
                            "Please enter non-negative values."
                    );
                    resultLabel.setText("Tip Amount: ₹0.00");
                    return;
                }

                double tip = bill * percentage / 100.0;

                resultLabel.setText(
                        String.format("Tip Amount: ₹%.2f", tip)
                );

                messageLabel.setText("Calculation successful!");

            } catch (NumberFormatException ex) {
                messageLabel.setText(
                        "Error: Enter valid numeric values."
                );
                resultLabel.setText("Tip Amount: ₹0.00");
            }
        });

        resetButton.setOnAction(e -> {
            billField.clear();
            tipField.clear();
            resultLabel.setText("Tip Amount: ₹0.00");
            messageLabel.setText("");
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(12);
        grid.setAlignment(Pos.CENTER);

        grid.add(billLabel, 0, 0);
        grid.add(billField, 1, 0);
        grid.add(tipLabel, 0, 1);
        grid.add(tipField, 1, 1);
        grid.add(calculateButton, 0, 2);
        grid.add(resetButton, 1, 2);

        VBox root = new VBox(15, title, grid, resultLabel, messageLabel);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("Tip Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}