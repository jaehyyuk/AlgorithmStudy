package no1_2017;

import java.util.*;
public class Solution {

	public static void main(String[] args) {
		
		int n = 5; 
		int[] arr1 = {9, 20, 28, 18, 11};
		int[] arr2 = {30, 1, 21, 17, 28};
		
		String answer[] = new String[n];
		
		int[] twon = new int[n];
		
		int temp = 1;
		for(int i=(n-1); i>=0; i--) {
			twon[i] = temp;
			temp *=2;
		}
		
		
		
		for(int i=0; i<n; i++) {
			int fir = arr1[i];
			int sec = arr2[i];
			
			int[] firn = new int[n];
			int[] secn = new int[n];
			
			for(int j=0; j<n; j++) {
				if(fir/twon[j]==1) {
					firn[j]=1;
					fir = fir-twon[j];
				}
				else {
					firn[j]=0;
				}
				if(sec/twon[j]==1) {
					secn[j]=1;
					sec = sec - twon[j];
				}
				else {
					secn[j]=0;
				}
			}
			String sb = new String();
			for(int j=0; j<n; j++) {
				if(firn[j]==0 && secn[j]==0) {
					sb = sb+" ";
				}
				else {
					sb = sb+"#";
				}
			}
			
			
			answer[i] = sb;
			
			
		}
		
		//return answer;

	}

}
