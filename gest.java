package Heelo;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class gest extends Application {
	
	
	public Button b1,b2,b3;
	public GridPane gp;
	public ChoiceBox ch;
    public ImageView aperçu;
	
     public TextField num,Nom,Prenom;
	public gest()
	{
		b1 = new Button("Home");
		 b1.setStyle("-fx-background-color:white; -fx-border-style: solid; -fx-font-size:20px;");
       
          gp = new GridPane(); 
          gp.setAlignment(Pos.CENTER);
          gp.setHgap(10);
          gp.setVgap(10);
          gp.setStyle("-fx-background-color:white;");
          gp.setPadding(new Insets(25, 25, 25, 25));
        
        b2 = new Button(); 
          b2.setText("OK");
          b2.setStyle("-fx-background-color:white; -fx-border-style: solid; -fx-font-size:20px;");
      
          b3 = new Button(); 
          b3.setText("Connection");
          b3.setStyle("-fx-background-color:white; -fx-border-style: solid; -fx-font-size:20px;");
      
        aperçu = new ImageView();
        aperçu.setFitWidth(150);
        aperçu.setFitHeight(150);
        aperçu.setPreserveRatio(true);
        
        num = new TextField();
        num.setPromptText("Entrez L'Id du  Patient");
        Nom = new TextField();
       Nom.setPromptText("Entrez nom du Patient");
        Prenom = new TextField();
        Prenom.setPromptText("Entrez le prénom du Patient");
  
          
        ch = new ChoiceBox();
        ch.setPrefSize(200, 50);
        ch.setItems(FXCollections.observableArrayList("PBM","PMG","BitMAP"));
        gp.add(num, 0, 2);
        gp.add(Nom, 0, 3);
        gp.add(Prenom, 0, 4);
        gp.add(ch, 0, 5);
        
        gp.add(aperçu, 5, 2);
  
        gp.add(b2, 0,10);
        gp.add(b1, 0, 11);

        gp.add(b3, 3,12);
    
	}

	@Override
	public void start(Stage st2) throws Exception {
		
	Scene s3 = new Scene(gp,1500,780,Color.GREEN);     
		
		st2.setFullScreen(true);
		st2.setScene(s3);
		st2.show();
	}

}
