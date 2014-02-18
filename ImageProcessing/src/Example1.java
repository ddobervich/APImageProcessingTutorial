import processing.core.PApplet;

public class Example1 extends PApplet {
	int cols, rows;
	int[][] myArray;

	public void setup() {
		size(200, 200);
		cols = 200;
		rows = 200;

		myArray = new int[cols][rows];

		int my_color, c;
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				c = (int) (Math.random() * 255);
				my_color = color(c, c, c);
				myArray[i][j] = my_color;
			}
		}
	}

	public void draw() {
		// Draw points
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				stroke(myArray[i][j]); 				// set color to be current pixel color
				point(i, j); 						// draw a point
			}
		}
	}
}
