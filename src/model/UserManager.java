package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

public class UserManager {
	public static Image profilePhoto;
	private List<User> users;

	public UserManager() {
		users = new ArrayList <User>();
	}

	public List<User> getUsers() {
		return users;
	}
	
	public void  add(User newContact) {
		users.add(newContact);
	}
	
	public boolean accountLogIn(String username,String password) {
		boolean found=false;
		boolean incorrect=false;
			for(int i=0; i<users.size();i++) {
				if(username.equals(users.get(i).getUsername())) {
		    		if(password.equals(users.get(i).getPassword())) {
		    			found=true;
		    			incorrect=true;
		    			Alert alert = new Alert(AlertType.INFORMATION);
				    	alert.setTitle("Information Dialog");
			    		alert.setHeaderText(null);
			    		alert.setContentText("Welcome!");

			    		alert.showAndWait();
		    		}
		    		else {
		    			Alert alert = new Alert(AlertType.WARNING);
				    	alert.setTitle("Information Dialog");
			    		alert.setHeaderText(null);
			    		alert.setContentText("Incorrect password");

			    		alert.showAndWait();
			    		incorrect=true;
		    		}
				}
			}
			
		if(incorrect==false) {
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Information Dialog");
    		alert.setHeaderText(null);
    		alert.setContentText("Incorrect username");

    		alert.showAndWait();
		}
		return found;
	}

	public int userPos(String username,String password) {
		int pos=-1;
		for(int i=0; i<users.size();i++) {
			if(username.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword())) {
				pos=i;
			}
		}
		return pos;
		
	}
	
}
