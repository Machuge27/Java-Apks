package myapp;

import BEAN.MatchBean;
import BEAN.PlayerBean;
import BEAN.TeamManagerBean;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MyApp extends Application {
    private TeamManagerBean teamManager = new TeamManagerBean();

    @Override
    public void start(Stage primaryStage) {
        showLoginPage(primaryStage);
    }

    private void showLoginPage(Stage primaryStage) {
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        loginButton.setOnAction(e -> {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please enter both username and password.");
            } else {
                showMainScreen(primaryStage);
            }
        });

        VBox loginLayout = new VBox(10);
        loginLayout.getChildren().addAll(
                new Label("Username:"), usernameField,
                new Label("Password:"), passwordField,
                loginButton
        );

        loginLayout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-spacing: 15;");
        Scene loginScene = new Scene(loginLayout, 400, 300);
        primaryStage.setTitle("Football Team Manager");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void showMainScreen(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        // Player Management Tab
        Tab playerTab = new Tab("Players", createPlayerManagementTab());
        playerTab.setClosable(false);

        // Match Management Tab
        Tab matchTab = new Tab("Matches", createMatchManagementTab());
        matchTab.setClosable(false);

        tabPane.getTabs().addAll(playerTab, matchTab);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> showLoginPage(primaryStage));

        VBox mainLayout = new VBox(10, tabPane, logoutButton);
        mainLayout.setStyle("-fx-padding: 10;");

        Scene mainScene = new Scene(mainLayout, 600, 700);
        primaryStage.setScene(mainScene);
    }

    private VBox createPlayerManagementTab() {
        // Add Player Form
        TextField playerNameField = new TextField();
        TextField playerPositionField = new TextField();
        Button savePlayerButton = new Button("Save Player");

        savePlayerButton.setOnAction(e -> {
            String name = playerNameField.getText();
            String position = playerPositionField.getText();
            if (name.isEmpty() || position.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields.");
            } else {
                teamManager.addPlayer(new PlayerBean(name, position));
                playerNameField.clear();
                playerPositionField.clear();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Player added successfully.");
            }
        });

        VBox addPlayerLayout = new VBox(10,
                new Label("Player Name:"), playerNameField,
                new Label("Position:"), playerPositionField,
                savePlayerButton
        );

        // View Players Table
        TableView<PlayerBean> playersTable = new TableView<>();
        ObservableList<PlayerBean> players = teamManager.getPlayers();

        TableColumn<PlayerBean, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<PlayerBean, String> positionColumn = new TableColumn<>("Position");
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<PlayerBean, Integer> goalsColumn = new TableColumn<>("Goals");
        goalsColumn.setCellValueFactory(new PropertyValueFactory<>("goals"));

        TableColumn<PlayerBean, Integer> assistsColumn = new TableColumn<>("Assists");
        assistsColumn.setCellValueFactory(new PropertyValueFactory<>("assists"));

        playersTable.setItems(players);
        playersTable.getColumns().addAll(nameColumn, positionColumn, goalsColumn, assistsColumn);

        VBox playerManagementLayout = new VBox(10, addPlayerLayout, new Label("Player List:"), playersTable);
        playerManagementLayout.setStyle("-fx-padding: 10;");
        return playerManagementLayout;
    }

private VBox createMatchManagementTab() {
    // Add Match Form
    TextField opponentField = new TextField();
    TextField dateField = new TextField();
    TextField ourScoreField = new TextField();
    TextField opponentScoreField = new TextField();
    Button saveMatchButton = new Button("Save Match");

    saveMatchButton.setOnAction(e -> {
        String opponent = opponentField.getText();
        String date = dateField.getText();
        int ourScore, opponentScore;

        try {
            ourScore = Integer.parseInt(ourScoreField.getText());
            opponentScore = Integer.parseInt(opponentScoreField.getText());

            if (opponent.isEmpty() || date.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields.");
            } else {
                teamManager.addMatch(new MatchBean(opponent, date, ourScore, opponentScore));
                opponentField.clear();
                dateField.clear();
                ourScoreField.clear();
                opponentScoreField.clear();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Match added successfully.");
            }
        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Scores must be valid numbers.");
        }
    });

    VBox addMatchLayout = new VBox(10,
            new Label("Opponent:"), opponentField,
            new Label("Date:"), dateField,
            new Label("Our Score:"), ourScoreField,
            new Label("Opponent Score:"), opponentScoreField,
            saveMatchButton
    );

    // View Matches Table
    TableView<MatchBean> matchesTable = new TableView<>();
    ObservableList<MatchBean> matches = teamManager.getMatches();

    TableColumn<MatchBean, String> opponentColumn = new TableColumn<>("Opponent");
    opponentColumn.setCellValueFactory(new PropertyValueFactory<>("opponent"));

    TableColumn<MatchBean, String> dateColumn = new TableColumn<>("Date");
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

    TableColumn<MatchBean, Integer> ourScoreColumn = new TableColumn<>("Our Score");
    ourScoreColumn.setCellValueFactory(new PropertyValueFactory<>("ourScore"));

    TableColumn<MatchBean, Integer> opponentScoreColumn = new TableColumn<>("Opponent Score");
    opponentScoreColumn.setCellValueFactory(new PropertyValueFactory<>("opponentScore"));

    matchesTable.setItems(matches);
    matchesTable.getColumns().addAll(opponentColumn, dateColumn, ourScoreColumn, opponentScoreColumn);

    VBox matchManagementLayout = new VBox(10, addMatchLayout, new Label("Match List:"), matchesTable);
    matchManagementLayout.setStyle("-fx-padding: 10;");
    return matchManagementLayout;
}

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
