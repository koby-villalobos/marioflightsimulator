// Author: Alton

public class BGMovement {
	// Member Variables
	EZImage[] bg = new EZImage[2];
	int pos1 = 512;
	int pos2 = 1536;
	long ti = System.currentTimeMillis();
	int movx;

	// Constructor
	BGMovement(int moveSpeed) { //
		bg[0] = EZ.addImage("bg.gif", pos1, 200);
		bg[1] = EZ.addImage("bg.gif", pos2, 200);
		movx = moveSpeed;
	}

	// Member Function
	// Movement of background to simulate player is moving
	void Movement(int movx) {
		// bg moves much slower than fg
		movx /= 2;
		pos1 -= movx;
		pos2 -= movx;

		// When bg1 gets to a position where it cannot be seen, move "behind" bg2
		bg[0].translateTo(pos1, 200);
		if (pos1 <= -512)
			pos1 = 2 * 1024 + pos1;
		// When bg2 gets to a position where it cannot be seen, move "behind" bg1
		bg[1].translateTo(pos2, 200);
		if (pos2 <= -512)
			pos2 = 2 * 1024 + pos2;
	}
}
