import processing.core.PApplet;
import processing.core.PImage;

public class Example5 extends PApplet {
	// Declaring a variable of type PImage
	PImage before, after;

	public void setup() {
		size(544, 408);

		// Make a before and after image.
		before = loadImage("../images/example.jpg");
		after = createImage(before.width, before.height, RGB);

		before.loadPixels(); // load pixel array
		after.loadPixels();

		for (int x = 0; x < before.width; x++) { 			// loop through every column
			for (int y = 0; y < before.height; y++) { 		// loop through every row
				int loc = x + y * before.width; 				// find the 1D pixel array
																// location
				int c = before.pixels[loc]; 					// load the pixel value

				double dist = colorDistance(c, color(183, 185, 102));	// Calculate color distance from yellow
				
				if (dist < 20) {
					after.pixels[loc] = color(255); 			// White
				} else {
					after.pixels[loc] = color(0); 				// Black
				}
			}
		}

		after.updatePixels();
		before.updatePixels(); // store the changes
	}

	public double colorDistance(int c1, int c2) {
		float red_diff = red(c1) - red(c2);
		float green_diff = green(c1) - green(c2);
		float blue_diff = blue(c1) - blue(c2);

		return Math.sqrt(red_diff*red_diff + green_diff*green_diff + blue_diff*blue_diff);
	}

	public void draw() {
		background(0);

		if (keyPressed) {
			image(after, 0, 0);
		} else {
			image(before, 0, 0);
		}
	}
}
