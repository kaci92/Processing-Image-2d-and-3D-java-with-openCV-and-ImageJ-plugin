package Heelo;

import java.awt.Color;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

class histogram {

private Image image;
private long alpha[] = new long[256];
private long rouge[] = new long[256];
private long vert[] = new long[256];
private long bleu[] = new long[256];

private long lum[] = new long[256];

XYChart.Series Alpha;
XYChart.Series Rouge;
XYChart.Series Vert;
XYChart.Series Bleu;
public LineChart line;
public XYChart.Series Luminosité;
public CategoryAxis axeX ;
public NumberAxis axeY;
private boolean succée;

histogram(Image src) {
image = src;
succée = false;
for (int i = 0; i < 256; i++) {
alpha[i] = rouge[i] = vert[i] = bleu[i] = 0;
lum[i] = 0;
}

PixelReader pixelReader = image.getPixelReader();
if (pixelReader == null) {
return;
}
for (int y = 0; y < image.getHeight(); y++) {
for (int x = 0; x < image.getWidth(); x++) {
int argb = pixelReader.getArgb(x, y);
int a = (0xff & (argb >> 24));
int r = (0xff & (argb >> 16));
int v = (0xff & (argb >> 8));
int b = (0xff & argb);

alpha[a]++;
rouge[r]++;
vert[v]++;
bleu[b]++;

//Convert RGB to HSB (or HSV)
float[] hsb = new float[3];
Color.RGBtoHSB(r, v, b, hsb);
lum[(int)(hsb[2]*255)]++;
}
}

Alpha = new XYChart.Series();
Rouge = new XYChart.Series();
Vert = new XYChart.Series();
Bleu = new XYChart.Series();
Luminosité = new XYChart.Series();
Alpha.setName("alpha");
Rouge.setName("red");
Vert.setName("green");
Bleu.setName("blue");
Luminosité.setName("Luminosité");
for (int i = 0; i < 256; i++) {
Alpha.getData().add(new XYChart.Data(String.valueOf(i), alpha[i]));
Rouge.getData().add(new XYChart.Data(String.valueOf(i), rouge[i]));
Vert.getData().add(new XYChart.Data(String.valueOf(i), vert[i]));
Bleu.getData().add(new XYChart.Data(String.valueOf(i), bleu[i]));

Luminosité.getData().add(new XYChart.Data(String.valueOf(i), lum[i]));
}
succée= true;}

public boolean Succée() {
return succée;}

public XYChart.Series getAlpha() {
return Alpha;}

public XYChart.Series getrouge() {
return Rouge;}

public XYChart.Series getvert() {
return Vert;}

public XYChart.Series getbleu() {
return Bleu;}

public XYChart.Series getluminosite(){return Luminosité;}
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