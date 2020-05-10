package Heelo;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import ij.plugin.DICOM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;

public class Filtrage {

	public Mat mat;
	public Mat mat1;
	public int  ligne,colonne,type;
	public BufferedImage src;
	public File file;
	public DICOM dcm;
	public WritableImage resultat ;
	
	
	public Filtrage() {
	
		
	}
	
	
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
    	  
   Imgproc.threshold(mat, mat1, Double.parseDouble(seuil.getText().toString()), Double.parseDouble(seuil2.getText().toString()) ,Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C);
   
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
    public WritableImage getFiltrageGaussien()
    {
    	Dialog d1 = new Dialog();
   	 
    	TextField sigma = new TextField();
    	sigma.setPromptText("Sigma 0.0 ");
    	GridPane gp2 = new GridPane();
    	gp2.add(sigma, 1, 2); 
    	
   	    d1.getDialogPane().setContent(gp2);	
	    ButtonType buttonTypeOk = new ButtonType("valider", ButtonData.OK_DONE);
	    d1.getDialogPane().getButtonTypes().add(buttonTypeOk);

	    Optional result = d1.showAndWait();	
    	
        byte [] data =  ((DataBufferByte)src.getRaster().getDataBuffer()).getData();
        
         mat = new Mat(src.getHeight(), src.getWidth(), CvType.CV_8UC3);
         mat.put(1,2, data);

        mat1 = new Mat(src.getHeight(),src.getWidth(),CvType.CV_8UC3);
        if(result.isPresent()){
        
        	
        	Imgproc.GaussianBlur(mat, mat1, new Size(45,45),Double.parseDouble(sigma.getText()));
     
         byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
         mat1.get(0, 0, data1);
         BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(),5 );
         image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
     resultat =  SwingFXUtils.toFXImage(image1, null);
     }
    	return resultat;
    } 
      
    public WritableImage getMoyanneur()
    {
    	
    	byte [] data =  ((DataBufferByte)src.getRaster().getDataBuffer()).getData();
        
        
        mat = new Mat(getImageLargeur(src),getImageHauteur(src) ,  CvType.CV_8UC3);
      mat.put(1,2, data);

        mat1 = new Mat(getImageLargeur(src),getImageHauteur(src), CvType.CV_8UC3);
      
        Imgproc.blur(mat, mat1, new Size(45,45), new Point(20,30), Core.BORDER_DEFAULT);				       
        byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
        mat1.get(0, 0, data1);
        BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(),5 );
        image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
       resultat =  SwingFXUtils.toFXImage(image1, null);
       
		return resultat;
    	
    }
    
    public WritableImage getEgalisateurHist()
    {
    	
    	byte [] data =  ((DataBufferByte)src.getRaster().getDataBuffer()).getData();
        
        
        mat = new Mat(getImageLargeur(src),getImageHauteur(src) ,  CvType.CV_8UC3);
        mat.put(1,2, data);

        mat1 = new Mat(getImageLargeur(src),getImageHauteur(src), CvType.CV_8UC3);
         Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY); 
        Imgproc.equalizeHist(mat1, mat1);				       
        byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
        mat1.get(0, 0, data1);
        BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(),BufferedImage.TYPE_BYTE_GRAY);
        image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
       resultat =  SwingFXUtils.toFXImage(image1, null);
		return resultat;
    	
    }

    public WritableImage getMedian()
    {
    	
        byte [] data =  ((DataBufferByte)src.getRaster().getDataBuffer()).getData();
         mat = new Mat(src.getHeight(), src.getWidth(),  CvType.CV_8UC3);
         mat.put(1,2, data);

         mat1 = new Mat(src.getHeight(),src.getWidth(), CvType.CV_8UC3);

         Imgproc.medianBlur(mat, mat1, 15);
         
         byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
         mat1.get(0, 0, data1);
         BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(),5 );
         image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

         return resultat =  SwingFXUtils.toFXImage(image1, null);
      
    	
    }


    public WritableImage getFourier(){
    	
    	
        byte [] data =  ((DataBufferByte)src.getRaster().getDataBuffer()).getData();
        
        mat = new Mat(src.getHeight(), src.getWidth(),  CvType.CV_8UC3);
        
        mat.put(1,2, data);

        mat1 = new Mat(src.getHeight(),src.getWidth(), CvType.CV_8UC3);
ArrayList<Mat> mv = new ArrayList<Mat>();
Core.split(mat, mv);
       byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
        mat1.put(0, 0, data1);
        BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(),5);
        image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

        return resultat =  SwingFXUtils.toFXImage(image1, null);
		
		}
    public WritableImage getLaplacien()
    { 
    		byte [] data =  ((DataBufferByte)src.getRaster().getDataBuffer()).getData();
            mat = new Mat(src.getHeight(), src.getWidth(),  CvType.CV_16S);
    	 	mat.put(1,2, data);
    	 	mat1 = new Mat(src.getHeight(),src.getWidth(),CvType.CV_16S);
    	 	Imgproc.Laplacian(mat, mat1, 3);

    	 byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
    	 mat1.put(0, 0, data1);            
            BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(),5);
            
             image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

            
        return resultat =  SwingFXUtils.toFXImage(image1, null);
    	
    } 
    public WritableImage getBinaire()
    {
  

	        byte [] data =  ((DataBufferByte)src.getRaster().getDataBuffer()).getData();
	         
	        
	      mat = new Mat(src.getHeight(), src.getWidth(), CvType.CV_8UC3);
	       mat.put(1,2, data);

	        mat1 = new Mat(src.getHeight(),src.getWidth(),CvType.CV_8UC3);
	        Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);
	      	 
	    byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
	      
	         mat1.get(0, 0, data1);
	         
	      BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(),  BufferedImage.TYPE_BYTE_GRAY );
	         image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
	     
	          
	        
	         resultat =  SwingFXUtils.toFXImage(image1, null);
	     	
    		return resultat; 
		}

	 /* public WritableImage getSplit()
    {  byte [] data =  ((DataBufferByte)src.getRaster().getDataBuffer()).getData();
           
           mat = new Mat(src.getHeight(), src.getWidth(),  CvType.CV_8UC3);
           mat.put(0,0, data);
 
        // split image to three channels
       	ArrayList<Mat> bgr = new ArrayList<>();
       	Core.split(mat, bgr);
       	Mat bChannel = bgr.get(0);
       	Mat gChannel = bgr.get(1);
       	Mat rChannel = bgr.get(2);
       	byte[] data1 = new byte[gChannel.rows() * gChannel.cols() * (int)(gChannel.elemSize())];
        gChannel.get(0,0, data);
                 
         
           BufferedImage image1 = new BufferedImage(gChannel.cols(),gChannel.rows(),5);
           image1.getRaster().setDataElements(0, 0, gChannel.cols(),gChannel.rows(), data1);
    	   
    	
    	return resultat =  SwingFXUtils.toFXImage(image1, null);
    }*/
    
}
