// Author: Alton

public class PlayerMovement {

	// Member variables
	EZImage pc;
	static int posx = 0;
	static int posy = 0;
	int maxHeight = 0;
	char chr;

	// Constructor
	PlayerMovement(char c) {
		chr = c;
		posx = 75;
		posy = 200;
		pc = EZ.addImage("marios.gif", posx, posy);
		if (chr == 'm')
			pc.setFocus(0, 65, 48, 128);
		if (chr == 'l')
			pc.setFocus(49, 65, 96, 128);
		maxHeight = 400;
	}

	// Member Functions
	void Movement() {
		// Default: movy = 7
		int movy = 7;
		// When W is pressed... move UP
		if (EZInteraction.isKeyDown('w') || EZInteraction.isKeyDown('W')) {
			// Change sprite to...
			if (chr == 'm')
				pc.setFocus(0, 129, 48, 198);
			else if (chr == 'l')
				pc.setFocus(49, 129, 96, 198);
			posy -= movy;
			pc.translateTo(posx, posy);
			if (posy <= 32)
				posy = 32;
		}
		// When S is pressed... move DOWN
		else if (EZInteraction.isKeyDown('s') || EZInteraction.isKeyDown('S')) {
			// Change sprite to...
			if (chr == 'm')
				pc.setFocus(0, 0, 48, 64);
			else if (chr == 'l')
				pc.setFocus(49, 0, 96, 64);
			posy += movy;
			pc.translateTo(posx, posy);
			if (posy >= maxHeight - 96)
				posy = maxHeight - 96;
		} else {
			if (chr == 'm')
				pc.setFocus(0, 65, 48, 128);
			if (chr == 'l')
				pc.setFocus(49, 65, 96, 128);
		}
	}

	void death() {
		if (chr == 'm')
			pc.setFocus(0, 199, 48, 256);
		else if (chr == 'l')
			pc.setFocus(49, 199, 96, 256);
	}
}
