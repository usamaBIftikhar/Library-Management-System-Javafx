package application.librarian;
import application.Login;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Librarian_Main_FX{

	/**
	 * This method is the control method which will create the main page of the<br>
	 * librarian once they log in. There are four buttons created in this method<br>
	 * which will allow the librarian to do the following: <br>
	 * 1. View/Generate file<br>
	 * 2. Add New Item<br> 
	 * 3. Issue Item<br> 
	 * 4. Return Item<br> 
	 * 5. Log Out. 
	 * @return VBox: Back to the override start method.
	 * @throws Exception
	 * 
	 * This method also is using try catch method for all the setOnActions for each<br>
	 * buttons so that it can handle exceptions properly for each action and execution.
	 */
	public static VBox LibrarianMenu() throws Exception 
	{
	
		// Main Buttons Start Here
		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		
		Text scenetitle = new Text("Librarian Menu");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		
		
		// Creates button and switch scene using the root method to view books in the system.
		final Button ViewItem = new Button("View Items");
		ViewItem.setMaxWidth(250);
		ViewItem.setMaxHeight(40);
		
		ViewItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					ViewItem.getScene().setRoot(Librarian_ViewItems_FX.ViewBook());
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			}
			
		});
		
		
		// Creates button and switch scene using root method to add a book to the system.
		final Button AddItem = new Button("Add Items");
		AddItem.setMaxWidth(250);
		AddItem.setMaxHeight(40);
		AddItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					AddItem.getScene().setRoot(AddItem_FX.AddItem());
				} catch (Exception e) {

					e.printStackTrace();
				}
				
			}
			
		});
	
			
		
		// Creates button and switch scene using the root method to issue a book from the system.
		final Button IssueItem = new Button("Issue Items");
		IssueItem.setMaxWidth(250);
		IssueItem.setMaxHeight(40);
		
		IssueItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					IssueItem.getScene().setRoot(Librarian_IssueItem_FX.IssueItem());
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			}
			
		});
		
		
		
		// Creates a button and switch scene using the root method to return books to the system.
		final Button ReturnItem = new Button("Return Items");
		ReturnItem.setMaxWidth(250);
		ReturnItem.setMaxHeight(40);
		
		ReturnItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					ReturnItem.getScene().setRoot(Librarian_ReturnItem_FX.ReturnItem());
				} catch (Exception e) {
				
					e.printStackTrace();
				}
				
			}
			
		});
		
		
		final Button WaitingList = new Button("View Waiting List");
		WaitingList.setMaxWidth(250);
		WaitingList.setMaxHeight(40);
		
		WaitingList.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					WaitingList.getScene().setRoot(Librarian_WaitingList_FX.ViewWaiting());
				} catch (Exception e) {
				
					e.printStackTrace();
				}
				
			}
			
		});
		
		
		
		// Creates a button and switch scene using the root method to return to the log in scene.
		Button Logout = new Button("Logout");
		Logout.setMaxWidth(70);
		Logout.setMaxHeight(40);
		
		Logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					Logout.getScene().setRoot(Login.loginMenu());
				} catch (Exception e) {
				
					e.printStackTrace();
				}
				
			}
			
		});
		
		
		// Adds all methods to the VBox as a children.
		vbox.getChildren().addAll(scenetitle, ViewItem, WaitingList ,AddItem, IssueItem, ReturnItem, Logout);
		
		// Returns back the VBox with all children added to the PrimaryStageControl class.
		return vbox;

	}
}
