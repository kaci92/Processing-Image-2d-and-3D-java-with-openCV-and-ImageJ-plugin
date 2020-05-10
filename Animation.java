package Heelo;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

import javafx.util.Duration;

import t2s.son.LecteurTexte;

public class Animation {
	public Node node;
	public Animation(){}
	public FadeTransition getFonu()
	
	{  FadeTransition fondu = new FadeTransition(Duration.seconds(2),node);
		
	   fondu.setFromValue(0.0);
	   fondu.setToValue(2.0);
	   fondu.setCycleCount(3);
	   fondu.setAutoReverse(true);
	   fondu.play();
		return fondu;
		
	}

	
	public ScaleTransition getScaleTran(){
	ScaleTransition echelle = new ScaleTransition(Duration.seconds(1),node);
    echelle.setFromX(8.0);
    echelle.setFromY(8.0);
    echelle.setToX(1.0);
    echelle.setToY(1.0);
    echelle.setFromZ(8.0);
    echelle.setToZ(1.0);
	  echelle.play();
	  return echelle;
	  }
	
	
	public TranslateTransition getTransition(){
	TranslateTransition anim = new  TranslateTransition();
    anim.setDuration(Duration.seconds(2));
    anim.setNode(node);
    anim.setToX(600);
    anim.setToY(300);
    anim.setAutoReverse(true);
    anim.setCycleCount(2);
    anim.play();
	return anim;
	}
	public PathTransition getCirculaire(){
	
	Circle lineanim  = new Circle(50); 
	lineanim.setTranslateX(750);
	lineanim.setTranslateY(75);
	
	PathTransition pt = new PathTransition(Duration.seconds(3),lineanim,node);
	pt.setAutoReverse(true);
	pt.setCycleCount(2);
	pt.play();
	return pt;
	
	}
	public LecteurTexte getParole(String t){
	LecteurTexte lecteur = new LecteurTexte(t);
	lecteur.setVoix(2);
     lecteur.playAll();
   
	return lecteur;
	}
	
	
	}
