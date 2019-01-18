
// Author: Koby

import java.awt.Color;

public class Health {

	// Member Variables
	EZRectangle blank = EZ.addRectangle(50, 375, 75, 20, Color.RED, true);

	EZRectangle h1;
	EZRectangle h2;
	EZRectangle h3;

	static final int NO_BAR = 0;
	static final int FIRST_BAR = 1;
	static final int SECOND_BAR = 2;
	static final int THIRD_BAR = 3;

	public static int state;

	// Constructor
	Health() {
		h1 = EZ.addRectangle(25, 375, 25, 20, Color.GREEN, true);
		h2 = EZ.addRectangle(50, 375, 25, 20, Color.GREEN, true);
		h3 = EZ.addRectangle(75, 375, 25, 20, Color.GREEN, true);

		state = 3;
	}

	// Member Functions
	// Check HP and change states accordingly when interacting w/ enemy/mushroom
	void processStates() {
		switch (state) {

		case NO_BAR:
			h1.hide();
			MainFunc.Game = 0;
			break;
		case FIRST_BAR:
			h1.show();
			h2.hide();
			h3.hide();
			break;
		case SECOND_BAR:
			h1.show();
			h2.show();
			h3.hide();
			break;
		case THIRD_BAR:
			h1.show();
			h2.show();
			h3.show();
			break;
		}
	}
}