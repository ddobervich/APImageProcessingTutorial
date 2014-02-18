import processing.core.PApplet;
import processing.core.PImage;

public class Example3 extends PApplet {
	// Declaring a variable of type PImage
	PImage before, after;   

	public void setup() {
  		size(544, 408);

  		// Make a before and after image.
  		before = loadImage("../images/example.jpg");
  		after = createImage(before.width, before.height, RGB);

		before.loadPixels();					// load pixel array 
		after.loadPixels();

		for (int x = 0; x < before.width; x++) {		// loop through every column
		  for (int y = 0; y < before.height; y++) {		// loop through every row
		    int loc = x + y * before.width;				// find the 1D pixel array location
		    int c = before.pixels[loc];					// load the pixel value

		    int r = (int) red(c);						// get the red, green, and blue values
		    int g = (int) green(c);						// out of the pixel's color value.
		    int b = (int) blue(c);

		    after.pixels[loc] = color(0, g, b);		// create a new color without the red
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
