package Heelo;


import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.util.Optional;

import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;

public class Recalag {
	
	public Mat mat;
	public Mat mat1;
	public int  ligne,colonne,type;
	public BufferedImage src;
	public File file;
	public WritableImage resultat ;
	public TextField pointx , pointy ,  angle , echelle , dimx ,   dimy ;


 public Recalag() {
	
  }

	public void getLib()
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	  
	}
	
	public WritableImage getRecalage(){
		   pointx = new TextField();
		  pointx.setPromptText("Point x");
		   pointy = new TextField();
		   pointy.setPromptText("Point Y");
		   angle = new TextField();
		   angle.setPromptText("Angle");
		   echelle = new TextField();
		   echelle.setPromptText("Echelle");
		   dimx = new TextField();
		   dimx.setPromptText("Dimension x");
		   dimy = new TextField();
		   dimy.setPromptText("Dimension y");
		   
		   Dialog<Object>   dialog = new Dialog<Object>();
			dialog.setTitle("Paramètres de recalage");
			dialog.setHeaderText("Entrez un point de recalage, un angle et un echelle et la dimenssion de recalage");
        GridPane gp = new GridPane();
        
        
		 gp.add(pointx, 2, 0);
		 gp.add(pointy, 2, 1);
		 gp.add(angle, 2, 2);
		 gp.add(dimx, 2, 3);
		 gp.add(dimy, 2, 4);
		 gp.add(echelle, 2, 5);
		  	

		dialog.getDialogPane().setContent(gp);	
		ButtonType buttonTypeOk = new ButtonType("valider", ButtonData.OK_DONE);
		
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

		 Optional<Object> result = dialog.showAndWait();
	
	byte [] data =  ((DataBufferByte)src.getRaster().getDataBuffer()).getData();
    
   
    mat = new Mat(src.getHeight(), src.getWidth(),  CvType.CV_8UC3);
  mat.put(1,2, data);

    mat1 = new Mat(src.getHeight(),src.getWidth(), CvType.CV_8UC3);
    Point p = new Point(Double.parseDouble(pointx.getText()),Double.parseDouble(pointy.getText()));
    
    Mat rotat = Imgproc.getRotationMatrix2D(p, Double.parseDouble(angle.getText()),  Double.parseDouble(echelle.getText()));
   
    Size dimans = new Size(mat.cols()*(Double.parseDouble(dimx.getText())),mat.rows()* (Double.parseDouble(dimy.getText())));
  if(result.isPresent()){
    Imgproc.warpAffine(mat, mat1, rotat, dimans);				       
    byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
    mat1.get(0, 0, data1);
    BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(),5 );
    image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
   resultat =  SwingFXUtils.toFXImage(image1, null);
  }
return resultat;
  }
}
