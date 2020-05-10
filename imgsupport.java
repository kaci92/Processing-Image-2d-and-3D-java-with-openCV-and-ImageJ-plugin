package Heelo;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.sun.prism.image.ViewPort;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.EllipseBuilder;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class imgsupport extends Application {

	//public GridPane pan_centre;
	 public ScrollPane scrollPane,scrollPane1;
	public ImageView supportimage, supportimage1, supportimage2, supportimage3,supportimage4;
	public GridPane grille;
	 
	public VBox h1,h2,h3,h4;
	public imgsupport()
	{
	     grille = new GridPane();
		
	     
	     h1 = new VBox();
	     h2 = new VBox();
	     h3 = new VBox();
	     h4 = new VBox();
	     
	     h1.setPrefSize(400,600);
	     h2.setPrefSize(400,600);
	     h3.setPrefSize(400,600);
	     h4.setPrefSize(400,600);
	     
	     
	    supportimage = new  ImageView(); 
		supportimage.setId("sup");
		supportimage1 = new  ImageView();
		supportimage1.setId("sup1");
	
		supportimage2 = new  ImageView();
		
		supportimage2.setId("sup2");
		supportimage3 = new  ImageView(); 
		supportimage3.setId("sup3");
		
		supportimage4 = new ImageView();
		
		supportimage1.setFitWidth(400);
 	    supportimage1.setFitHeight(400);
 	    supportimage1.setPreserveRatio(true);
	
 	   supportimage2.setFitWidth(400);
	    supportimage2.setFitHeight(400);
	    supportimage2.setPreserveRatio(true);
	    
	    supportimage3.setFitWidth(400);
 	    supportimage3.setFitHeight(400);
 	    supportimage3.setPreserveRatio(true);
	
 	    
 	    
 	    supportimage4.setFitWidth(400);
	    supportimage4.setFitHeight(400);
	    supportimage4.setPreserveRatio(true);
		
 	    
 	    
 	    supportimage.setFitWidth(500);
 	    supportimage.setFitHeight(500);
 	    supportimage.setPreserveRatio(true);
             
              
 	 grille.add(supportimage, 2,0, 1, 1);
 	grille.add(supportimage1, 1, 0, 1, 1);
 	grille.add(supportimage2, 2, 0, 1, 1);
 	grille.add(supportimage3, 3, 0, 1, 1);

	grille.add(supportimage4, 4, 0, 1, 1);
 	grille.add(h1,4,1,1,1);
 	grille.add(h2,3,1,1,1);
 	grille.add(h3,2,1,1,1);
 	grille.add(h4,1,1,1,1);
 	
 	
 	
 	
 	scrollPane = new ScrollPane();
 	   scrollPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);

 	scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
 	   scrollPane.setFitToHeight(true);
 	   scrollPane.setPannable(true);
 	   
 	    scrollPane.setContent(grille);
		
 	 
 
 	   
		
	}
	
	
	@Override
	public void start(Stage st3) throws Exception {
		
		Scene s4 = new Scene(scrollPane1,300,300,Color.CORAL);
		s4.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		st3.setFullScreen(true);
		st3.setScene(s4);
		st3.show();
		
		
	}

}
