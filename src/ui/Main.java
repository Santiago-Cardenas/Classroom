package ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
	@FXML
	private Pane mainPane= new Pane();
	
	private ClassroomGUI classroomgui;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main_Window.fxml"));
		classroomgui= new ClassroomGUI();
		fxmlLoader.setController(classroomgui);
		Parent root= fxmlLoader.load();
		Scene scene = new Scene(root);
		
		classroomgui.setMainStage(primaryStage);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Classroom");
		primaryStage.show();
		classroomgui.showLogin(mainPane);
		
	}
}

