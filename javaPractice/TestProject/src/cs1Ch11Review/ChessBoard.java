//*****************************************************************************
// ChessBoard.java		Author: Beau Nelford	3/31/2009
// Creates a chessboard with an 8 x 8 arrangement where other methods can be
// tested.
//*****************************************************************************

package cs1Ch11Review;

import java.awt.Point;
import java.util.Random;

public class ChessBoard
{
	int[][] board;
	static int size;
	Random generator = new Random();

	public ChessBoard (int[][] setup)		//!!!be careful as the x and y coordinates are backwards: board[y][x]
	{
		board = setup;
		size = board.length;
	}
	
	public void printBoard ()
	{
		for (int i = 0; i<board.length; i++)			//y axis
		{
			for (int j = 0; j<board.length; j++)		//x axis
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public int countQueens()
	{
		int count = 0;
		for (int i = 0; i<board.length; i++)			//y axis
		{
			for (int j = 0; j<board[i].length; j++)		//x axis
			{
				if (board[i][j] ==1)
					count++;		
			}
		}
		return count;
	}
	
	public void solve ()
	{
		int ok = 0;
		while (ok<8)
		{
			boolean done = false;
			ok= 0;
			
			populate(8);
			for (int i = 0; i<board.length; i++)			//y axis
			{
				for (int j = 0; j<board[i].length; j++)		//x axis
				{
					if (board[i][j] == 1)
					{
						done = checkPosition(j, i);	
						if (done)
							ok++;
					}
				}
			}
		}
	}
	
	public void populate (int toBePlaced)
	{
		Point random;
		int count=0;
		do
		{
			random = randomize();
			if (empty(random.x, random.y))
			{
				placeQueen(random.x, random.y);
				count++;
			}
			
		}
		while(count<8);
	}
	
	public void clear ()
	{
		for (int i = 0; i<board.length; i++)			//y axis
		{
			for (int j = 0; j<board[i].length; j++)		//x axis
			{
				board[i][j]=0;
			}
		}
	}
	
	public boolean hasSpaces ()
	{
		boolean clear = false;
		int atLeastOne = 0;
		
		for (int i = 0; i<board.length; i++)			//y axis
		{
			for (int j = 0; j<board[i].length; j++)		//x axis
			{
				if (checkPosition(j, i))
					atLeastOne++;
			}
		}
		
		if (atLeastOne>0)
			clear = true;
		return clear;
	}
	
	public void placeQueen(int x, int y)
	{
		if (empty(x,y))
			board[y][x] = 1;
	}
	
	public Point randomize ()
	{
		Random generator = new Random();
		int x = Math.abs(generator.nextInt()%8);
		int y = Math.abs(generator.nextInt()%8);
		Point point = new Point (x,y);
		return point;
	}
	
	public Point iterate (int x, int y)
	{
		int xVal = x;
		int yVal = y;
		
		if (xVal < 7)			//if you can move to the right, do.
			xVal++;
		else					//if you cannot move to the right, return to the left & next row
		{			
			xVal = 0;
			
			if (yVal < 7)		//if you can move to a new row, do;
				yVal++;
			else				//if you cannot move to the next row, return to the beginning
				yVal = 0;
		}
		Point point = new Point(xVal, yVal);
		return point;
	}
	
	public boolean empty (int x, int y)
	{
		if (board[y][x] == 0)
			return true;
		else
			return false;
		
	}
	
	public boolean checkPosition (int x, int y)
	{
		if (checkXs(x,y) && checkYs(x,y) && checkDiags(x, y) && empty(x,y))
			return true;
		return false;
	}
	
	
	public boolean checkXs (int x, int y)
	{
		if (checkXRight(x,y) && checkXLeft(x,y))
			return true;
		return false;
	}
	
	public boolean checkXRight (int x, int y)
	{
		boolean answer;
		
		if (x >= 7)			// base case: if it hits a border, it is clear
		{
			answer = true;
//			System.out.println("The rest of the " + y + " row to the right is clear");
		}
		else
		{	
			if (board[y][x+1] == 1)		// primary base case: if the square to the right has a queen, not clear
			{
				answer = false;
//				System.out.println("The space to the right of " + x + "," + y + " is NOT clear");
			}
			else						//if it is clear, run it again
			{
//				System.out.println("The space to the right of " + x + "," + y + " is clear");
				answer = checkXRight(x+1, y);
			}
				
		}
		return answer;
	}
	
	public boolean checkXLeft (int x, int y)
	{
		boolean answer;
		
		if (x <= 0)			// base case: if it hits a border, it is clear,
		{
			answer = true;
//			System.out.println("The rest of the " + y + " row to the left is clear");
		}
		else
		{
			if (board[y][x-1] == 1)		// primary base case: if the square to the right has a queen, not clear
			{
				answer = false;
//				System.out.println("The space to the left of " + x + "," + y + " is NOT clear");
			}
			else						//if it is clear, run it again
			{
//				System.out.println("The space to the left of " + x + "," + y + " is clear");
				answer = checkXLeft(x-1, y);
			}
		}
		return answer;
	}
	
	public boolean checkYs (int x, int y)
	{
		if (checkYUp(x,y) && checkYDown(x,y))
			return true;
		return false;
	}
	
	public boolean checkYDown (int x, int y)
	{
		boolean answer;
		
		if (y >= 7)			// base case: if it hits a border, it is clear,
		{
			answer = true;
//			System.out.println("The rest of the " + x + " column below is clear");
		}
		else
		{
			if (board[y+1][x] == 1)		// primary base case: if the square to the right has a queen, not clear
			{
				answer = false;
//				System.out.println("The space below " + x + "," + y + " is NOT clear");
			}
			else		//if it is clear, run it again
			{
//				System.out.println("The space below " + x + "," + y + " is clear");
				answer = checkYDown(x, y+1);
			}
		}
		return answer;
	}
	
	public boolean checkYUp (int x, int y)
	{
		boolean answer;
		
		if (y <= 0)			// base case: if it hits a border, it is clear,
		{
			answer = true;
//			System.out.println("The rest of the " + x + " column above is clear");
		}
		else
		{	
			if (board[y-1][x] == 1)		// primary base case: if the square to the right has a queen, not clear
			{
				answer = false;
//				System.out.println("The space above " + x + "," + y + " is NOT clear");
			}
			else	//if it is clear, run it again
			{
//				System.out.println("The space above " + x + "," + y + " is clear");
				answer = checkYUp(x, y-1);
			}

		}
		return answer;
	}
	
	public boolean checkDiags (int x, int y)		//recursive check?
	{
		if (checkUpperRight(x,y) && checkLowerRight(x,y) && checkUpperLeft(x,y) && checkLowerLeft(x,y))
			return true;
		return false;
	}
	
	public  boolean checkUpperRight (int x, int y)
	{
		boolean answer;
		
		if (x >= 7 || y <= 0)			// base case: if it hits a border, it is clear,
		{
//			System.out.println("The rest of the upper right diagnol is clear");
			answer = true;
		}
		else
		{
			if (board[y-1][x+1] == 1)		// primary base case: if the square to the upper right has a queen, not clear
			{
				answer = false;
//				System.out.println("The space above and to the right of " + x + "," + y + " is NOT clear");
			}
			else		//if it is clear, run it again
			{
//				System.out.println("The space above and to the right of " + x + "," + y + " is clear");
				answer = checkUpperRight(x+1, y-1);
			}
		}
		return answer;
	}
	
	public boolean checkLowerRight (int x, int y)
	{
		boolean answer;
		
		if (x >= 7 || y >= 7)			// base case: if it hits a border, it is clear,
		{
//			System.out.println("The rest of the lower right diagnol is clear");
			answer = true;
		}
		else
		{
			if (board[y+1][x+1] == 1)		// primary base case: if the square to the upper right has a queen, not clear
			{
				answer = false;
//				System.out.println("The space below and to the right of " + x + "," + y + " is NOT clear");
			}
			else		//if it is clear, run it again
			{
//				System.out.println("The space below and to the right of " + x + "," + y + " is clear");
				answer = checkLowerRight(x+1, y+1);
			}
		}
		return answer;
	}
	
	public boolean checkUpperLeft (int x, int y)
	{
		boolean answer;
		
		if (x <= 0 || y <= 0)			// base case: if it hits a border, it is clear,
		{
//			System.out.println("The rest of the upper left diagnol is clear");
			answer = true;
		}
		else
		{
			if (board[y-1][x-1] == 1)		// primary base case: if the square to the upper right has a queen, not clear
			{
				answer = false;
//				System.out.println("The space above and to the left of " + x + "," + y + " is NOT clear");
			}
			else		//if it is clear, run it again
			{
//				System.out.println("The space below and to the right of " + x + "," + y + " is clear");
				answer = checkUpperLeft(x-1, y-1);
			}
		}
		return answer;
	}
	
	public boolean checkLowerLeft (int x, int y)
	{
		boolean answer;
		
		if (x <= 0 || y >= 7)			// base case: if it hits a border, it is clear,
		{
//			System.out.println("The rest of the lower left diagnol is clear");
			answer = true;
		}
		else
		{
			if (board[y+1][x-1] == 1)		// primary base case: if the square to the upper right has a queen, not clear
			{
				answer = false;
//				System.out.println("The space below and to the left of " + x + "," + y + " is NOT clear");
			}
			else		//if it is clear, run it again
			{
//				System.out.println("The space below and to the left of " + x + "," + y + " is clear");
				answer = checkLowerLeft(x-1, y+1);
			}

		}
		return answer;
	}
}