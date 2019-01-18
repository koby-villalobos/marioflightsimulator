
// Author: Angeli

import java.util.Random;

public class Coin {

	// Member Variables
	static EZImage coin;
	int x = 700;
	int y = 100;
	float t;
	int movx;
	int maxScreenX;
	int maxScreenY;
	EZSound coinGet = EZ.addSound("coin.wav");

	// Constructor
	Coin(int moveSpeed, float time, int maxX, int maxY) {
		coin = EZ.addImage("coin.gif", 0, 0);
		t = time;
		maxScreenX = maxX;
		maxScreenY = maxY;
		random();
	}

	// Member Functions
	// Sets random y position when off-screen
	void random() {
		Random rg = new Random();

		int multiples = (int) maxScreenY / coin.getHeight();

		int ranY = rg.nextInt(multiples) * coin.getHeight();
		position(maxScreenX, ranY);
	}

	// Movement Speed
	public void move(int moveSpeed) {
		x -= moveSpeed;
		position(x, y);
		if (x < 0) {
			random();
		}
	}

	// Movement
	public void position(int posx, int posy) {
		x = posx;
		y = posy;
		if (y < 32)
			y += 32;
		if (y > 300)
			y -= 32;
		coin.translateTo(x, y);
	}

	// Collision Check
	boolean collision() {
		if (EZ.isElementAtPoint(PlayerMovement.posx + 16, PlayerMovement.posy + 16, coin)
				|| EZ.isElementAtPoint(PlayerMovement.posx + 16, PlayerMovement.posy - 16, coin)
				|| EZ.isElementAtPoint(PlayerMovement.posx - 16, PlayerMovement.posy + 16, coin)
				|| EZ.isElementAtPoint(PlayerMovement.posx - 16, PlayerMovement.posy - 16, coin)) {
			// System.out.println("get!");
			coinGet.play();
			return true;
		}
		return false;
	}

	public void hide() {
		random();
	}
}
