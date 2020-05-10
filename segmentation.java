package Heelo;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import ij.plugin.DICOM;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;

public class segmentation {
	public Mat mat;
	public Mat mat1;
	public int  ligne,colonne,type;
	public BufferedImage src;
	public File file;
	public DICOM dcm;
	public WritableImage resultat ;
	
	public segmentation()
	{}
	
	public Mat getMatSource()
	{
		
		
		return mat;
	}
	public Mat getMatResultat()
	{
		
		return mat1;
	}
	public void getLib()
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
	}
	public int getImageLargeur(BufferedImage src)
	{
		return src.getHeight();
	}
	public int getImageHauteur(BufferedImage src)
	{
		return src.getWidth();
	}
	public int getImageType()
	{
		return CvType.CV_8UC3;
	}
  public  void Setmatrice(int ligne, int colonne,int type)
    {
    	this.ligne = src.getHeight();
    	this.colonne = src.getWidth();
    	this.type = src.getType();
    	
    }
  public WritableImage getSeuillage() throws IOException
  {	

  	Dialog<Object> d1 = new Dialog<Object>();
 	 
  	TextField seuil = new TextField();
  	seuil.setPromptText(" seuilMin 0.0");
  	TextField seuil2 = new TextField();
  	seuil2.setPromptText(" seuilMax 0.0");
  	
  	 
  	GridPane gp2 = new GridPane();
  	gp2.add(seuil, 1, 0); 
  	gp2.add(seuil2, 1, 1); 
      d1.getDialogPane().setContent(gp2);	
	    ButtonType buttonTypeOk = new ButtonType("valider", ButtonData.OK_DONE);
	    d1.getDialogPane().getButtonTypes().add(buttonTypeOk);

	    Optional<Object> result = d1.showAndWait();	
  	
  	
  byte [] data =  ((DataBufferByte)src.getRaster().getDataBuffer()).getData();
   
  
    mat = new Mat(src.getHeight(), src.getWidth(), CvType.CV_8UC3);
   mat.put(1,2, data);

    mat1 = new Mat(src.getHeight(),src.getWidth(),CvType.CV_8UC3);
  
    if(result.isPresent()){
  	  
 Imgproc.threshold(mat, mat1, Double.parseDouble(seuil.getText().toString()), Double.parseDouble(seuil2.getText().toString()) ,Imgproc.THRESH_BINARY);
 
  // Imgproc.watershed(mat, mat1);
//Imgproc.equalizeHist(mat, mat1);
  
   byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
   mat1.get(0, 0, data1);
   BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(),5 );
   image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

   
  
  resultat = SwingFXUtils.toFXImage(image1, null);
    }
		return  resultat;
  	
  }
  public WritableImage getConteur()
  {
  	
  	Dialog<Object> d1 = new Dialog<Object>();
  	 
  	TextField seuil = new TextField();
  	seuil.setPromptText(" seuil 0.0");
  	TextField seuil2 = new TextField();
  	seuil2.setPromptText(" seuil 0.0");
  	
  	 
  	GridPane gp2 = new GridPane();
  	gp2.add(seuil, 1, 0); 
  	gp2.add(seuil2, 1, 1); 
      d1.getDialogPane().setContent(gp2);	
	    ButtonType buttonTypeOk = new ButtonType("valider", ButtonData.OK_DONE);
	    d1.getDialogPane().getButtonTypes().add(buttonTypeOk);

	    Optional<Object> result = d1.showAndWait();	
  	
  	
  

	        byte [] data =  ((DataBufferByte)src.getRaster().getDataBuffer()).getData();
	         
	        
	      mat = new Mat(src.getHeight(), src.getWidth(), CvType.CV_8UC3);
	       mat.put(1,2, data);

	        mat1 = new Mat(src.getHeight(),src.getWidth(),CvType.CV_8UC3);
	        
	      if(result.isPresent()){

	    	  //Imgproc.Canny(mat, mat1, 0.2, 0.2);       
	      //Imgproc.Canny(mat, mat1,Double.parseDouble(seuil.getText()), Double.parseDouble(seuil2.getText()), Integer.parseInt(seuil3.getText()), cmb.getValue().equals("false"));
	    Imgproc.Canny(mat, mat1,Double.parseDouble(seuil.getText()), Double.parseDouble(seuil2.getText()));
	       byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
	      
	         mat1.get(0, 0, data1);
	         
	      BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(),  BufferedImage.TYPE_BYTE_GRAY );
	         image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
	     
	          
	        
	         resultat =  SwingFXUtils.toFXImage(image1, null);
	     	
  }
		return resultat; 
		}

}
