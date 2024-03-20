package demo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListDetail extends Application {

    private final ObservableList<String> peopleList = FXCollections.observableArrayList(
            "John Doe",
            "Jane Smith",
            "Michael Johnson",
            "Emily Davis"
    );

    @Override
    public void start(Stage primaryStage) {
        ListView<String> listView = new ListView<>(peopleList);
        listView.setOnMouseClicked(event -> {
			String selectedPerson = listView.getSelectionModel().getSelectedItem();
			if (selectedPerson != null) {
				showDetailsDialog(selectedPerson);
			}
        });

        VBox root = new VBox(listView);
        root.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.setTitle("People App");
        primaryStage.show();
    }

    private void showDetailsDialog(String personName) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Person Details");
        dialog.setHeaderText("Details for " + personName);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> dialog.close());

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Name:"), 0, 0);
        grid.add(new Label(personName), 1, 0);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);

        dialog.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

