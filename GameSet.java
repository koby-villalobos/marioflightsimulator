import java.awt.Color;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

// Author: Kobi + Alton

public class GameSet {

	// Variables
	static int highScoreNum = 0;
	static String highScoreName;
	static char charSelect;
	int scoreNum = 0;
	String[] nameA = new String[1000];
	int[] scoreA = new int[1000];
	String input;
	EZSound bgm = EZ.addSound("bgm.wav");
	EZSound lose = EZ.addSound("lose.wav");
	EZSound win = EZ.addSound("win.wav");
	EZSound title = EZ.addSound("title.wav");

	// Member Functions
	// Game Setup
	void Up() throws java.io.IOException {
		EZ.initialize(600, 400);
		EZImage title = EZ.addImage("titlecard.png", 300, 200);
		this.title.loop();

		// Game Start Scene
		// Enter Name, check high score(s)
		System.out.println("Super Mario Flight Simulator v1.1 \n" + "ICS 111 Project 3 \n"
				+ "By Alton Lee, Angeli Amascual, Koby Villalobos\n\n");
		System.out.println(
				"- Controls - \nW: Fly up\nS: Fly down\n\n- Objective - \nCollect as many coins as you can!!\nCollect mushrooms to restore health.");
		System.out.println("------------------------------------");
		System.out.println("What is your name?\n");
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		input = scan.nextLine();
		System.out.println("Choose your character!\nm = Mario\nl = Luigi");

		// Choose a character. If bad choice, will ask player again
		while (charSelect != 'm' && charSelect != 'l' && charSelect != 'M' && charSelect != 'L') {
			charSelect = scan.next().charAt(0);
			switch (charSelect) {
			case 'm':
			case 'M':
				charSelect = 'm';
				break;
			case 'l':
			case 'L':
				charSelect = 'l';
				break;
			default:
				System.out.println("Bad input!");
			}
		}
		scan.close();

		// Checking Scores
		FileReader fr = new FileReader("HighScore.txt");
		Scanner sc = new Scanner(fr);
		if (sc.hasNextInt()) {
			scoreNum = sc.nextInt();
		}

		while (sc.hasNextLine()) {
			for (int i = 0; i < scoreNum; i++) {
				scoreA[i] = sc.nextInt();
				nameA[i] = sc.nextLine();
			}

		}

		for (int i = 0; i < scoreNum; i++) {
			if (scoreA[i] > highScoreNum) {
				highScoreName = nameA[i];
				highScoreNum = scoreA[i];
			}
		}

		// Title Close
		System.out.println("\n\n\n\n\n\n\n\nGAME START!!");
		sc.close();
		title.hide();
		this.title.stop();
		bgm.loop();
		fr.close();
	}

	// Breaks down game when lost
	void Close() throws java.io.IOException {
		// Saving + Comparing scores
		scoreNum++;
		FileWriter fw = new FileWriter("HighScore.txt");
		fw.write(scoreNum + "\n");
		for (int i = 0; i < scoreNum - 1; i++)
			fw.write(scoreA[i] + nameA[i] + "\n");

		fw.write(MainFunc.scorePoint + " " + input);
		if (MainFunc.scorePoint > highScoreNum) {
			// System.out.println(scorePoint + input);
			EZ.addText(300, 275, "New High Score!", Color.RED, 24);
			EZ.addText(300, 325, input + " " + MainFunc.scorePoint, Color.RED, 32);
			bgm.stop();
			win.play();
		} else {
			// System.out.println(highScoreNum + highScoreName);
			EZ.addText(300, 250, "High Score:", Color.RED, 28);
			EZ.addText(300, 287, highScoreName + " " + highScoreNum, Color.RED, 32);
			EZ.addText(300, 325, "Your Score: " + MainFunc.scorePoint, Color.RED, 24);
			bgm.stop();
			lose.play();
		}
		fw.close();
	}
}
