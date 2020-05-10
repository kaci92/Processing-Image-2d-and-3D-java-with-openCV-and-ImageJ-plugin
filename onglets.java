package Heelo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class onglets extends Application {

    public TabPane tabg;	
	public Tab tab1,tab2;
    public Slider sld;
    public Button del;
	
	public onglets()
	{
		VBox vb = new VBox();
		tabg = new TabPane();
		sld = new Slider();
		del = new Button();
		del.setText("supprimer");
		sld.setMin(0);
		sld.setValue(0);
		sld.setMax(400);
		sld.setShowTickLabels(true);
		sld.setShowTickMarks(true);
		sld.setMajorTickUnit(50);
		sld.setMinorTickCount(5);
		sld.setBlockIncrement(10);
		
		tab1 = new Tab("Mesure & Dessin");
		tab2 = new Tab("Outils");
		vb.getChildren().addAll(sld,del);
		tab2.setContent(vb);
		tabg.getTabs().addAll(tab1,tab2);
		
		
		
	}
	
	
	@Override
	public void start(Stage stab) throws Exception {
		
		Scene stabpane = new Scene(tabg,780,1500,Color.AQUA);
		
		stab.setScene(stabpane);
		stab.setFullScreen(true);
		stab.show();
	}

}
