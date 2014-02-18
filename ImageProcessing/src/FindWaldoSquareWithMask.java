import processing.core.PApplet;
import processing.core.PImage;

public class FindWaldoSquareWithMask extends PApplet {
	
	// Declaring a variable of type PImage
	PImage before;
	String path = "../images/";
	int currentImage = 1;
	String extension = ".jpg";
	int LASTIMAGE = 35;
	int targetW = 50;
	int targetH = 50;
	
	Square bestRegion;

	public void setup() {
		size(800, 600);
		
		bestRegion = new Square(0, 0, 50, 50);
		
		// Make a before and after image.
		before = loadImage(fileFor(currentImage));
		applyColorMask();
		
		bestRegion = findWaldoSquare(before);
	}

	public void applyColorMask() {
		before.loadPixels(); // load pixel array

		for (int x = 0; x < before.width; x++) { 			// loop through every column
			for (int y = 0; y < before.height; y++) { 		// loop through every row
				
				int c = getColor(before, x, y);
				int loc = x + y * before.width;
				
				if (colorDistance(c, color(235, 28, 38)) < 40 || colorDistance(c, color(255, 255, 255)) < 15) {
					before.pixels[loc] = color(255); 			// Black
				} else {
					before.pixels[loc] = color(0); 		// White
				}

			}
		}

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

		image(before, 0, 0); // draw the image
		
		stroke(color(0, 255, 0));
		fill(color(255,255,255,0));
		rect(bestRegion.x, bestRegion.y, bestRegion.width, bestRegion.height);
		
		// Draw cross hairs
		stroke(0); // set line color to black
		line(mouseX, 0, mouseX, height);
		line(0, mouseY, width, mouseY);

		// Handle mouse clicks
		if (mousePressed) {
			if (mouseButton == RIGHT) { // if RIGHT click, display information
										// for that pixels
				int c = getColor(before, mouseX, mouseY);
				System.out.println("Mouse x: " + mouseX + "\tMouse y: "
						+ mouseY + " color: " + red(c) + "," + green(c) + ","
						+ blue(c));
			}

			if (mouseButton == LEFT) { // if LEFT click, run test on square
										// region where mouse is
				fill(0, 0, 0, 0);
				rect(mouseX, mouseY, targetW, targetH);
				
				double score = testRegion(mouseX, mouseY, mouseX + targetW,
						mouseY + targetH);
				
				System.out.println("Score for region: " + score);
			}
		}
	}

	private double testRegion2(int x1, int y1, int x2, int y2) {
		
	}
	
	private double testRegion(int x1, int y1, int x2, int y2) {
		int count = 0;
		for (int x = x1; x < x2; x++) {
			for (int y = y1; y < y2; y++) {
				int c = getColor(before, x, y);
				if (c == color(255, 255, 255)) {			// if color is white
					count++;						// add to the count
				}
			}
		}
		return count;
	}

	private int getColor(PImage img, int x, int y) {
		return img.pixels[x + y * img.width];
	}

	private Square findWaldoSquare(PImage before2) {
		applyColorMask();
		
		double bestScore = testRegion(bestRegion.x, bestRegion.y, bestRegion.x+50, bestRegion.y + 50);
		
		for (int x = 0; x < before2.width - 50; x += 40) {
			for (int y = 0; y < before2.height - 50; y += 40) {
				Square test = new Square(x, y, 50, 50);
				double score = testRegion(test.x, test.y, test.x+50, test.y+50);
				if ( score > bestScore) {
					bestScore = score;
					bestRegion = test;
				}
			}
		}
		
		return bestRegion;
	}

	public String fileFor(int n) {
		return path + n + extension;
	}

	public void keyReleased() {
		if (key == CODED) {
			if (keyCode == LEFT) {
				currentImage--;
				if (currentImage < 1)
					currentImage = LASTIMAGE;
				before = loadImage(fileFor(currentImage));
				bestRegion = findWaldoSquare(before);
			} else if (keyCode == RIGHT) {
				currentImage++;
				if (currentImage > LASTIMAGE)
					currentImage = 1;
				before = loadImage(fileFor(currentImage));
				bestRegion = findWaldoSquare(before);
			}
		}
	}
}
