package org.ameya.puzzle.impl;

/**
 * @author Ameya Advankar
 * 
 * ------------------------
 * Problem Statement
 * ------------------------
 * Given a matrix where every cell has some number of coins. Count number of ways to reach bottom right
 * from top left with exactly k coins. We can move to (i+1, j) and (i, j+1) from a cell (i, j).
 * EXAMPLE: , the entries of M represent the number of coins in each cell. k = 12,
 * M =  1 2 3
 *		4 6 5
 *		3 2 1
 * The two possible paths are:
 * 1. 1 -> 2 -> 6 -> 2 -> 1
 * 2. 1 -> 2 -> 3 -> 5 -> 1
 * ------------------------
 */


public class MatrixCoin 
{
	public static void main(String[] args) 
	{	
		//Matrix input which denotes the number of coins in each cell
		int[][] coins = { {1,2,3},
						  {4,6,5},
						  {3,2,1} };

		int k = 12;
		
		findPathsWithKCoins(coins, k);
	}

	public static void findPathsWithKCoins(int[][] coins, int k) {
		/*
		 * m = number of rows in input matrix
		 * n = number of columns in input matrix
		 */
		int m = coins.length;
		int n = coins[0].length;
		
		/*
		 * Calculate max possible paths.
		 * Since we have to move m-1 steps down and n-1 steps to the right,
		 * the problem is similar to performing m-1 down & n-1 right steps in a total of (m-1+n-1) steps
		 * using formula (m-1 + n-1)!/ (m-1)!*(n-1)!
		 */
		int maxPaths = 1;
		for (int i = n; i < (m + n - 1); i++) {
			maxPaths = maxPaths*i;
			maxPaths = maxPaths/(i - n + 1);
	    }
		
		//Number of paths till a particular cell
		int[][] traversedPaths=new int[m][n];
		
		//3D Array to keep track of cost of each path till that cell
		int[][][] traversedCost=new int[m][n][maxPaths];
		
		/*
		 * Initialize the 1st cell cost to the number of coins in cell 0,0
		 * since it is be the start cell
		 */
		traversedCost[0][0][0] = coins[0][0];
		
		/*
		 * Initialize the 1st cell path to 1
		 */
		traversedPaths[0][0] = 1;
		
		/*
		 * Since the 1st row and 1st column can only be traversed in a single path 
		 * either downward or rightward, compute the entire 1st row and 1st column values
		 */
		for (int i=1; i<m; i++)
		{
			traversedCost[i][0][0] = traversedCost[i-1][0][0] + coins[i][0];
			traversedPaths[i][0] = 1;
		}
		
		for (int j=1; j<n; j++)
		{
			traversedCost[0][j][0] = traversedCost[0][j-1][0] + coins[0][j];
			traversedPaths[0][j] = 1;
		}
		
		/*
		 * Loop to traverse the entire matrix diagonally
		 * i.e. 
		 * 1st iteration: 0,0
		 * 2nd iteration: 1,0 -> 0,1
		 * 3rd iteration: 2,0 -> 1,1 -> 0,2
		 * and so on till m-1,n-1
		 */
		for(int x=1; x<=m+n-2; x++)
		{
			for(int y = 1;y <= x; y++)
			{
				//Condition to not traverse columns/rows out of matrix
				if(y <= m-1 && (x-y) <= n-1 && (x-y)>0 && y>0 )
				{
					/*
					 * There are 2 possible paths which could lead to this cell, the one above and the one to left
					 * Add current cell cost to the above cell traversedCost Array. If sum is less than K, then add path to current cell traversedPaths
					 */
					traversedPaths[y][x-y]=0;
					for(int z=0; z<traversedPaths[y-1][x-y]; z++)
					{
						if(traversedCost[y-1][x-y][z] + coins[y][x-y] <= k)
						{
							traversedCost[y][x-y][traversedPaths[y][x-y]] = traversedCost[y-1][x-y][z] + coins[y][x-y];
							traversedPaths[y][x-y]++;
						}
					}
					
					for(int z=0; z<traversedPaths[y][x-y-1]; z++)
					{
						if(traversedCost[y][x-y-1][z] + coins[y][x-y] <= k)
						{
							traversedCost[y][x-y][traversedPaths[y][x-y]] = traversedCost[y][x-y-1][z] + coins[y][x-y];
							traversedPaths[y][x-y]++;
						}
					}
				}
			}
		}
		int feasiblePaths=0;
		for(int i=0; i<traversedPaths[m-1][n-1]; i++)
		{
			if(traversedCost[m-1][n-1][i] == k)
				feasiblePaths++;
		}
		System.out.println("Total possible paths from top left to bottom right in "+k+" coins = "+feasiblePaths);
	}
}
