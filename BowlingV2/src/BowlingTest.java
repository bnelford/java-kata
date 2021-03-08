import junit.framework.TestCase;

public class BowlingTest extends TestCase {
	private Game g;
	
	protected void setUp() throws Exception {
		g = new Game("Fred");
	}
	
	private void bowlMany(int remainingFrames, int roll) {
		for (int i = 0; i< remainingFrames; i++) {
			g.bowlFrame(roll, roll);
		}
	}
	
	public void testGutter() throws Exception {
		bowlMany(10,0);
		g.calcAndPrintScores();
		assertEquals(0,g.currentScore);
	} 
	
	public void testAllOnes() throws Exception {
		bowlMany(10,1);
		g.calcAndPrintScores();
		assertEquals(20,g.currentScore);
	}
	
	public void testOneSpareOnFirst() throws Exception {
		g.bowlFrame(5, 5);
		g.bowlFrame(3,3);
		bowlMany(8,0);
		g.calcAndPrintScores();
		assertEquals(19,g.currentScore);
	}
	
	public void testOneSpareInMiddle() throws Exception {
		bowlMany(4,0);
		g.bowlFrame(5, 5);
		g.bowlFrame(3,3);
		bowlMany(4,0);
		g.calcAndPrintScores();
		assertEquals(19,g.currentScore);
	}
	
	public void testOneSpareAtEnd() throws Exception {
		bowlMany(9,0);
		g.bowlFinalFrame(5, 5, 5);
		g.calcAndPrintScores();
		assertEquals(15,g.currentScore);
	}
	
	public void testOneStrikeOnFirst() throws Exception {
		g.bowlFrame(10, 0);
		g.bowlFrame(3,3);
		bowlMany(8,0);
		g.calcAndPrintScores();
		assertEquals(22,g.currentScore);
	}
	
	public void testOneStrikeInMiddle() throws Exception {
		bowlMany(4,0);
		g.bowlFrame(10, 0);
		g.bowlFrame(3,3);
		bowlMany(4,0);
		g.calcAndPrintScores();
		assertEquals(22,g.currentScore);
	}
	
	public void testTurkeyInMiddle() throws Exception {
		bowlMany(4,0);
		g.bowlFrame(10, 0);
		g.bowlFrame(10,0);
		g.bowlFrame(10,0);
		bowlMany(3,0);
		g.calcAndPrintScores();
		assertEquals(60,g.currentScore);
	}
	
	public void testTurkeyAtStart() throws Exception {
		g.bowlFrame(10, 0);
		g.bowlFrame(10,0);
		g.bowlFrame(10,0);
		bowlMany(7,0);
		g.calcAndPrintScores();
		assertEquals(60,g.currentScore);
	}
	
	public void testTurkeyAtEnd() throws Exception {
		bowlMany(9,0);
		g.bowlFinalFrame(10, 10, 10);
		g.calcAndPrintScores();
		assertEquals(30,g.currentScore);
	}
	
	public void testOneStrikeAtEnd() throws Exception {
		bowlMany(9,0);
		g.bowlFinalFrame(10, 7, 3);
		g.calcAndPrintScores();
		assertEquals(20,g.currentScore);
	}
	
	public void testPerfectGame() throws Exception {
		bowlMany(9,10);
		g.bowlFinalFrame(10, 10, 10);
		g.calcAndPrintScores();
		assertEquals(300,g.currentScore);
	}
}
