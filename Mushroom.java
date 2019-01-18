
// Author: Angeli

import java.util.Random;

public class Mushroom {

	// Member Variables
	static EZImage shrm;
	int posx = 700;
	int posy = 200;
	int movx;
	long startTime = System.currentTimeMillis();
	static int go = 0;
	Random y = new Random();
	EZSound mushGet = EZ.addSound("mush.wav");

	// Constructor
	Mushroom() {
		shrm = EZ.addImage("mushroom.gif", posx, posy);
	}

	// Member Functions
	// Sets random y position off-screen
	void randomLocation() {
		if (posx <= -100) {
			posx = 700;
			posy = y.nextInt(300);
			go = 1;
		}
	}

	// After a set time, moves to the left
	void Movement(int moveSpeed) {
		long time = System.currentTimeMillis() - startTime;
		// Default: 20 secs
		if (time > 20000 && go == 0) {
			posx -= moveSpeed;
			shrm.translateTo(posx, posy);
		}
		if (go == 1) {
			startTime = System.currentTimeMillis();
			go = 0;
		}
	}

	// Collision Check
	boolean mushGet() {
		if (EZ.isElementAtPoint(PlayerMovement.posx, PlayerMovement.posy + 16, shrm)
				|| EZ.isElementAtPoint(PlayerMovement.posx, PlayerMovement.posy - 16, shrm)
				|| EZ.isElementAtPoint(PlayerMovement.posx, PlayerMovement.posy + 16, shrm)
				|| EZ.isElementAtPoint(PlayerMovement.posx, PlayerMovement.posy - 16, shrm)) {
			mushGet.play();
			posx = 700;
			posy = y.nextInt(300);
			shrm.translateTo(posx, posy);
			go = 1;
			if (Health.state < 3)
				Health.state++;
			return true;
		}
		return false;
	}
}
