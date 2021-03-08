//a frame consists of 10 pins and two chances to score
// Only the roll logic is here, this is mostly just a storage object
//
public class Frame {
	
	public int firstRoll;
	public int secondRoll;
	public int thirdRoll;
	public boolean isSpare;
	public boolean isStrike;
	public int frameScore;
	
	public Frame() {
		this.firstRoll = 0;
		this.secondRoll = 0;
		this.thirdRoll = 0;
		this.isSpare = false;
		this.isStrike = false;
		this.frameScore = 0;
	}
	
	public int getFirstRoll() {
		return firstRoll;
	}

	public int getFrameScore() {
		return frameScore;
	}

	public int getSecondRoll() {
		return secondRoll;
	}

	public int getThirdRoll() {
		return thirdRoll;
	}

	private int roll(int remainingPins) {
		int roll = (int) (Math.random()*(remainingPins + 1)); // normal stochastic distribution
		//int roll = 10; //strikemaster
		return roll;
	}

	public void setFirstRoll() {
		this.firstRoll = roll(10);
	}

	public void setFrameScore(int frameScore) {
		this.frameScore = frameScore;
	}

	public void setSecondRoll(int remainingPins) {
		this.secondRoll = roll(remainingPins);
	}

	public void setThirdRoll(int remainingPins) {
		this.thirdRoll = roll(remainingPins);
	}
}
