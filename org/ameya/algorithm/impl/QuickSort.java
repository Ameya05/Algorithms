package org.ameya.algorithm.impl;

/**
 * @author Ameya Advankar
 */
public class QuickSort {
	
	/**
	 * Sorts the <code>input</code> array in ascending order using fixed pivot QuickSort.<br/>
	 * The pivot is always taken as the last element of the array.
	 * @param input The integer array to be sorted
	 * @return <b>int[]</b> Sorted integer array
	 */
	public static int[] sort(int[] input)
	{
		performQuickSort(input, 0, input.length-1);
	
		return input;
	}
	
	private static void performQuickSort(int[] input, int start, int end)
	{
		
		if( start < end)
		{
			int pivot_location = quickSortPartition(input, start, end);
			performQuickSort(input, start, pivot_location-1);
			performQuickSort(input, pivot_location+1, end);
		}
	}

	private static int quickSortPartition(int[] input, int start, int end)
	{
		int pivot = input[end];
		int i = start-1;
		int temp;
		
		for ( int j=start ; j<=end-1 ; j++)
		{
			if( input[j] <= pivot )
			{
				i++;
				temp = input[j];
				input[j] = input[i];
				input[i] = temp;
			}
		}
		
		temp = input[i+1];
		input[i+1] = input[end];
		input[end] = temp;
		
		return i+1;
	}
}
