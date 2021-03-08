//***************************************************************************************
// NonAttackingQueens.java		Author: Beau Nelford		3/29/2009
// Solves the non attacking queens problem, placing 8 queens on a chess board that cannot
// attack one another immediately.
//***************************************************************************************

package cs1Ch11Review;

public class NonAttackingQueens
{
	public static final int SIZE = 8;
	public static int[][] board = {{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0}};
	static int currX = 0;
	static int currY = 0;
	static int queensRem = 8;
	
	public static void main (String[] args)
	{
		board[3][7]=1;
		
		printAns(board);
		placeQueen(queensRem, currX, currY);
		printAns(board);
	}
	
	
	/*
	 * Recursive method for placing  
	 */
	public static void placeQueen (int remaining, int x, int y)
	{
		//iterate through the whole board, returning to the top if not all the queens are placed
		
		/*do
		{
			if (checkXs(currX, currY) && checkYs (currX, currY) && checkDiags(currX, currY))
			{
				board[x][y] = 1;
				queensRem--;
				placeQueen(queensRem, x, y);
			}
			else if (x==board.length)
			{
				y++;
			}
			else
				x++;
			
		}
		while (remaining>0);*/
		
		if (queensRem > 0)				//ensures that a recursive call stops after 8
		{
			if (checkXs(x, y) && checkYs (x, y) && checkDiags(x, y))	//checks the current position for other
			{
				board[x][y] = 1;	//places a queen
				placeQueen(queensRem, x, y);
			}
			else							//if position is bad
			{
				if (x<board.length)		//move to the next x if not there already
				{
					x++;
				}
				else						//move to the next y & return to first x
				{
					x = 0;
					y++;
				}
				board[y][x] = 1;
				placeQueen(queensRem, x, y);
			}
		}
		
		
		
		

		
	}

	public static boolean checkDiags (int x, int y)		//recursive check?
	{
		if (checkUpperRight(x,y) && checkLowerRight(x,y) && checkUpperLeft(x,y) && checkLowerLeft(x,y))
			return true;
		return false;
	}
	
	public static boolean checkUpperRight (int x, int y)
	{
		boolean answer = true;
		
		if (x == SIZE-1 || y == 0)			// base case: if it hits a border, it is clear,
			answer = true;
		else
		{
			if (board[x+1][y-1] == 0)		//if it is clear, run it again
				answer = checkUpperRight(x+1, y-1);
			if (board[x+1][y-1] == 1)		// primary base case: if the square to the upper right has a queen, not clear
				answer = false;
		}
		return answer;
	}
	
	public static boolean checkLowerRight (int x, int y)
	{
		boolean answer = true;
		
		if (x == 0 || y == SIZE-1)			// base case: if it hits a border, it is clear,
			answer = true;
		else
		{
			if (board[x+1][y+1] == 0)		//if it is clear, run it again
				answer = checkLowerRight(x+1, y+1);
			if (board[x+1][y+1] == 1)		// primary base case: if the square to the upper right has a queen, not clear
				answer = false;
		}
		return answer;
	}
	
	public static boolean checkUpperLeft (int x, int y)
	{
		boolean answer = true;
		
		if (x == 0 || y == 0)			// base case: if it hits a border, it is clear,
			answer = true;
		else
		{
			if (board[x-1][y-1] == 0)		//if it is clear, run it again
				answer = checkUpperLeft(x-1, y-1);
			if (board[x-1][y-1] == 1)		// primary base case: if the square to the upper right has a queen, not clear
				answer = false;
		}
		return answer;
	}
	public static boolean checkLowerLeft (int x, int y)
	{
		boolean answer = true;
		
		if (x == 0 || y == 0)			// base case: if it hits a border, it is clear,
			answer = true;
		else
		{
			if (board[x-1][y+1] == 0)		//if it is clear, run it again
				answer = checkUpperLeft(x-1, y+1);
			if (board[x-1][y+1] == 1)		// primary base case: if the square to the upper right has a queen, not clear
				answer = false;
		}
		return answer;
	}
	
	public static void printAns(int[][] answer)
	{
		for (int i = 0; i<answer.length; i++)			//x axis
		{
			for (int j = 0; j<answer.length; j++)		//y axis
			{
				System.out.print(answer[i][j] + " ");
			}
			System.out.println();
		}
	}
}