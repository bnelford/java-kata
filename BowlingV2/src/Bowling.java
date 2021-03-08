// This class means to create an object domain of a game, which contains frames
public class Bowling {
	public static void main(String[] args) {
		Game game = new Game("Fred");
		for (int frameIndex = 0; frameIndex < 9; frameIndex++) {
			int first = game.rollRandom(10);
			int second = game.rollRandom(10-first);
			game.bowlFrame(first, second);
		}
		int first = game.rollRandom(10);
		int second = game.rollRandom(10-first);
		game.bowlFinalFrame(first, second, 0);
		game.calcAndPrintScores();
	}
}
