package org.ameya.puzzle.impl;

import java.lang.Math;

public class CutRod {

	public static void main(String args[])
	{
		int[][] price = {	{1,2,3,4,5,6,7,8,9,10},
							{1,5,8,9,10,17,17,20,27,29} };
		
		int lengthOfRod = 10;
		
		int revenueGreedy = cutRodGreedy(price, lengthOfRod);
		int revenueDynamic = cutRodDynamic(price, lengthOfRod);
		System.out.println("\nMax revenue using Greedy approach is :" + revenueGreedy
							+"\nMax revenue using Dynamic prog. approach is: " + revenueDynamic);	
	}

	public static int cutRodDynamic(int[][] price, int length)
	{
		int priceArrayLength = price[0].length;
		int[] revenue= new int[priceArrayLength+1];
		int[] rodCut = new int[priceArrayLength+1];
		revenue[0]=0;
		
		for(int i=1; i<=priceArrayLength; i++)
		{
			int maxRevenue= -99; 
			
			for(int j=1; j<=i; j++)
			{
				int currMax = maxRevenue;
				maxRevenue = Math.max(maxRevenue, price[1][j-1] + revenue[i-j]);
				if (currMax != maxRevenue)
				{	
					rodCut[i] = j;
				}
			}
			revenue[i] = maxRevenue;
		}
		
		int len = length;
		//System.out.print("\nOptimal rod cuts for rod of length "+length+": ");
		while( len > 0)
		{
			System.out.print(rodCut[len]+" ");
			len = len - rodCut[len]; 
		}
		return revenue[length];
	}
	
	public static int cutRodGreedy(int[][] price, int length)
	{
		int priceArrayLength = price[0].length;
		int cutIndex = 0;
		int optimumRevenue = 0;
		
		float[] pricePerUnit = new float[priceArrayLength];
		
		for(int i=0; i<priceArrayLength; i++)
			pricePerUnit[i] = (float)price[1][i] / price[0][i];
		
		//System.out.print("\nOptimal rod cuts for rod of length "+length+": ");
		
		while(length>0)
		{
			cutIndex = getGreedyChoice(pricePerUnit,length);
			optimumRevenue += price[1][cutIndex-1];
			length -= cutIndex;
			System.out.print(cutIndex+" ");
		}
		return optimumRevenue;
	}
	
	private static int getGreedyChoice(float[] value, int length)
	{
		int choice = 0;
		float greedyChoiceValue = 0;
		for(int i=0 ; i<length ; i++)
		{
			if(value[i] >= greedyChoiceValue)
			{
				greedyChoiceValue = value[i];
				choice = i;
			}
		}
		return choice + 1;
	}
}
