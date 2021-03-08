//Game object, used to execute the work flow of a game
//stores information in the frame objects
//keeps a scoreSheet object for all the scores.
public class Game 	{
	//Properties of a game
	public int currentFrameNum;
	public int currentScore;
	public Frame[] scoreSheet;
	public String playerName = new String();
	
	// Constructor - resets the game to zeros.
	public Game(String playername) 	{
		this.currentFrameNum = 0;
		this.currentScore = 0;
		this.scoreSheet = new Frame[10];
		this.playerName = playername;
	}
	
	//This kicks off the game logic, including the 10th Frame.
	public void start() {
		while (currentFrameNum < 9) { //first 9 frames
			bowlFrame(5,5);
			currentFrameNum++;
		}
		bowlFinalFrame(5,5,5);
	}
	
	public void bowlFrame(int frameIndex, int firstRoll, int secondRoll) {
		scoreSheet[frameIndex] = new Frame();
		scoreSheet[frameIndex].setFirstRoll(firstRoll);
		calculateFirst();
		//scoreSheet[frameIndex].setSecondRoll(secondRoll, true);
		currentFrameNum++;
	}
	
	//bowling logic - no scores are tallied - just frame properties
	public void bowlFrame(int firstRoll, int secondRoll) {
		scoreSheet[currentFrameNum] = new Frame();
		scoreSheet[currentFrameNum].setFirstRoll(firstRoll); //bowl at 10 pins
		
		if (firstRoll == 10) { //strike!
			scoreSheet[currentFrameNum].isStrike = true ;
			calculateFirst();
			currentFrameNum++;
			return;
		}
		else { //this was not a strike
			calculateFirst();
			scoreSheet[currentFrameNum].setSecondRoll(secondRoll); //bowl at remaining pins
			
			if (firstRoll + secondRoll == 10) { //Spare!
				scoreSheet[currentFrameNum].isSpare = true;
			}
		}
		calculateSecond();
		currentFrameNum++;
		return;
	}
	
	//Logic for final frame here
	public void bowlFinalFrame(int firstRoll, int secondRoll, int thirdRoll) {
		scoreSheet[currentFrameNum] = new Frame();
		scoreSheet[currentFrameNum].setFirstRoll(firstRoll);
		if (firstRoll == 10) { //strike! Get a third roll
			scoreSheet[currentFrameNum].isStrike = true;
			calculateFirst();
			scoreSheet[currentFrameNum].setSecondRoll(secondRoll); //get clean frame on second roll
			calculateSecond();
			if (secondRoll == 10) { //strike on second roll
				scoreSheet[currentFrameNum].setThirdRoll(thirdRoll); //Clean frame on third roll
				calculateThird();
			}
			else { //not a strike on second roll
				scoreSheet[currentFrameNum].setThirdRoll(thirdRoll); //bowl at remaining pins
				calculateThird();
			}
		}
		else { //first roll in final frame was not a strike
			calculateFirst();
			scoreSheet[currentFrameNum].setSecondRoll(secondRoll); //bowl second at remaining pins
			if (firstRoll + secondRoll == 10) { //Spare! Get a third roll
				scoreSheet[currentFrameNum].isSpare = true;
				calculateSecond();
				scoreSheet[currentFrameNum].setThirdRoll(thirdRoll); //get a clean 3rd roll 
				calculateThird();
			}
			else { //Open frame on second roll, be done.
				calculateSecond();
			}
		}
	}
	
