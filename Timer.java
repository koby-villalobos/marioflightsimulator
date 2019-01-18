// Author: Alton

public class Timer {
	// Member Variables
	long startTime = System.currentTimeMillis();
	int Speed;

	// Constructor
	Timer(int moveSpeed) {
		Speed = moveSpeed;
	}

	// Member Variables
	// Changes speed after a set time
	int SpeedChange(int moveSpeed) {
		long time = System.currentTimeMillis() - startTime;
		// Default: 15 secs
		if (time > 15000) {
			Speed++;
			startTime = System.currentTimeMillis();
			System.out.println("Speed up!");
		}
		return Speed;
	}
}