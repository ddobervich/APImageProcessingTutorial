import processing.core.PApplet;
import processing.core.PImage;

public class FindWaldoSquare extends PApplet {
	// Declaring a variable of type PImage
	PImage before;
	String path = "../images/";
	int currentImage = 1;
	String extension = ".jpg";
	int LASTIMAGE = 35;
	int targetW = 50;
	int targetH = 50;

	public void setup() {
		size(800, 600);

		// Make a before and after image.
		before = loadImage(fileFor(currentImage));
	}

	public void draw() {
		background(0);

		image(before, 0, 0); // draw the image

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

	private void findWaldoSquare(PImage before2) {
		// TODO Auto-generated method stub

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
				findWaldoSquare(before);
			} else if (keyCode == RIGHT) {
				currentImage++;
				if (currentImage > LASTIMAGE)
					currentImage = 1;
				before = loadImage(fileFor(currentImage));
				findWaldoSquare(before);
			}
		}
	}
}
