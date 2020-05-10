package Heelo;

import java.beans.EventHandler;

import javax.swing.JFrame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Trait extends Application {
public BorderPane p2;
public Button b;
public    HBox  h;
public imgsupport aff;
	
	public Trait() {
		
		p2 = new BorderPane();	
	    
		h = new HBox();
		
		h.setStyle("-fx-background-color:Black;");
		b = new Button("Home");
		b.setStyle("-fx-background-color:white; -fx-border-style: solid; -fx-font-size:20px;");
     
		aff = new imgsupport();
      	
		h.getChildren().add(b);

	

		p2.setCenter(aff.scrollPane);
		
	       
       p2.setBottom(h);
       
       
       
       
       
	}
	

	


	@Override
	public void start(Stage st1) throws Exception {
        
		
		Scene s2 = new Scene(p2,1500,780,Color.GREEN);     
		
		st1.setFullScreen(true);
		st1.setScene(s2);
		st1.show();
		
	}




	

}
