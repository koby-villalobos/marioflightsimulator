
//			ICS 111 Project 3 - Super Mario Flight Simulator
//
// Authors: Alton, Angeli, Koby
// TODO:	+ Add high score system
//			+ Game Start! / Game Over! screens
//			+ Edit placement of start enemies so no collisions when switching windows
//			+ Fix bg white bars
//			+ Fix enemy collisions sometimes not working
//			+ player animations
// Enjoy!

import java.awt.Color;

public class MainFunc {
	// Universal Game Variables
	static int Speed = 4;
	static int Game = 1;
	static int scorePoint = 0;

	// Game Start!!
	public static void main(String[] args) throws java.io.IOException {
		// Initialize game set, high score, etc...
		GameSet set = new GameSet();
		set.Up();

		// Game variables
		Timer time = new Timer(Speed);
		BGMovement bg = new BGMovement(Speed);
		PlayerMovement player = new PlayerMovement(GameSet.charSelect); // m = Mario; l = Luigi
		Mushroom mush = new Mushroom();
		Coin coin = new Coin(Speed, 1, 600, 300);
		Health hp = new Health();

		// Game Enemies
		Block[] obstacle = new Block[3];
		for (int i = 0; i < 3; i++) {
			obstacle[i] = new Block(450, 700, 4 + i);
		}

		// Score
		EZText scoreText = EZ.addText(160, 376, "Score: " + scorePoint, Color.white, 20);
		scoreText.setFont("SuperMario256.ttf");

		// While game is running...
		while (Game == 1) {
			// Speed gets faster
			Speed = time.SpeedChange(Speed);

			// Game AI
			hp.processStates();
			bg.Movement(Speed);
			player.Movement();
			coin.move(Speed);

			// Mushroom movement
			mush.Movement(Speed);
			mush.randomLocation();
			mush.mushGet();

			// Enemy movement
			for (int i = 0; i < 3; i++) {
				obstacle[i].hitBox();
				obstacle[i].Move();
				obstacle[i].reset();
				obstacle[i].restart();
			}

			// Coin movement
			if (coin.collision()) {
				coin.hide();
				scorePoint++;
				scoreText.setMsg("Score: " + scorePoint);
			}

			EZ.refreshScreen();

			// When game is lost...
			if (Game == 0) {
				// Compare Scores, Display High Score
				set.Close();

				// Game End Scene
				player.death();
				EZ.addText(300, 200, "GAME OVER", Color.RED, 64);
				System.out.println("GAME OVER");
			}
		}
	}
}
