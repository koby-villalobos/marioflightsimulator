
// Author: Koby

import java.util.Random;

public class Block {

	// member variables
	public EZImage m;
	public int xloc;
	long startTime;
	public int moveSpeed;
	EZSound hit = EZ.addSound("hit.wav");

	int rr;

	int sec = 0;

	// constructor
	public Block(int x, int y, int z) {
		xloc = x;
		m = EZ.addImage("npc.gif", xloc, y);
		moveSpeed = z;
		startTime = System.currentTimeMillis();

	}

	// member functions
	// moves each object to the left
	public void Move() {
		m.translateBy(-moveSpeed, 0);
	}

	// resets block once it goes off the screen, places it with random y
	public void reset() {
		int xCen = m.getXCenter();
		Random y = new Random();
		if (xCen <= -50) {
			m.translateTo(650, y.nextInt(350));
			m.show();
		}
	}

	// function that increases the speed of objects every ten seconds
	void restart() {
		long time = System.currentTimeMillis() - startTime;
		// Default: 15 secs
		if (time > 15000) {
			// sec += 10;
			moveSpeed += 1;
			startTime = System.currentTimeMillis();
		}
	}

	// collision check
	boolean hitBox() {
		if (EZ.isElementAtPoint(PlayerMovement.posx, PlayerMovement.posy, m)
				|| EZ.isElementAtPoint(PlayerMovement.posx + 16, PlayerMovement.posy + 16, m)
				|| EZ.isElementAtPoint(PlayerMovement.posx + 16, PlayerMovement.posy - 16, m)
				|| EZ.isElementAtPoint(PlayerMovement.posx - 16, PlayerMovement.posy + 16, m)
				|| EZ.isElementAtPoint(PlayerMovement.posx - 16, PlayerMovement.posy - 16, m)) {
			m.hide();
			if (Health.state > 1)
				hit.play();
			// System.out.println("hit!");
			Health.state--;
			return true;
		}
		return false;
	}
}
