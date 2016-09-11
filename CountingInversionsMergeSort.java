package ProgAssg1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountingInversionsMergeSort {
	
	public static void main (String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\prsharma\\Desktop\\tni\\ADA-coursera-part-1\\Assignments\\ProgAssg1\\input.txt"));
		
		int [] arr = new int[100000];
		String line;
		int i=0;
		while ((line=br.readLine()) != null) {
			arr[i] = Integer.parseInt(line);
			i++;
		}
		
		for (i=0;i<20;i++)
			System.out.println(arr[i]);
		
		System.out.println();
		
		long inv = countInversions(arr, 0, 99999);
		System.out.println(inv);
		
	}
	
	public static long countInversions(int [] arr, int start, int end) {
		if (start>=end)
			return 0;
		
		int mid = (start+end)/2;
		
		long inv=0;
		
		inv += countInversions(arr, start, mid);
		inv += countInversions(arr, mid+1, end);
		inv += combine(arr, start, mid, end);
		return inv;
	}
	
	public static long combine(int [] arr, int start, int mid, int end) {
		long inversions=0;
		int size = end-start+1;
		int [] tmp = new int[size];
		int i=start, j=mid+1, k=0;
		
		while (i<=mid && j<=end) {
			if (arr[i] < arr[j]) {
				tmp[k]=arr[i];
				i++;
				k++;
			} else {
				tmp[k]=arr[j];
				j++;
				k++;
				inversions += (mid-i+1);
			}
		}
		
		while (i<=mid) {
			tmp[k]=arr[i];
			i++;
			k++;
		}
		
		while (j<=end) {
			tmp[k]=arr[j];
			j++;
			k++;
		}
		
		for (i=0;i<size;i++) {
			arr[i+start] = tmp[i];
		}
		
		return inversions;
	}
	
	public static void mergeSort(int [] arr, int start, int end) {
		if (start>=end)
			return;
		
		int mid = (start+end)/2;
		
		mergeSort(arr, start, mid);
		mergeSort(arr, mid+1, end);
		merge(arr, start, mid, end);
	}
	
	public static void merge(int [] arr, int start, int mid, int end) {
		int size = end-start+1;
		int [] tmp = new int[size];
		
		int i=start, j=mid+1, k=0;
		
		while (i<=mid && j<=end) {
			if (arr[i] < arr[j]) {
				tmp[k]=arr[i];
				i++;
				k++;
			} else {
				tmp[k]=arr[j];
				j++;
				k++;
			}
		}
		
		while (i<=mid) {
			tmp[k]=arr[i];
			i++;
			k++;
		}
		
		while (j<=end) {
			tmp[k]=arr[j];
			j++;
			k++;
		}
		
		for (i=0;i<size;i++) {
			arr[i+start] = tmp[i];
		}
	}

}
