import processing.core.PApplet;
import processing.core.PImage;

public class Example2 extends PApplet {
	// Declaring a variable of type PImage
	PImage before, after;   

	public void setup() {
  		size(544, 408);

  		// Make a before and after image.
  		before = loadImage("../images/example.jpg");
  		after = createImage(before.width, before.height, RGB);

		before.loadPixels();					// load pixel array 
		after.loadPixels();

		for (int i = 0; i < before.pixels.length; i++) {	// loop through all pixels
			
			if (before.pixels[i] < 250) {					
				after.pixels[i] = before.pixels[i] - 100;		// increase their color by 5
			} else {
				after.pixels[i] = before.pixels[i];
			}
			
		}

		after.updatePixels();
		before.updatePixels();					// store the changes
	}

	public void draw() {
  		background(0);
  
		if (keyPressed) {
			image(after,0,0);
		} else {
			image(before, 0, 0);
		}
	}
}
