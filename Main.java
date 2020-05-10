package Heelo;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.HistogramWindow;
import ij.gui.ImageWindow;
import ij.io.FileInfo;
import ij.io.FileOpener;
import ij.io.FileSaver;
import ij.io.SaveDialog;
import ij.plugin.DICOM;
import ij.plugin.Histogram;
import ij.plugin.PlugIn;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application implements PlugIn {
	public ImageView view;
	public BorderPane root,root2;
	public GridPane dimans,gp2;
	public Scene scene,s2;
    public FileInputStream input;
    public File file;
    public   javafx.scene.image.Image img,img2;
    public 	FileOutputStream output;
    public BufferedImage image;
	public Stage primaryStage;
    public WritableImage resultat;
	public Slider sld;
	private ImagePlus mp,mp1;
	private Mat mat, mat1;
	private DICOM dcm;
	private TextField txtid,txtuser;
	
	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	@Override
	public void start(Stage primaryStage) throws IOException {
       
		//les instance
		
			Trait t = new Trait();
			gest gt = new gest();      
			imgsupport sp = new imgsupport();
			appMenu barm = new appMenu();
			onglets ong = new onglets();
			Filtrage flt = new Filtrage();
			segmentation sgm = new segmentation();
			Recalag re = new Recalag();
			DB base = new DB();
			Animation anime = new Animation();
       //fin des instances   
		    	
			
			 primaryStage.setTitle("Traitement D'image");
		 
			
			 primaryStage.getIcons().add(new Image("file:///C:/Users/Youcef/workspace/FXTtry/lib/Icon.png"));
			
		     root = new BorderPane();
		     root2 = new BorderPane();
		     txtid = new TextField();
		     txtid.setPromptText("Entrez votre ID...");
		     txtid.getText();
		     txtid.setStyle("  -fx-prompt-text-fill: gray;");
		     txtuser = new TextField();
		     txtuser.setPromptText("Entrez votre Nom...");
		     txtuser.getText();
		     txtuser.setStyle("  -fx-prompt-text-fill: gray;");
		    
		     HBox box2 = new HBox();
		     
		     gp2 = new GridPane(); 
		     gp2.setAlignment(Pos.CENTER);
	          gp2.setHgap(10);
	          gp2.setVgap(10);
	          //gp2.setStyle("-fx-background-color:white;");
	          gp2.setPadding(new Insets(25, 25, 25, 25));
	              
	          gp2.add(txtid, 0, 1);
	          gp2.add(txtuser, 0, 2);
	          gp2.setStyle("    -fx-background-repeat: stretch;   -fx-background-size: 900 506;-fx-background-position: center center; -fx-effect: dropshadow(one-pass-box, green, 10, 0.5, 0, 0); ");
		     Button animation = new Button("Entrer");
		    animation.setId("anim");
		     //animation.setStyle("");
		     s2 = new Scene(root2,1500,780,Color.ALICEBLUE);
		    
		     //<--deb Animation-->
	
		     
             root2.setId("root2");
		    //
             Text tx = new Text("Traitement Informatique d'imagerie médicales");
			    tx.setFont(Font.font(50));
			    tx.setId("tx");
			    tx.setFill(Color.WHITE);
			    
			    root2.getChildren().add(tx);
				anime.node = tx;
				anime.getCirculaire();
			 
				anime.node = gp2;
				anime.getTransition();
	             gp2.add(animation, 1, 3);
				root2.setCenter(gp2);
					     
             //<--fin Animation-->
		    
		    s2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
          
		    barm.menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		     
		     scene = new Scene(root, 1500, 780, Color.WHITE);
             scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
             root.setTop(barm.menuBar);
         
           
             HBox boxhaut2 = new HBox();
             boxhaut2.setStyle("-fx-background-color:white;"
 		     		+ "-fx-height:50px");
 		     
             
             Button btn = new Button("Parcourir..");
		     //btn.setStyle(" -fx-font-size:30px; -fx-cursor:hand;  -fx-background-color:#0489B1;");
		    
		     root.setCenter(sp.scrollPane);
		     Group gp = new Group();

		     gp.getChildren().addAll(barm.menuBar);
		     
		 		     
		     Button btn0 = new Button();
		     btn0.setText("Traitement");
		     
		     root.setTop(gp);
		     HBox boxhaut = new HBox();
		     boxhaut.setStyle("-fx-background-color:white;"
		     		+ "-fx-height:50px");
		    
		     Button btn2 = new Button();
		     btn2.setText("Traitement");
		    
		   
		     Button btn3 = new Button();
		     btn3.setText("Gestion Base");
              
            btn3.setOnAction(e->
            {
            	primaryStage.getScene().setRoot(gt.gp);
            	
            });
            
            gt.b1.setOnAction(e->
            {
            	primaryStage.getScene().setRoot(root);
            	
            });
		     
		     VBox boxgauche = new VBox(8);
		     //boxgauche.setStyle("-fx-padding:20px; -fx-background-color: radial-gradient(focus-angle 291deg , focus-distance 59% , center 50% 50% , radius 55% , #7cfc00, #ff0000);");
		     boxgauche.getChildren().addAll(btn,btn2,btn3);
             root.setLeft(boxgauche);
		     root.setRight(ong.tabg);
		     Text t1 = new Text("Copyright © 2018");
		    
		     Label lab2 = new Label();
		   
		     HBox boxbas = new HBox();
		     boxbas.setSpacing(10);
		     boxbas.getChildren().addAll(t1,lab2);
		     boxbas.setStyle("-fx-background-color: #92a8d1; -fx-text-fill:black;");  
		    // t1.setFill(clr3.getValue());
		    
            
		     
		     //les action Event
		    
		     boxgauche.setVisible(false);
		
		     
		     barm.item7.setOnAction((event)->
		     {   if(((CheckMenuItem) barm.item7).isSelected()){
		    	 boxgauche.setVisible(true);
		    	 barm.item7.setText("Désactiver");
		     }else
		     {
		    	 barm.item7.setText("Afficher");
		    	 boxgauche.setVisible(false);
		     
		     }
		    	 
		     });
		   
		 
		     //action d'affichage du panneau gestion Patient
		     barm.item11.setOnAction(e2->
		     {
		    	 
		    	 primaryStage.getScene().setRoot(gt.gp);
		    	 
		    	 
		     });
		     barm.item10.setOnAction(e3->
		     {
		    	  primaryStage.getScene().setRoot(t.p2);
                 
		     });
		     t.b.setOnAction(e4->
		     {
		    	 primaryStage.getScene().setRoot(root);
                 
		     });
		    //-fin gestion patient
		     
             //-affichage d'image
		     
		     
		     barm.itemdicom.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {

					 
					FileChooser ch = new FileChooser();
					ch.setTitle("Ouvrir un Fichier DICOM");
					FileChooser.ExtensionFilter exfilter = new FileChooser.ExtensionFilter("All Files ", "*.*");
			       ch.getExtensionFilters().add(exfilter);
			       
			        file = ch.showOpenDialog(primaryStage);
			     try  {
				       input = new FileInputStream(file);
		             
				       if(file!=null)
			       {  
				    dcm = new DICOM(input);
	                dcm.run("Name");
	                //dcm.show();
		             
			       img2 = SwingFXUtils.toFXImage(dcm.getBufferedImage(), null);
				    	 	sp.supportimage.setImage(img2);
				    	    anime.node = sp.supportimage;
				    	    anime.getScaleTran();
				    	  	root.setCenter(sp.scrollPane); 
				    	 
				    }else
				    {
				    	Alert alt = new Alert(AlertType.INFORMATION);
				    	alt.setTitle("Informations");
				    	alt.setHeaderText("DICOM");
				    	alt.setContentText("Il faut ouvrir un fichier Dicom(Digital imaging and communications in medicine)");
				       alt.showAndWait();
				    }
				
			      } catch (  Exception e) {
						e.printStackTrace();
				}
					
				}

					
				});
		     sp.supportimage.setOnDragOver(new EventHandler <DragEvent>(){

				@Override
				public void handle(DragEvent event) {
					Dragboard sb = event.getDragboard();
					if(sb.hasFiles())
					{
						event.acceptTransferModes(TransferMode.ANY);
					}
					event.consume();
				}});
		     
		     
		     sp.scrollPane.setOnDragOver(new EventHandler<DragEvent>() {
		            @Override
		            public void handle(DragEvent event) {
		                Dragboard db = event.getDragboard();
		                if (db.hasFiles()) {
		                    event.acceptTransferModes(TransferMode.COPY);
		                } else {
		                    event.consume();
		                }
		            }
		        });
		        
		        // Dropping over surface
		     sp.scrollPane.setOnDragDropped(new EventHandler<DragEvent>() {
		            @Override
		            public void handle(DragEvent event) {
		                Dragboard db = event.getDragboard();
		                boolean success = false;
		                if (db.hasFiles()) {
		                    success = true;
		                    String filePath = null;
		                    for (File file:db.getFiles()) {
		                        filePath = file.getAbsolutePath();
		                        System.out.println(filePath);
		                    }
		                }
		                event.setDropCompleted(success);
		                event.consume();
		            }
		        });
		     barm.item1.setOnAction(new EventHandler()
		     {
		    
				@Override
				public void handle(Event arg0) {
					 
					FileChooser ch = new FileChooser();
					FileChooser.ExtensionFilter exfilter = new FileChooser.ExtensionFilter("All Files ", "*.*");
			       ch.getExtensionFilters().add(exfilter);
			       
			        file = ch.showOpenDialog(primaryStage);
			     try  {
				       input = new FileInputStream(file);
				       
				       if(file!=null)
			       {
				    	 img = new javafx.scene.image.Image(input);
				    	 	sp.supportimage.setImage(img); 
				    	    anime.node = sp.supportimage;
				    	    anime.getScaleTran();
				    	  	root.setCenter(sp.scrollPane); 
				    	 
				    }
				
			      } catch (  Exception e) {
						e.printStackTrace();
				}
					
				}
 
		     });
		

              HBox histo = new HBox(); 
        
              ong.tab1.setContent(histo);
		     
		     //<!--debut histograme-->
		        barm.sub13.setOnAction(new EventHandler(){

					@Override
					public void handle(Event event) {
						
                   
						if(barm.sub13.isSelected()){	
							
						histogram hist = new histogram(img);
						
						if(hist.Succée()){
						 hist.histodessin();
                      hist.line.getData().add(hist.getluminosite());
						 
						}
					histo.getChildren().add(hist.line);
					}else
					{
						histo.getChildren().clear();
					}
		            }});
		        
		             barm.egaHist.setOnAction(new EventHandler(){

						@Override
						public void handle(Event arg0) {
							
							try {
								flt.getLib();
								image = ImageIO.read(file);
								
								flt.src = image ;
								flt.mat = mat;
						        flt.mat1 = mat1;
						       
						        sp.supportimage.setImage(flt.getEgalisateurHist());
						        histogram hist = new histogram (flt.resultat);
						        
			 			     	if(barm.sub13.isSelected()){
			 						
			 						if(hist.Succée()){
			 							hist.histodessin();
			 							hist.line.getData().addAll(hist.getluminosite());
			 						 
			 						}
			 				t.aff.h4.getChildren().add(hist.line);
			 					}else
			 					{
			 						histo.getChildren().clear();
			 					}
			                 
						        image.flush();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						}});
		       
	 		    //<!--fin histograme--> 
		     
	
		       scene.setOnKeyPressed(new EventHandler<KeyEvent>(){


				@Override
				public void handle(KeyEvent event) {
					if(event.getCode() == KeyCode.Q)
					{
						Platform.exit();
					}
					
				}});
		     
		     
		     //-traitement
		     btn2.setOnAction(e6->
		     { 
		    	 
		    	 primaryStage.getScene().setRoot(t.p2);
		       
		     
		     }
		     );
		     
		
              t.b.setOnAction(e7->
              {
            	 
            	  primaryStage.getScene().setRoot(root);
            	  
              });
	  
		     
		     //-fin traitement
		     

              //traitement seuillage essay
             

 		     barm.sub11.setOnAction(e8->{
 		     
 		    try {
 		    	sgm.getLib();
		      	
		        image = ImageIO.read(file);
		        sgm.src = image;
		         sgm.mat = mat;
		          sgm.mat1 = mat1;
		         sp.supportimage.setImage(sgm.getSeuillage());
			     t.aff.supportimage2.setImage(sgm.getSeuillage());
			    
					if(barm.sub13.isSelected()){	
						
					histogram hist = new histogram(sgm.resultat);
					
					if(hist.Succée()){
					 hist.histodessin();
                  hist.line.getData().add(hist.getluminosite());
					 
					}
				t.aff.h4.getChildren().add(hist.line);
				}else
				{
					histo.getChildren().clear();
				}
		         
		      } catch (Exception e) {
		         System.out.println("Error: " + e.getMessage());
		      }
 		    });
                  
 		
 		     barm.sub12.setOnAction(e->
 		     {
         	 try {
         		 	sgm.getLib();   
         		     image = ImageIO.read(file);
         		    sgm.src = image;
         		    sgm.mat = mat;
                     sgm.mat1 = mat1;
 			         sp.supportimage.setImage(sgm.getConteur());
 			        gt.aperçu.setImage(sgm.getConteur());
 			        t.aff.supportimage1.setImage(sgm.getConteur());
 			       
						if(barm.sub13.isSelected()){	
							
						histogram hist = new histogram(sgm.resultat);
					
						if(hist.Succée()){
						 hist.histodessin();
                    hist.line.getData().add(hist.getluminosite());
						 
						}
					t.aff.h3.getChildren().add(hist.line);
					}else
					{
						histo.getChildren().clear();
					}
                 
 			    
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
 		     });
 		
 		
 		     barm.item15.setOnAction(e9->
 		     {
		          try {
		        	   flt.getLib();
		        	   image = ImageIO.read(file);	
                       flt.src = image;
                       flt.mat = mat;
                       flt.mat1 = mat1;
                       sp.supportimage.setImage(flt.getFiltrageGaussien());
                       gt.aperçu.setImage(flt.getFiltrageGaussien());
				       t.aff.supportimage2.setImage(flt.getFiltrageGaussien());
				       
							if(barm.sub13.isSelected()){	
								
							histogram hist = new histogram(img);
							
							if(hist.Succée()){
							 hist.histodessin();
	                      hist.line.getData().add(hist.getluminosite());
							 
							}
					t.aff.h1.getChildren().add(hist.line);
						}else
						{
							histo.getChildren().clear();
						}	
		          } catch (IOException e1) {
					// 
					e1.printStackTrace();
				}	

		        
 		         
 		     });
 		    //<!--Essay laplacien-->
 		    barm.item16.setOnAction(e->
 		     {
 		    		ImagePlus mp = new ImagePlus(file.toString());
 		    		
 		    		IJ.runPlugIn(mp, "ij.plugin.filter.Convolver", null);
 		    
 		     });
 		     //<!--fin laplacien-->
 		    barm.item6.setOnAction(ev->
 		     {  
 		    	String tt = "Salut, comment vous allez!!";
 				anime.getParole(tt);
 		    	});

 		 //<!--moyaneur-->
 	       barm.item13.setOnAction(e->
              {
            	  anime.getParole("On va appliquez un filtrage Moyanneur sur l'image, qui conssite a calculer la moyenne des pixels de l'image");
            	  try { 
		        	 flt.getLib();
					image = ImageIO.read(file); 
					flt.src = image;
                    flt.mat = mat;
                    flt.mat1 = mat1;
		         sp.supportimage.setImage(flt.getMoyanneur());
		         anime.getParole("Voila le résultat");
            	 
		         t.aff.supportimage3.setImage(flt.getMoyanneur());
		        histogram hist = new histogram(flt.resultat);
		         
     	    	if(barm.sub13.isSelected()){
					if(hist.Succée()){
			   hist.histodessin();
			   
               hist.line.getData().addAll(hist.getluminosite());
					 
					}
			t.aff.h2.getChildren().add(hist.line);
				}else
				{
					histo.getChildren().clear();
				}
            	  
            	  
            	  } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	

		                     });
              //recalage
              barm.sub2.setOnAction(new EventHandler(){

				@Override
				public void handle(Event arg0) {
                 
	            	  try { 
			        	 re.getLib();
						image = ImageIO.read(file); 
				        re.src = image;
				        re.mat = mat;
				        re.mat1 = mat1;
				        
			         sp.supportimage.setImage(re.getRecalage());

		        	 histogram hist = new histogram(re.resultat);
		                  if(hist.Succée()){
			  			   hist.histodessin();
			  			    hist.line.getData().addAll(hist.getluminosite());
			  					 
			  					}
			        	 
			        	t.aff.h1.getChildren().add(hist.line);
			         

			         }
					 catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
	            	  image.flush();
				}});
        
 		barm.item14.setOnAction(e->
 		  {	 try { 
	        	   
	        	     flt.getLib();
	        	    
	        	     image = ImageIO.read(file);
	        	     
	        	     flt.src = image ;
	        	     flt.mat = mat;
	        	     flt.mat1 = mat1;
	        	     sp.supportimage.setImage(flt.getMedian());
	        	     t.aff.supportimage4.setImage(flt.getMedian());
	        
	        	     
	        	    
					histogram hist = new histogram(flt.resultat);
	    		     
	        	    	if(barm.sub13.isSelected()){
						
						if(hist.Succée()){
							hist.histodessin();
	                  hist.line.getData().add(hist.getluminosite());
						 
						}
				t.aff.h1.getChildren().add(hist.line);
					}else
					{
						histo.getChildren().clear();
					}

			} catch (IOException e1) {
				
				e1.printStackTrace();
			}	
 		 image.flush();
 			  
 		  });
 		  
 		     sp.supportimage.setOnMouseMoved(e->
 		     { 
 		       
 		    	
				int y = (int) e.getY(),x = (int) e.getX();
 		    	if(img != null){
				lab2.setWrapText(true);
 		    	lab2.setText("H:"+y+"W:"+ x + "  Résolution : "+img.getHeight()+ " X " +img.getWidth()+"\n Chemain:"+
		    	 		  file.getAbsolutePath()+"\n Nom:"+file.getName()+"dcm: ");
 		    	}
 		    	else
 		    	{
 		    		if(img2!=null)
 		    		{
 		    			lab2.setWrapText(true);
 		 		    	lab2.setText("H:"+y+"W:"+ x + "  Résolution : "+img2.getHeight()+ " X " +img2.getWidth()+"\n Chemain:"+
 				    	 		  file.getAbsolutePath()+"\n Nom:"+file.getName());
 		 		    
 		    		}
 		    	}
 		    	
 		     });
 		 barm.info.setOnAction(e->
 		 {
 			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Informations");
			alert.setHeaderText("Informations Fichier DICOM");
			alert.setContentText(""+dcm.getInfoProperty());							
			alert.showAndWait();
 			 
 		 });
 		   
 		     //rotation
 		     
 		     barm.item8.setOnAction(e8->
 		     {
 		    	  
 		    	try { 
	        	    System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	        	    image = ImageIO.read(file);
	        	    byte [] data =  ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
	                  mat = new Mat(image.getHeight(), image.getWidth(),  CvType.CV_8UC3);
	                 mat.put(1,2, data);

	                  mat1 = new Mat(image.getHeight(),image.getWidth(), CvType.CV_8UC3);
	       
	                 Core.flip(mat, mat1, 1);
	                 
	                 byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
	                 mat1.get(0, 0, data1);
	                 BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(),5 );
	                 image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
	        
	                 resultat =  SwingFXUtils.toFXImage(image1, null);
	                 sp.supportimage.setImage(resultat);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
 		    	image.flush();
          	 
 		    	 
 		     });
 		  barm.item3d.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				
			  mp1 = new ImagePlus(file.toString()); 
			  mp1.getBufferedImage();
	  
			  IJ.run(mp1 , "Interactive 3D Surface Plot", null);
				
			}});
 		  
 		
 		  

 		  barm.item3d1.setOnAction(e->
 		  {
 			  mp = new ImagePlus(file.toString()); 
			  image = mp.getBufferedImage();
		  
			IJ.runPlugIn(mp,"ij.plugin.FFT", null);	
 			  
 		  });
 		  
 		  
 		  
 		  barm.item3d2.setOnAction(e->
 		  {
 			  mp = new ImagePlus(file.toString()); 
			mp.getBufferedImage(); 
		   	 IJ.runPlugIn(mp , "ij.plugin.frame.ColorThresholder", null);
		   	 
		   	
 		  });
 		     //conteur perwitt
 		     barm.sub3.setOnAction(new EventHandler(){

				@Override
				public void handle(Event arg0) {
					
				    try {
	        	    	flt.getLib();
						image = ImageIO.read(file);
                      flt.src = image;
                      flt.mat = mat;
                      flt.mat1 = mat1;
                      sp.supportimage.setImage(flt.getFourier());
                      
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
	 		    	image.flush();
				
					
				}});
 		     
 		     
 		     barm.sub1.setOnAction(new EventHandler()
 		     {

				@Override
				public void handle(Event arg0) {
				
				mp = new ImagePlus(file.toString()); 
				image = mp.getBufferedImage();
				
				IJ.runPlugIn(mp ,"ij.plugin.ChannelSplitter", null);
				   	 
					
				}
 		    	 
 		     });
 		    barm.smallitem1.setOnAction(e->
 		    {
 		    	try {
					image = ImageIO.read(file);
					flt.getLib();
			    	flt.src = image;
			    	flt.mat = mat;
			    	flt.mat1 = mat1;
			       sp.supportimage.setImage(flt.getBinaire());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 		     
 		    });
 		     barm.histsub.setOnAction(e->
 		     {
 		    	String affichagebin = flt.getBinaire().toString();
 		    	 mp = new ImagePlus(affichagebin);	
 		    	 
 		      	IJ.runPlugIn(mp, "sc.fiji.skeletonize3D.Skeletonize3D_", null);
 	 		    	
 	 		  ImageWindow wind = new ImageWindow(mp);

				
 		    	
 		     }
 		     );
 		     
 		     barm.conteur.setOnAction(e->
 		     {
 		        mp = new ImagePlus(file.toString());
 		    	IJ.runPlugIn(mp, "ij.plugin.Histogram", null);
 		    	HistogramWindow histwin = new HistogramWindow(mp);
 		    
 		     });
 		     ong.sld.valueProperty().addListener(new ChangeListener<Number>(){

				@Override
				public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				sp.supportimage.setRotate(arg2.doubleValue());
				
					
				}});
 		 
 		 
 		     t.aff.supportimage1.setOnScroll(new EventHandler<ScrollEvent>(){

			@Override
			public void handle(ScrollEvent event) {
				if(event.getDeltaY()==0){return;}
				
				
				double zoom = 1.1;
				   @SuppressWarnings("unused")
				double scaleFactor =    (event.getDeltaY() > 0) ? zoom : 1/zoom;

				if(event.getDeltaY()>0){
				t.aff.supportimage1.setScaleX(t.aff.supportimage1.getScaleX()*zoom);
			t.aff.supportimage1.setScaleY(t.aff.supportimage1.getScaleY()*zoom);
			
				}else
				{ if(event.getDeltaY()<0){
					t.aff.supportimage1.setScaleX(t.aff.supportimage1.getScaleX()/zoom);
					t.aff.supportimage1.setScaleY(t.aff.supportimage1.getScaleY()/zoom);
				
					
				}
				}
				
			}});
 		   
 		   
 		  t.aff.supportimage2.setOnScroll(new EventHandler<ScrollEvent>(){

 				@Override
 				public void handle(ScrollEvent event) {
 					if(event.getDeltaY()==0){return;}
 					
 					
 					double zoom = 1.1;
 					   double scaleFactor =    (event.getDeltaY() > 0) ? zoom : 1/zoom;

 					if(event.getDeltaY()>0){
 					t.aff.supportimage2.setScaleX(t.aff.supportimage2.getScaleX()*zoom);
 				t.aff.supportimage2.setScaleY(t.aff.supportimage2.getScaleY()*zoom);
 					}else
 					{ if(event.getDeltaY()<0){
 						t.aff.supportimage2.setScaleX(t.aff.supportimage2.getScaleX()/zoom);
 						t.aff.supportimage2.setScaleY(t.aff.supportimage2.getScaleY()/zoom);
 					}
 					}
 					
 				}});
 		   
 		 
 		 t.aff.supportimage3.setOnScroll(new EventHandler<ScrollEvent>(){

 			@Override
 			public void handle(ScrollEvent event) {
 				if(event.getDeltaY()==0){return;}
 				
 				
 				double zoom = 1.1;
 				   double scaleFactor =    (event.getDeltaY() > 0) ? zoom : 1/zoom;

 				if(event.getDeltaY()>0){
 				t.aff.supportimage3.setScaleX(t.aff.supportimage3.getScaleX()*zoom);
 			t.aff.supportimage3.setScaleY(t.aff.supportimage3.getScaleY()*zoom);
 				}else
 				{ if(event.getDeltaY()<0){
 					t.aff.supportimage3.setScaleX(t.aff.supportimage3.getScaleX()/zoom);
 					t.aff.supportimage3.setScaleY(t.aff.supportimage3.getScaleY()/zoom);
 				}
 				}
 				
 			}});
 		   
 		   
 		 
 		
 		     sp.supportimage.setOnScroll(new EventHandler<ScrollEvent>(){

				@Override
				public void handle(ScrollEvent ev) {
					if(ev.getDeltaY()==0){return;}
				
					
					double zoom = 1.1;
					   double scaleFactor =    (ev.getDeltaY() > 0) ? zoom : 1/zoom;

					if(ev.getDeltaY()>0){
					sp.supportimage.setScaleX(sp.supportimage.getScaleX()*zoom);
				sp.supportimage.setScaleY(sp.supportimage.getScaleY()*zoom);
				
					}else
					{ if(ev.getDeltaY()<0){
						sp.supportimage.setScaleX(sp.supportimage.getScaleX()/zoom);
						sp.supportimage.setScaleY(sp.supportimage.getScaleY()/zoom);
						
						
					}
					}
					
				}});
 		  barm.item22.setOnAction(e->
 		  {
 			
            
 	         IJ.run("Save");
 		
 		  });   
 		     
 			barm.sld1.valueProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					sp.supportimage.setScaleX(newValue.floatValue()/2);
					sp.supportimage.setScaleY(newValue.floatValue()/2);
					
				}
			});
 		    
 		    
 		     //slider
 		     ong.sld.valueProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
				{
                    sp.supportimage.setRotate(newValue.doubleValue());
                    
				}
			});
 		    
 		 
              barm.item2.setOnAction(new EventHandler(){

				@Override
				public void handle(Event arg0) {
                     
					 
						FileChooser ch = new FileChooser();
						ch.setTitle("Enregistrement d'image");
						FileChooser.ExtensionFilter exfilter = new FileChooser.ExtensionFilter("All Files ", "*.*");
						   ch.getExtensionFilters().add(exfilter);
				        file = ch.showSaveDialog(primaryStage);
						if(file != null){
							try {
								ImageIO.write(SwingFXUtils.fromFXImage(img ,null) , "png", file);
									
							ImageIO.write( SwingFXUtils.fromFXImage(flt.resultat ,null), "png", file);
							ImageIO.write( SwingFXUtils.fromFXImage(re.resultat ,null), "png", file);
							
							}catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 
					}					
					
								
				}});
        
              gt.b3.setOnAction(e->
              {
            	  if(base.conn!=null){
            		base.CreerTable();
            	  JOptionPane.showMessageDialog(null, "Creer Avec succée!!");
            	  }
            	  else
            	  {
            		  JOptionPane.showMessageDialog(null, "Erreur de connections");
            	  }
            	  
              });
		    //affichage principale
         gt.b2.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				
				

				base.Insertion(gt.num.getText().toString(), gt.Nom.getText().toString(), gt.Prenom.getText().toString(), gt.ch.getValue().toString()); 
              
				JOptionPane.showMessageDialog(null, "Avec succée");
				
				try {
					base.recherche(gt.Nom.toString(), gt.Prenom.toString());
					JOptionPane.showMessageDialog(null, ""+base.rs.getString("Nom"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
         
         ong.del.setOnAction(new EventHandler(){

			@Override
			public void handle(Event arg0) {
				
				
				sp.supportimage.setImage(null);
				
			}});
		     root.setBottom(boxbas);     
		     
		     primaryStage.setScene(s2);
		     primaryStage.show();
		 
		     
		     s2.setOnKeyPressed(new EventHandler<KeyEvent>(){


					@Override
					public void handle(KeyEvent event) {
						if(event.getCode() == KeyCode.ENTER)
						{if(txtid.getText().equals("10") && txtuser.getText().equals("medical")){
						    
							primaryStage.setScene(scene);
							
							anime.getParole("Bienvenu");
						   
							primaryStage.show();
						     
						     primaryStage.setFullScreen(true); 
						}else
						{
							anime.getParole("S'il vous plait! veuillez ressaisir l'ID et le nom,Merci");
							  							Alert alert = new Alert(AlertType.WARNING);
							alert.setTitle("Erreurs");
							alert.setHeaderText("Verifiez votre Nom et ID");
							alert.setContentText("Attention Nom ou Id incorrrecte!");							
							alert.showAndWait();
						}
						}
						
					}});
		     
		    scene.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

				@Override
				public void handle(ContextMenuEvent arg0) {
					barm.popmenu.show(root, arg0.getSceneX(), arg0.getSceneY());
					
				}
		        });
		    
		    
		    
		    
		     animation.setOnAction(e->
		     {
		    	 if(txtid.getText().equals("10") && txtuser.getText().equals("medical")){
		    	  primaryStage.setScene(scene);
				     primaryStage.show();
				     primaryStage.setFullScreen(true); 
		    	 }else
		    	 {
		    		 Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Erreurs");
						alert.setHeaderText("Verifiez votre Nom et ID");
						alert.setContentText("Attention Nom ou Id incorrrecte!");
						alert.showAndWait();
						}
		    	 
		     });
		     
		
				
		     tx.setOnMouseClicked(new EventHandler(){

				@Override
				public void handle(Event event) {
					 
					primaryStage.setScene(scene);
				     primaryStage.show();
				        
				     primaryStage.setFullScreen(true); 
				
				}});
		     System.getProperty("java.library.path");
	}
	 
	
	public static void main(String[] args) {
		
		launch(args);
	}
	@SuppressWarnings("null")
	@Override
	public void run(String arg0) {
		//ImagePlus imp = IJ.getImage();
		IJ.run("Interactive 3D Surface Plot");
		IJ.run("FFT");
		IJ.run("Split Channels");
		IJ.run("Find Edges");
		IJ.run("Sharpen");
		IJ.run("Convolver");
	IJ.run("Save");
	IJ.run("Histogram");
	IJ.run("Skeletonize(2D/3D)");
		IJ.run("Color Threshold ");
	IJ.run("Make Binary");
	FileInfo fi  = new FileInfo();
	 new FileOpener(fi).open();

		if (arg0.equals("jpeg"))
			new FileSaver(mp).saveAsJpeg();
		
		
	ImageProcessor ip = null;
	new ByteProcessor(ip, true);
	        if (arg0.equals("edge")) {
	            ip.setSnapshotCopyMode(true);
	            ip.findEdges();
	            ip.setSnapshotCopyMode(false);
	           
	        }
	        
	  }


	
	  }
	