	//check for previous spares or strikes, set frameScore for past and for present;
	private void calculateFirst() {
		//in firstFrame, nothing to roll up, go to next roll
		if (currentFrameNum == 0) {
			if (scoreSheet[currentFrameNum].isStrike) {
				scoreSheet[currentFrameNum].setFrameScore(10);
			}
			return;
		}
		else { //For the Second Frame and beyond, look backward
			//if last frame was a spare, add this Frame's firstRoll to the finalScore of the previous frame
			if (scoreSheet[currentFrameNum-1].isSpare) {
				scoreSheet[currentFrameNum-1].setFrameScore(scoreSheet[currentFrameNum-1].getFrameScore() + scoreSheet[currentFrameNum].getFirstRoll());
			}
			//if last frame was a strike, add this Frame's firstRoll to the finalScore of the previous frame, check previous for strike
			if (scoreSheet[currentFrameNum-1].isStrike) {
				scoreSheet[currentFrameNum-1].setFrameScore(scoreSheet[currentFrameNum-1].getFrameScore() + scoreSheet[currentFrameNum].getFirstRoll());
				
				if (currentFrameNum > 1 ) { //if on 3rd frame and two back and one back were strikes, add this to current score
					if (scoreSheet[currentFrameNum-2].isStrike) {
						scoreSheet[currentFrameNum-2].setFrameScore(scoreSheet[currentFrameNum-2].getFrameScore() + scoreSheet[currentFrameNum].getFirstRoll());
					}
				}
			} 
			//if firstRoll and is strike, do nothing yet on current frame except for setting the frameScore to 10.
			if (scoreSheet[currentFrameNum].firstRoll == 10) {
				scoreSheet[currentFrameNum].setFrameScore(10);
				return; 
			}
		}
	}
	
	//Check for current spares or open frames & for previous strikes, sets frameScores accordingly
	private void calculateSecond() {
		//if first frame, no need to look back. Just calculate current if open frame
		if (currentFrameNum == 0) {
			//if secontRoll is a spare, do nothing yet.
			if (scoreSheet[currentFrameNum].isSpare || scoreSheet[currentFrameNum].isStrike) {
				scoreSheet[currentFrameNum].setFrameScore(10);
				return;
			}
			else { //if open frame,  
				scoreSheet[currentFrameNum].setFrameScore(scoreSheet[currentFrameNum].getFirstRoll() + scoreSheet[currentFrameNum].getSecondRoll());
			}
		}
		else {  // if not in the first frame, add scores for current frame and to last spares or strikes.
			scoreSheet[currentFrameNum].setFrameScore(scoreSheet[currentFrameNum].getFirstRoll() + scoreSheet[currentFrameNum].getSecondRoll());
			
			//If previous frame was a strike, add this score to it.
			if (scoreSheet[currentFrameNum-1].isStrike) {
				scoreSheet[currentFrameNum-1].setFrameScore(scoreSheet[currentFrameNum-1].getFrameScore() + scoreSheet[currentFrameNum].getSecondRoll());
			}
		}
	}
	
	//If final frame first roll was a strike, add this to the frameScore. If second roll was spare, add this to frameScore
	private void calculateThird() {
		scoreSheet[currentFrameNum].setFrameScore(scoreSheet[currentFrameNum].getFrameScore() + scoreSheet[currentFrameNum].getThirdRoll());
	}
	
	public void setPlayerName (String name) {
		this.playerName = name;
	}
	
	// Score sheet printer
	public void calcAndPrintScores() {
		//System.out.println(playerName + "'s Bowling Game");
		System.out.println("Frame\tScores\tBonus\tFrame\tTotal");
		for (int i = 0; i < scoreSheet.length-1; i++) {
			currentScore += scoreSheet[i].getFrameScore();
			System.out.println((i+1) + ":\t" + scoreSheet[i].getFirstRoll() + "/" + scoreSheet[i].getSecondRoll() + "\t" + (scoreSheet[i].isStrike?"X":"") + (scoreSheet[i].isSpare?"/":"") + "\t" + scoreSheet[i].getFrameScore() + "\t" + currentScore); 
		}
		currentScore += scoreSheet[9].getFrameScore();
		System.out.println("10:\t" + scoreSheet[9].getFirstRoll() + "/" + scoreSheet[9].getSecondRoll() + "/" + scoreSheet[9].getThirdRoll()+ "\t" + (scoreSheet[9].isStrike?"X":"") + (scoreSheet[9].isSpare?"/":"") + "\t" + scoreSheet[9].getFrameScore()  + "\t" + currentScore);
		System.out.println("Final Score: " + currentScore);
	}
	
	public int rollRandom(int remainingPins) {
		int roll = (int) (Math.random()*(remainingPins + 1)); // normal stochastic distribution
		return roll;
	}
}
