package Heelo;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
class Histo2  {
	
	
	

   public BufferedImage imageh;
    public CategoryAxis  axeX;
    public NumberAxis axeY;
   public  LineChart<String, Number> line;
    private long alpha[] = new long[256];
    private long R[] = new long[256];
    private long V[] = new long[256];
    private long B[] = new long[256];

    @SuppressWarnings("rawtypes")
    
    XYChart.Series Alpha;
    @SuppressWarnings("rawtypes")
    
    XYChart.Series rouge;
    @SuppressWarnings("rawtypes")
    
    XYChart.Series vert;
    @SuppressWarnings("rawtypes")
	
    XYChart.Series bleu;

    private boolean succeé;

     @SuppressWarnings({ "rawtypes", "unchecked" })
	Histo2(BufferedImage src) {
    	 imageh = src;
        succeé = false;

        //initialisation des tableaux des couleurs
        for (int i = 0; i < 256; i++) {
            alpha[i] = R[i] = V[i] = B[i] = 0;
        }
        WritableRaster pixels =  imageh.getRaster();
        if (pixels == null) {
            return;
        }

        //parcourir les pixels de l'image  0fxx = 255 en decimale
        for (int y = 0; y < imageh.getHeight(); y++) {
            for (int x = 0; x < imageh.getWidth(); x++) {
            	
            	int argb = pixels.getHeight();
                int a = (0xff & (argb >> 24));
                int r = (0xff & (argb >> 16));
                int v = (0xff & (argb >> 8));
                int b = (0xff & argb);
              
                alpha[a]++;
                R[r]++;
                B[b]++;
                V[v]++;

            }
        }

       Alpha = new XYChart.Series();
       rouge = new XYChart.Series();
       vert = new XYChart.Series();
        bleu = new XYChart.Series();
        Alpha.setName("alpha");
        rouge.setName("rouge");
        vert.setName("vert");
       bleu.setName(" bleu");

        //affichage des pixels dans l'histogramme
        
        for (int i = 0; i < 256; i++) {
            Alpha.getData().add(new XYChart.Data(String.valueOf(i), alpha[i]));
            rouge.getData().add(new XYChart.Data(String.valueOf(i), R[i]));
            vert.getData().add(new XYChart.Data(String.valueOf(i), V[i]));
            bleu.getData().add(new XYChart.Data(String.valueOf(i), B[i]));
        }

        succeé = true;
    }

    public boolean avecSuccée() {
        return succeé;
    }

    @SuppressWarnings("rawtypes")
	public XYChart.Series getAlpha() {
        return Alpha;
    }


    @SuppressWarnings("rawtypes")
	public XYChart.Series getRouge() {
        return rouge;
    }

    @SuppressWarnings("rawtypes")
	public XYChart.Series getVert() {
        return vert;
    }

    @SuppressWarnings("rawtypes")
	public XYChart.Series getBleu() {
        return bleu;
    }
    

public  void histodessin()
{
	   axeX = new CategoryAxis();
      axeX.setLabel("Intensité Lumineuse");
      axeY = new NumberAxis();
      axeY.setLabel("Nombre de Pixels");
        line = new LineChart<>(axeX, axeY);
		 	line.setCreateSymbols(false);
		  line.setId("hist");	

}





}