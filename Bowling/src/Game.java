//Game object, used to execute the work flow of a game
//stores information in the frame objects
//keeps a scoreSheet object for all the scores.
public class Game 	{
	//Properties of a game
	public static int currentFrameNum;
	public static int currentScore;
	public static Frame[] scoreSheet;
	
	// Constructor - resets the game to zeros.
	public Game() 	{
		Game.currentFrameNum = 0;
		Game.currentScore = 0;
		Game.scoreSheet = new Frame[10];
	}
	
	//This kicks off the game logic, including the 10th Frame.
	public void start() {
		while (currentFrameNum < 9) { //first 9 frames
			bowlFrame();
			currentFrameNum++;
		}
		bowlFinalFrame();
	}
	
	//bowling logic - no scores are tallied - just frame properties
	public void bowlFrame() {
		Game.scoreSheet[currentFrameNum] = new Frame();
		Game.scoreSheet[currentFrameNum].setFirstRoll(); //bowl at 10 pins
		
		if (Game.scoreSheet[currentFrameNum].getFirstRoll() == 10) { //strike!
			Game.scoreSheet[currentFrameNum].isStrike = true ;
			calculateFirst();
			return;
		}
		else { //this was not a strike
			calculateFirst();
			Game.scoreSheet[currentFrameNum].setSecondRoll(10-Game.scoreSheet[currentFrameNum].getFirstRoll()); //bowl at remaining pins
			
			if (Game.scoreSheet[currentFrameNum].getSecondRoll()+Game.scoreSheet[currentFrameNum].getFirstRoll() == 10) { //Spare!
				Game.scoreSheet[currentFrameNum].isSpare = true;
			}
		}
		calculateSecond();
		return;
	}
	
