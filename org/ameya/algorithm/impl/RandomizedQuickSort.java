package org.ameya.algorithm.impl;

import java.util.Random;

/**
 * @author Ameya Advankar
 */
public class RandomizedQuickSort {
	
	/**
	 * Sorts the <code>input</code> array in ascending order using Randomized QuickSort.<br/>
	 * The pivot is arbitrarily chosen
	 * @param input The integer array to be sorted
	 * @return <b>int[]</b> Sorted integer array
	 */
	public static int[] sort(int[] input)
	{
		performRandomizedQuickSort(input, 0, input.length-1);
	
		return input;
	}
	
	private static void performRandomizedQuickSort(int[] input, int start, int end)
	{
		
		if( start < end)
		{
			int pivot_location = randomizedPartition(input, start, end);
			performRandomizedQuickSort(input, start, pivot_location-1);
			performRandomizedQuickSort(input, pivot_location+1, end);
		}
	}
	
	private static int randomizedPartition(int[] input, int start, int end)
	{
		int range = end - start + 1;
		Random random = new Random();
		int randomPivotIndex = start + random.nextInt(range);
		
		//Swap randomPivot with last element
		int temp = input[randomPivotIndex];
		input[randomPivotIndex] = input[end];
		input[end] = temp;
		
		return quickSortPartition(input, start, end);
		
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
