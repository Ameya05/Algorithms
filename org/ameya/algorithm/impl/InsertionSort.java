package org.ameya.algorithm.impl;

/**
 * @author Ameya Advankar
 */
public class InsertionSort {

	/**
	 * Sorts the <code>input</code> array in ascending order using Insertion sort.
	 * @param input The integer array to be sorted
	 * @return <b>int[]</b> Sorted integer array
	 */
	public static int[] sort(int[] input)
	{
		int x;
		
		for (int i=1; i<input.length; i++)
		{
			x=input[i];
			
			for(int j=0; j<i; j++)
			{
				if( x < input[j])
				{
					insert(input, i, j);
					break;
				}
			}
		}
		return input;
	}
	
	private static void insert(int[] inArray,int originalPosition, int newPosition)
	{
		int valueToInsert = inArray[originalPosition];
		
		//Loop over array and right shift values from newPosition to originalPosition-1
		for(int i = originalPosition-1 ; i >= newPosition ; i--)
		{
			inArray[i+1] = inArray[i];
		}
		
		inArray[newPosition] = valueToInsert;
	}

}
