package Heelo;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class appMenu extends Application{

	
	public MenuBar menuBar;
	public Menu menu,m2,m3,m4,mfilt,mtr,menu3d,analyse;

	public CheckMenuItem conteur,info,itemdicom,item1,item2,item22,item3,item4,item5,item6,item7,item8,egaHist,item3d,hist;
	public CustomMenuItem item9;
	public CheckMenuItem item10,item11,item12,item13,item14,item15,item16;
	public CheckMenuItem histsub,sub1,sub2,sub3,sub4,sub11,sub12,sub13;
	public ContextMenu popmenu;
	public MenuItem smallitem1,smallitem2,smallitem3;
	public MenuItem itm3d,item3d1,item3d2;
	public Slider sld1;
	public appMenu()
	{ 
	
	  menuBar = new MenuBar();
	     //menuBar.setStyle("  -fx-background-color: radial-gradient(center 50% 50% , radius 200px , #ffebcd, #008080);");
	     // menu
	     menu = new Menu("Fichier");
	     
	     item1 = new CheckMenuItem("Ouvrir...");
	     item1.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
	     itemdicom = new CheckMenuItem("Ouvrir DICOM");
	     itemdicom.setAccelerator(KeyCombination.keyCombination("Ctrl+d"));
	     
	     item2 = new CheckMenuItem("Enregistrer...");
	     item22 = new CheckMenuItem("Enregistrer Image3D");
	     item22.setAccelerator(KeyCombination.keyCombination("Ctrl+n"));
	     item2.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
	     item3 = new CheckMenuItem("Quitter");
	     item3.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
	     item3.setOnAction(e->
	     {
	    	 Platform.exit();
	     });
	   item3.setMnemonicParsing(true);
	  
          menu.setStyle("-fx-font-size:25px; -fx-font-color:white;");
	     menu.getItems().addAll(item1,itemdicom, item2,item22,new SeparatorMenuItem(), item3);
	     
	      m2 = new Menu("Edition");
	      item4 = new CheckMenuItem("Coupier");
	      item5 = new CheckMenuItem("Coller");
	      item6 = new CheckMenuItem("paler");
	      m2.getItems().addAll(item4,item5,new SeparatorMenuItem(),item6);
	     
	      m2.setStyle("-fx-font-size:25px;");
	     
	      m3 = new Menu("Affichage");
	      item7 = new CheckMenuItem("Afficher PanneauG");
	      
	     sld1 = new Slider();
	     
	     item8 = new CheckMenuItem("Rotation");
	     item3d = new CheckMenuItem("3D");
	      item9 = new CustomMenuItem(sld1);
	      item9.setHideOnClick(false);
	      hist = new CheckMenuItem("Histogramme");
	      m3.getItems().addAll(item7,new SeparatorMenuItem(),item8,item9,item3d);
	      m3.setStyle("-fx-font-size:25px;");
	      
	      m4 = new Menu("Aller ->");
	  
	      item10 = new CheckMenuItem("Traitement");
	      item10.setGraphic(new ImageView("file:///C:/Users/Youcef/workspace/FXTtry/Icons/aff.png"));
	      item11 = new CheckMenuItem("Gestion Patient");
	      item12 = new CheckMenuItem("Autres");
	      m4.getItems().addAll(item10,new SeparatorMenuItem(),item11,item12);
	      m4.setStyle("-fx-font-size:25px;");
	       
	      menu3d = new Menu("3D");
	      item3d = new CheckMenuItem("Affichage 3D");
	      item3d1 = new CheckMenuItem("FFT");
	      item3d2 = new CheckMenuItem("Segmentation 3D");
	      menu3d.getItems().addAll(item3d,item3d1, new SeparatorMenuItem(),item3d2);
	      menu3d.setStyle("-fx-font-size:25px;");
 	    
	      
	      mfilt = new Menu("Filtre");
	     item13 = new CheckMenuItem("Moyanneur");
	     item14 = new CheckMenuItem("Médian");
	     item15 = new CheckMenuItem("Gaussien");
	     item16 = new CheckMenuItem("Laplacien");
         mfilt.getItems().addAll(item13,item14, new SeparatorMenuItem(),item15,item16);
         mfilt.setStyle("-fx-font-size:25px;");
	     
         
         
         mtr = new Menu("Traitement");
         sub1 = new CheckMenuItem("Split");
         sub11 = new CheckMenuItem("Seuillage");
         sub12 = new CheckMenuItem("Conteurs Canny");
         
         sub13 = new CheckMenuItem("Histogramme");
         egaHist = new CheckMenuItem("Egalisation");
         sub2 = new CheckMenuItem("Recalage");
         sub3 = new CheckMenuItem("Filtrage 2D");
         mtr.getItems().addAll(sub1,sub11,sub12,sub13,sub2,sub3, egaHist);
         mtr.setStyle("-fx-font-size:25px;");
         
         mfilt.getItems().add(mtr);
         analyse = new Menu("Analyse");
         histsub = new CheckMenuItem("Squlette");
         info = new CheckMenuItem("Informations DICOM");
         conteur = new CheckMenuItem("Conteurs intelligents");
         analyse.getItems().addAll(histsub,info,new SeparatorMenuItem(),conteur);
         analyse.setStyle("-fx-font-size:25px;");
         
         menuBar.getMenus().addAll(menu,m2,m3,m4,mfilt,menu3d,analyse);
         
         popmenu = new ContextMenu(); 
		smallitem1 = new MenuItem("Binaire");
		smallitem2 = new MenuItem("Couper");
		smallitem3 = new MenuItem("Annuler");
			popmenu.getItems().addAll(smallitem1,smallitem2,smallitem3);
		
			
	}
	

	
	@Override
	public void start(Stage smenu) throws Exception {	
	
		Scene stmenu = new Scene(menuBar,780,1500,Color.CHOCOLATE); 
		
		menuBar.prefWidthProperty().bind(smenu.widthProperty());
		smenu.setFullScreen(true);
		smenu.setScene(stmenu);
		smenu.show();
	}

}
