package project;

import edu.princeton.cs.introcs.StdDraw;

public class SnakeEntity implements Entity {

	private int xPos;
	private int yPos;

	public SnakeEntity(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	@Override
	public int getXPos() {
		return xPos;
	}

	@Override
	public int getYPos() {
		return yPos;
	}

	@Override
	public void draw() {
		StdDraw.setPenColor(0, 150, 0);
		StdDraw.filledSquare(xPos * 0.04 + 0.02, yPos * 0.04 + 0.02, 0.015);
	}
}