	//Logic for final frame here
	private void bowlFinalFrame() {
		Game.scoreSheet[currentFrameNum] = new Frame();
		Game.scoreSheet[currentFrameNum].setFirstRoll();
		if (Game.scoreSheet[currentFrameNum].getFirstRoll() == 10) { //strike! Get a third roll
			Game.scoreSheet[currentFrameNum].isStrike = true;
			calculateFirst();
			Game.scoreSheet[currentFrameNum].setSecondRoll(10); //get clean frame on second roll
			calculateSecond();
			if (Game.scoreSheet[currentFrameNum].getSecondRoll() == 10) { //strike on second roll
				Game.scoreSheet[currentFrameNum].setThirdRoll(10); //Clean frame on third roll
				calculateThird();
			}
			else { //not a strike on second roll
				Game.scoreSheet[currentFrameNum].setThirdRoll(10-Game.scoreSheet[currentFrameNum].getSecondRoll()); //bowl at remaining pins
				calculateThird();
			}
		}
		else { //first roll in final frame was not a strike
			calculateFirst();
			Game.scoreSheet[currentFrameNum].setSecondRoll(10-Game.scoreSheet[currentFrameNum].getFirstRoll()); //bowl second at remaining pins
			if (Game.scoreSheet[currentFrameNum].getSecondRoll()+Game.scoreSheet[currentFrameNum].getFirstRoll() == 10) { //Spare! Get a third roll
				Game.scoreSheet[currentFrameNum].isSpare = true;
				calculateSecond();
				Game.scoreSheet[currentFrameNum].setThirdRoll(10); //get a clean 3rd roll 
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
		if (Game.currentFrameNum == 0) {
			if (Game.scoreSheet[currentFrameNum].isStrike) {
				Game.scoreSheet[currentFrameNum].setFrameScore(10);
			}
			return;
		}
		else { //For the Second Frame and beyond, look backward
			//if last frame was a spare, add this Frame's firstRoll to the finalScore of the previous frame
			if (Game.scoreSheet[currentFrameNum-1].isSpare) {
				Game.scoreSheet[currentFrameNum-1].setFrameScore(Game.scoreSheet[currentFrameNum-1].getFrameScore() + Game.scoreSheet[currentFrameNum].getFirstRoll());
			}
			//if last frame was a strike, add this Frame's firstRoll to the finalScore of the previous frame, check previous for strike
			if (Game.scoreSheet[currentFrameNum-1].isStrike) {
				Game.scoreSheet[currentFrameNum-1].setFrameScore(Game.scoreSheet[currentFrameNum-1].getFrameScore() + Game.scoreSheet[currentFrameNum].getFirstRoll());
				
				if (currentFrameNum > 1 ) { //if on 3rd frame and two back and one back were strikes, add this to current score
					if (Game.scoreSheet[currentFrameNum-2].isStrike) {
						Game.scoreSheet[currentFrameNum-2].setFrameScore(Game.scoreSheet[currentFrameNum-2].getFrameScore() + Game.scoreSheet[currentFrameNum].getFirstRoll());
					}
				}
			} 
			//if firstRoll and is strike, do nothing yet on current frame except for setting the frameScore to 10.
			if (Game.scoreSheet[currentFrameNum].firstRoll == 10) {
				Game.scoreSheet[currentFrameNum].setFrameScore(10);
				return; 
			}
		}
	}
	
	//Check for current spares or open frames & for previous strikes, sets frameScores accordingly
	private void calculateSecond() {
		//if first frame, no need to look back. Just calculate current if open frame
		if (Game.currentFrameNum == 0) {
			//if secontRoll is a spare, do nothing yet.
			if (Game.scoreSheet[currentFrameNum].isSpare || Game.scoreSheet[currentFrameNum].isStrike) {
				Game.scoreSheet[currentFrameNum].setFrameScore(10);
				return;
			}
			else { //if open frame,  
				Game.scoreSheet[currentFrameNum].setFrameScore(Game.scoreSheet[currentFrameNum].getFirstRoll() + Game.scoreSheet[currentFrameNum].getSecondRoll());
			}
		}
		else {  // if not in the first frame, add scores for current frame and to last spares or strikes.
			//if secontRoll is a spare, do nothing yet.
			if (Game.scoreSheet[currentFrameNum].isSpare || Game.scoreSheet[currentFrameNum].isStrike) {
				Game.scoreSheet[currentFrameNum].setFrameScore(10);
			}
			else { //if open frame,  
				Game.scoreSheet[currentFrameNum].setFrameScore(Game.scoreSheet[currentFrameNum].getFirstRoll() + Game.scoreSheet[currentFrameNum].getSecondRoll());
			}
			
			//If last frame was a strike, add this score to it.
			if (Game.scoreSheet[currentFrameNum-1].isStrike) {
				Game.scoreSheet[currentFrameNum-1].setFrameScore(Game.scoreSheet[currentFrameNum-1].getFrameScore() + Game.scoreSheet[currentFrameNum].getSecondRoll());
			}
			
			//if secondRoll, and is open, first + second = frameScore
			if (Game.scoreSheet[currentFrameNum].getFirstRoll() + Game.scoreSheet[currentFrameNum].getSecondRoll() < 10) {
				Game.scoreSheet[currentFrameNum].setFrameScore(Game.scoreSheet[currentFrameNum].getFirstRoll() + Game.scoreSheet[currentFrameNum].getSecondRoll());
			}
			
			//if final frame and second roll is a strike, add this to the framescore
			if (currentFrameNum == 9 && Game.scoreSheet[currentFrameNum].getSecondRoll() == 10 ) {
				Game.scoreSheet[currentFrameNum].setFrameScore(Game.scoreSheet[currentFrameNum].getFrameScore() + Game.scoreSheet[currentFrameNum].getSecondRoll());
			}
		}
	}
	
	//If final frame first roll was a strike, add this to the frameScore. If second roll was spare, add this to frameScore
	private void calculateThird() {
		Game.scoreSheet[currentFrameNum].setFrameScore(Game.scoreSheet[currentFrameNum].getFrameScore() + Game.scoreSheet[currentFrameNum].getThirdRoll());
	}
	
	public void endGame() {
		printScores();
	}
	
	// Score sheet printer
	private void printScores() {
		for (int i = 0; i < scoreSheet.length-1; i++) {
			Game.currentScore += scoreSheet[i].getFrameScore();
			System.out.println((i+1) + ":\t" + scoreSheet[i].getFirstRoll() + "/" + scoreSheet[i].getSecondRoll() + "\t" + (scoreSheet[i].isStrike?"X":"") + (scoreSheet[i].isSpare?"/":"")+ "\tframeScore:\t" + scoreSheet[i].getFrameScore()  + "\t" + currentScore); 
		}
		Game.currentScore += scoreSheet[9].getFrameScore();
		System.out.println("10:\t" + scoreSheet[9].getFirstRoll() + "/" + scoreSheet[9].getSecondRoll() + "/" + scoreSheet[9].getThirdRoll()+ "\t" + (scoreSheet[9].isStrike?"X":"") + (scoreSheet[9].isSpare?"/":"")+ "\tframeScore:\t" + scoreSheet[9].getFrameScore()  + "\t" + currentScore);
		System.out.println("Final Score: " + Game.currentScore);
	}
}
