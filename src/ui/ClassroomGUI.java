package ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;
import model.UserManager;

public class ClassroomGUI {

	//Register Window
		
	  @FXML
	    private TextField txt_NewUsername;

	    @FXML
	    private TextField txt_NewPassword;

	    @FXML
	    private Button backToLogin;

	    @FXML
	    private Button createAcc;

	    @FXML
	    private MenuItem browser_Chrome;

	    @FXML
	    private MenuItem browser_Firefox;

	    @FXML
	    private MenuItem browser_Explorer;

	    @FXML
	    private TextField txt_ProfilePhotoDIR;

	    @FXML
	    private RadioButton Male;

	    @FXML
	    private ToggleGroup genderOption;

	    @FXML
	    private RadioButton Female;

	    @FXML
	    private RadioButton Other;

	    @FXML
	    private RadioButton bt_CareerSoftware;

	    @FXML
	    private ToggleGroup careerOption;

	    @FXML
	    private RadioButton bt_CareerTelematic;

	    @FXML
	    private RadioButton bt_CareerIndustrial;

	    @FXML
	    private DatePicker date_Birthday;
	    
	    @FXML
	    private SplitMenuButton browserOption;
	    

	    @FXML
	    private Pane createAccountPane;
	    
	    // Main Window
	    
	    @FXML
	    private TextField txt_Username;

	    @FXML
	    private TextField txt_Password;
	    
	    // User List
	    
	    @FXML
	    private Label lbl_userId;

	    @FXML
	    private Button btm_LogOut;

	    @FXML
	    private ImageView profile_Photo;
	    
	    @FXML
	    private TableView<User> tableView;
	    
	    @FXML
	    private TableColumn<User, String> userTC;

	    @FXML
	    private TableColumn<User, String> genderTC;

	    @FXML
	    private TableColumn<User, String> carrerTC;

	    @FXML
	    private TableColumn<User, String> birthdayTC;

	    @FXML
	    private TableColumn<User, String> browserTC;
	    
	    private ObservableList<User> observableList;
	    
	    private UserManager userManager;
	    
	    private Stage mainStage;
	    
	    private String browser;

	    public Stage getMainStage() {
			return mainStage;
		}
	    
	    public void setMainStage(Stage mainStage) {
			this.mainStage = mainStage;
		}
	    
	    public ClassroomGUI() {
			userManager = new UserManager();
		}
	    
	    private void initializeTableView() {
			observableList = FXCollections.observableArrayList(userManager.getUsers());
			
			tableView.setItems(observableList);
			userTC.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
			genderTC.setCellValueFactory(new PropertyValueFactory<User,String>("gender"));
			carrerTC.setCellValueFactory(new PropertyValueFactory<User,String>("career"));
			birthdayTC.setCellValueFactory(new PropertyValueFactory<User,String>("birthday"));
			browserTC.setCellValueFactory(new PropertyValueFactory<User,String>("browser"));
		}
	    
	    @FXML
	    void createAccount(ActionEvent event) throws IOException {
	    	String gender;
	    	String career;
	    	LocalDate bday = date_Birthday.getValue();
	    	if (genderOption.getSelectedToggle().toString().equals("RadioButton[id=Male, styleClass=radio-button]'Male'")) {
	    		gender="Male";
	    	}
	    	else if (genderOption.getSelectedToggle().toString().equals("RadioButton[id=Female, styleClass=radio-button]'Female'")) {
	    		gender="Female";
	    	}
	    	else {
	    		gender="Other";
	    	}
	    	
	    	if (careerOption.getSelectedToggle().toString().equals("RadioButton[id=bt_CareerSoftware")) {
	    		career="Software Engineering";
	    	}
	    	else if (careerOption.getSelectedToggle().toString().equals("RadioButton[id=bt_CareerTelematic")) {
	    		career="Telematic Engineering";
	    	}
	    	else {
	    		career="Industrial Engineering";
	    	}
	    	
	    	User user = new User (txt_NewUsername.getText(),txt_NewPassword.getText(), gender,career, bday.toString() , browser ,UserManager.profilePhoto);
	    	
	    	userManager.add(user);
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Information Dialog");
    		alert.setHeaderText(null);
    		alert.setContentText("Account succesfully created");

    		alert.showAndWait();
    		
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main_Window.fxml"));
			fxmlLoader.setController(this);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);

			mainStage.setScene(scene);
			mainStage.setTitle("Classroom");
			mainStage.show();
	    }
   
	    @FXML
	    void searchForPhoto(ActionEvent event) {
	    	FileChooser fc = new FileChooser();
	    	fc.setTitle("Select image");
	    	fc.getExtensionFilters().addAll(
	    		new FileChooser.ExtensionFilter("PNG", "*.png"),
	    		new FileChooser.ExtensionFilter("JPG", "*.jpg")
	    	
	    	);
	    	Stage stage = (Stage)createAccountPane.getScene().getWindow();
	    	File photo = fc.showOpenDialog(stage);
	    	if(photo!=null) {
	    		txt_ProfilePhotoDIR.setText(photo.getAbsolutePath());
	            UserManager.profilePhoto = new Image ("file:"+photo.getAbsolutePath());

	        }
	    }

		@FXML
		void chromeBrowser(ActionEvent event) {
			browserOption.setText("Google Chrome");
			browser="Google Chrome";
		}

		@FXML
		void explorerBrowser(ActionEvent event) {
			browserOption.setText("Windows Explorer");
			browser="Windows Explorer";
		}

		@FXML
		void firefoxBrowser(ActionEvent event) {
			browserOption.setText("Mozilla Firefox");
			browser="Mozilla Firefox";
		}
	    
	    @FXML
	    void goTo_Log_in(ActionEvent event) throws IOException {
	    	int pos;
	    	if(txt_Username.getText().equals("") && txt_Password.getText().equals("")) {
	    		Alert alert = new Alert(AlertType.WARNING);
		    	alert.setTitle("Information Dialog");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Fill in the paramaters");

	    		alert.showAndWait();
	    	}
	    	else if(userManager.accountLogIn(txt_Username.getText(),txt_Password.getText())) {
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserList.fxml"));
			fxmlLoader.setController(this);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			
			mainStage.setScene(scene);
			mainStage.setTitle("Classroom");
			mainStage.show();
			
			initializeTableView();
			lbl_userId.setText(txt_Username.getText());
			pos=userManager.userPos(txt_Username.getText(),txt_Password.getText());
			profile_Photo.setImage(userManager.getUsers().get(pos).getPhotoDir());
	    	}
	    }

	    @FXML
	    void goTo_Sing_up(ActionEvent event) throws IOException {
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterWindow.fxml"));
			fxmlLoader.setController(this);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);

			mainStage.setScene(scene);
			mainStage.setTitle("Classroom");
			mainStage.show();
	    	
	    }
	    
	    @FXML
	    void log_Out(ActionEvent event) throws IOException {
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main_Window.fxml"));
			fxmlLoader.setController(this);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);

			mainStage.setScene(scene);
			mainStage.setTitle("Classroom");
			mainStage.show();
	    }
	    
	    @FXML
	    void goBackToSingIn(ActionEvent event) throws IOException {
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main_Window.fxml"));
			fxmlLoader.setController(this);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);

			mainStage.setScene(scene);
			mainStage.setTitle("Classroom");
			mainStage.show();
	    }
	    
	    public void showLogin(Pane mainPane) throws IOException {
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main_Window.fxml"));
			fxmlLoader.setController(this);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);

			mainStage.setScene(scene);
			mainStage.setTitle("Classroom");
			mainStage.show();
	    }
	    

}
