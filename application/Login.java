package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.admin.Admin;
import application.librarian.Librarian_Main_FX;
import application.student.StudentViewController;
import dao.LoginAdminDBController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utilities.Helper;


public class Login {

	/**
	 * Main scene for the app. Displays the login scene.
	 * 
	 * @return The GridPane that will hold everything in the scene.
	 */
	public static GridPane loginMenu() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Library Managment System");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Text subTitle = new Text("Log In");
		subTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		grid.add(subTitle, 0, 1, 2, 1);

		final ComboBox<String> userComboBox = new ComboBox<String>();
		userComboBox.getItems().addAll("Admin", "Librarian");
		userComboBox.setPromptText("Select user type...");
		grid.add(userComboBox, 0, 4);

		Label userID = new Label("User ID:");
		grid.add(userID, 0, 2);

		TextField idTextField = new TextField();
		grid.add(idTextField, 1, 2);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 3);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 3);

		Button loginBtn = new Button("Login");
		HBox hbBtn = new HBox();
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().add(loginBtn);
		grid.add(hbBtn, 0, 6);

		loginBtn.setOnAction(
				e -> loginButton(idTextField.getText(), pwBox.getText(), userComboBox.getValue(), grid, loginBtn));

		Button backButton = new Button("Back to Search Menu");
		HBox backBtn = new HBox();
		backBtn.setAlignment(Pos.BOTTOM_RIGHT);
		backBtn.getChildren().add(backButton);
		grid.add(backBtn, 1, 6);

		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Stage primaryStage = (Stage) backButton.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlViews/StudentView.fxml"));
				Parent root;
				try {
					root = loader.load();

					StudentViewController viewCtrl = loader.getController();
					Scene studentScene = new Scene(root, 1080, 720);

					// Pass a reference to the created student scene to the Student View Controller
					viewCtrl.setCurrentScene(studentScene);

					primaryStage.setResizable(false);
					primaryStage.setTitle("Library Application | Student View");
					primaryStage.setScene(studentScene);
					primaryStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		return grid;
	}

	/**
	 * Event handler method that logs in the user to the system if their credentials
	 * are in the system.
	 * 
	 * @param id       ID number of the user attempting to log in.
	 * @param password password linked to the ID number of the user logging in.
	 * @param userType The user can either be a librarian or an admin.
	 * @param grid     GridPane used to display information onto a new window.
	 * @param btn      Button used to set the scene.
	 */
	private static void loginButton(String id, String password, String userType, GridPane grid, Button btn) {

		try {
			int idNum = Integer.parseInt(id);

			if (userType == null) {
				Helper.showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Login Error!",
						"No user type selected.");
			} else if (userType.equalsIgnoreCase("Admin")) {
				try {
					ResultSet resultSet = LoginAdminDBController.adminLogin(idNum, password);

					if (resultSet.next()) {
						btn.getScene().setRoot(Admin.adminMenu());
					} else {
						Helper.showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Login Error!",
								"No Administrator account exists with this ID or password");
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (userType.equalsIgnoreCase("Librarian")) {
				try {
					ResultSet resultSet = LoginAdminDBController.librarianLogin(idNum, password);

					if (resultSet.next()) {
						try {
							btn.getScene().setRoot(Librarian_Main_FX.LibrarianMenu());
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} else {
						Helper.showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Login Error!",
								"No Librarian account exists with this ID or password");
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} catch (NumberFormatException e) {
			Helper.showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Login Error!",
					"Please enter a Number for ID.");
		}
	}
}
