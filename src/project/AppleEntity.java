package project;

import edu.princeton.cs.introcs.StdDraw;

public class AppleEntity implements Entity {

	private int xPos;
	private int yPos;

	
	public AppleEntity(int xPos, int yPos) {
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
		StdDraw.setPenColor(255, 0, 0);
		//StdDraw.filledCircle(xPos * 0.04 + 0.02, yPos * 0.04 + 0.02, 0.02);
		StdDraw.picture(xPos * 0.04 + 0.02, yPos * 0.04 + 0.02, "C:\\Users\\mjmik\\IdeaProjects\\project-mjmikejoo\\pictures\\apple1.png", 0.035, 0.035);
	}
}
