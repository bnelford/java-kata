//*********************************************************************************
// TestDriver.java		Author: Beau Nelford		3/31/2009
// A driver application to test the various chess methods contained in the
// ChessBoard class
//*********************************************************************************

package cs1Ch11Review;

import java.util.Random;

public class TestChessDriver
{
	public static final int QUEENS = 8;
	public static int[][] tester = {{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0}};
	
	public static void main (String[] args)
	{	
		Random generator = new Random();
		ChessBoard board = new ChessBoard (tester);
		boolean outcome = true;
//		int[] newVals = board.iterate(7, 7);
//		System.out.println(newVals[0] + "," + newVals[1]);
		
//		board.printBoard();
//		System.out.println();
//		int tries = Math.abs(generator.nextInt()%8);
//		int count = 0;
//
//		
			board.solve();
//			count = board.countQueens();
//			tries++;
//			System.out.println(tries);
//		
		
//		boolean atLeastOne = board.hasSpaces();
		
//		System.out.println(atLeastOne);
		
//		System.out.println(outcome);
		
		board.printBoard();
		System.out.println(board.countQueens());
		
/*		int x = 0;
		int y = 3;
		
		boolean left = board.checkXRight(x, y);
		boolean right = board.checkXLeft(x, y);
		boolean bothxs = board.checkXs(x, y);
		
		boolean down = board.checkYDown(x, y);
		boolean up = board.checkYUp(x, y);
		boolean bothys = board.checkYs(x, y);
		
		boolean upRight = board.checkUpperRight(x, y);
		boolean loRight = board.checkLowerRight(x, y);
		boolean upLeft = board.checkUpperLeft(x, y);
		boolean loLeft = board.checkLowerLeft(x, y);
		boolean diags = upRight && loRight && upLeft && loLeft;
		
		boolean all = board.checkPosition(x, y);
		
		System.out.println("left: " + left + ", right: " + right + ", Xs: " + bothxs);
		System.out.println("down: " + down + ", up: " + up + ", Ys: " + bothys);
		System.out.println("upper Right diag: " + upRight);
		System.out.println("lower Right diag: " + loRight);
		System.out.println("upper Left diag: " + upLeft);
		System.out.println("lower Left diag: " + loLeft);
		
		
		System.out.println("Can I put a queen here?: "+ x + "," + y + " " + all);
		*/
	}
}