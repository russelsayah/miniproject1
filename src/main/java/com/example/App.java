package com.example;



import java.time.LocalDate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sign Up");
        primaryStage.setResizable(false);

        // Sign-In Form
        GridPane signInGrid = createSignInForm();
        Scene signInScene = new Scene(signInGrid, 350, 250);

        // Success Scene
        Scene successScene = createSuccessScene();

        // Menu Scene
        Scene menuScene = createMenuScene();

        // Set the initial scene
        primaryStage.setScene(signInScene);
        primaryStage.show();

        // Sign-In Button Action
        Button signInButton = (Button) signInGrid.lookup("#signInButton");
        Text errorMsg = (Text) signInGrid.lookup("#errorMsg");

        signInButton.setOnAction(e -> {
            TextField usernameField = (TextField) signInGrid.lookup("#usernameField");
            PasswordField passwordField = (PasswordField) signInGrid.lookup("#passwordField");

            if (usernameField.getText().equals("admin") && passwordField.getText().equals("1234")) {
                primaryStage.setScene(successScene);
                Stage menuStage = new Stage();
                menuStage.setScene(menuScene);
                menuStage.setTitle("Menu Order");
                menuStage.setResizable(false);
                menuStage.show();
            } else if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                errorMsg.setText("Please fill all the fields");
            } else {
                errorMsg.setText("Invalid credentials");
            }
        });
    }

    private GridPane createSignInForm() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text msg = new Text("Welcome!");
        msg.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        msg.setFill(Color.BROWN);
        grid.add(msg, 0, 0, 2, 1);

        Label username = new Label("Username:");
        grid.add(username, 0, 1);
        TextField usernameField = new TextField();
        usernameField.setId("usernameField");
        grid.add(usernameField, 1, 1);

        Label password = new Label("Password:");
        grid.add(password, 0, 2);
        PasswordField passwordField = new PasswordField();
        passwordField.setId("passwordField");
        grid.add(passwordField, 1, 2);

        Button signInButton = new Button("Sign In");
        signInButton.setId("signInButton");
        grid.add(signInButton, 0, 3);

        Text errorMsg = new Text();
        errorMsg.setId("errorMsg");
        errorMsg.setFill(Color.RED);
        grid.add(errorMsg, 1, 3, 2, 1);

        return grid;
    }

    private Scene createSuccessScene() {
        Text successful = new Text("Login Successful!");
        successful.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        successful.setFill(Color.WHITE);
        StackPane root = new StackPane();
        root.getChildren().add(successful);
        Scene worked = new Scene(root, 350, 250);
        worked.getRoot().setStyle("-fx-background-color: green");
        return worked;
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    private Scene createMenuScene() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Title
        Text title = new Text("Order:");
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        title.setFill(Color.BROWN);
        grid.add(title, 0, 0, 2, 1);

        // Name and Date of Birth
        Label nameLabel = new Label("Name:");
        grid.add(nameLabel, 0, 1);
        TextField nameField = new TextField();
        grid.add(nameField, 1, 1);

        Label dobLabel = new Label("Date of Birth:");
        grid.add(dobLabel, 0, 2);
        DatePicker dobPicker = new DatePicker();
        dobPicker.setValue(LocalDate.now());
        grid.add(dobPicker, 1, 2);

        // Food Choices
        Label foodLabel = new Label("Choose your food:");
        grid.add(foodLabel, 0, 3);
        HBox foodBox = new HBox(10);
        RadioButton pizza = new RadioButton("Pizza");
        RadioButton burger = new RadioButton("Burger");
        RadioButton hotdog = new RadioButton("HotDog");
        ToggleGroup foodGroup = new ToggleGroup();
        pizza.setToggleGroup(foodGroup);
        burger.setToggleGroup(foodGroup);
        hotdog.setToggleGroup(foodGroup);
        foodBox.getChildren().addAll(pizza, burger, hotdog);
        grid.add(foodBox, 1, 3);

        // Ketchup
        Label ketchupLabel = new Label("Ketchup:");
        grid.add(ketchupLabel, 0, 4);
        HBox ketchupBox = new HBox(10);
        RadioButton yesKetchup = new RadioButton("Yes");
        RadioButton noKetchup = new RadioButton("No");
        ToggleGroup ketchupGroup = new ToggleGroup();
        yesKetchup.setToggleGroup(ketchupGroup);
        noKetchup.setToggleGroup(ketchupGroup);
        ketchupBox.getChildren().addAll(yesKetchup, noKetchup);
        grid.add(ketchupBox, 1, 4);

        // Drinks
        Label drinksLabel = new Label("Drinks:");
        grid.add(drinksLabel, 0, 5);
        ChoiceBox<String> drinksChoice = new ChoiceBox<>();
        drinksChoice.getItems().addAll("Pepsi", "7up", "Ice Tea", "Miranda");
        drinksChoice.getSelectionModel().selectFirst();
        grid.add(drinksChoice, 1, 5);

        // Utensils
        Label utensilsLabel = new Label("Utensils:");
        grid.add(utensilsLabel, 0, 6);
        HBox utensilsBox = new HBox(10);
        RadioButton yesUtensils = new RadioButton("Yes");
        RadioButton noUtensils = new RadioButton("No");
        ToggleGroup utensilsGroup = new ToggleGroup();
        yesUtensils.setToggleGroup(utensilsGroup);
        noUtensils.setToggleGroup(utensilsGroup);
        utensilsBox.getChildren().addAll(yesUtensils, noUtensils);
        grid.add(utensilsBox, 1, 6);

        // Address
        Label addressLabel = new Label("Address:");
        grid.add(addressLabel, 0, 7);
        TextField addressField = new TextField();
        grid.add(addressField, 1, 7);

        // Submit Button
        Button submitButton = new Button("Submit");
        grid.add(submitButton, 0, 8, 2, 1);

        // Table to display orders
        TableView<Order> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Order, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Order, String> dobCol = new TableColumn<>("Date of Birth");
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));

        TableColumn<Order, String> foodCol = new TableColumn<>("Food");
        foodCol.setCellValueFactory(new PropertyValueFactory<>("food"));

        TableColumn<Order, String> ketchupCol = new TableColumn<>("Ketchup");
        ketchupCol.setCellValueFactory(new PropertyValueFactory<>("ketchup"));

        TableColumn<Order, String> drinksCol = new TableColumn<>("Drinks");
        drinksCol.setCellValueFactory(new PropertyValueFactory<>("drinks"));

        TableColumn<Order, String> utensilsCol = new TableColumn<>("Utensils");
        utensilsCol.setCellValueFactory(new PropertyValueFactory<>("utensils"));

        TableColumn<Order, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        table.getColumns().addAll(nameCol, dobCol, foodCol, ketchupCol, drinksCol, utensilsCol, addressCol);

        ObservableList<Order> data = FXCollections.observableArrayList();
        table.setItems(data);

        grid.add(table, 0, 9, 2, 1);

        // Submit Button Action
        submitButton.setOnAction(e -> {
            String name = nameField.getText();
            String dob = dobPicker.getValue().toString();
            String food = ((RadioButton) foodGroup.getSelectedToggle()).getText();
            String ketchup = ((RadioButton) ketchupGroup.getSelectedToggle()).getText();
            String drinks = drinksChoice.getValue();
            String utensils = ((RadioButton) utensilsGroup.getSelectedToggle()).getText();
            String address = addressField.getText();

            if (!name.isEmpty() && !dob.isEmpty() && !food.isEmpty() && !ketchup.isEmpty() && !drinks.isEmpty() && !utensils.isEmpty() && !address.isEmpty()) {
                data.add(new Order(name, dob, food, ketchup, drinks, utensils, address));
            } else {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
            }
        });

        return new Scene(grid, 1000, 600);
    }

    // Order class to hold data
    public static class Order {
        private final String name;
        private final String dob;
        private final String food;
        private final String ketchup;
        private final String drinks;
        private final String utensils;
        private final String address;

        public Order(String name, String dob, String food, String ketchup, String drinks, String utensils, String address) {
            this.name = name;
            this.dob = dob;
            this.food = food;
            this.ketchup = ketchup;
            this.drinks = drinks;
            this.utensils = utensils;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public String getDob() {
            return dob;
        }

        public String getFood() {
            return food;
        }

        public String getKetchup() {
            return ketchup;
        }

        public String getDrinks() {
            return drinks;
        }

        public String getUtensils() {
            return utensils;
        }

        public String getAddress() {
            return address;
        }
    }

    public static void setRoot(String string) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setRoot'");
    }
}