package no2_2017;

public class Solution {

	public static void main(String[] args) {
		String dartResult = "1S*2T*3S";
		char[] temp = dartResult.toCharArray();
		
		int[] score = new int[3];
		char[] bonus = new char[3];
		char[] sang = new char[3];
		
		int index=-1;
		for(int i=0; i<temp.length; i++) {
			if(temp[i]>=48 && temp[i]<=57) {
				if(temp[i]==48) {
					if(i-1 >= 0) {
						if(temp[i-1]==49) {
							score[index]=10;
						}
						else {
							index++;
							score[index] = 0;
						}
					}
					else {
						index++;
						score[index]=0;
					}
				}
				else {
					index++;
					score[index]=temp[i]-48;
				}
			}
			else if(temp[i]=='S' || temp[i]=='D' || temp[i]=='T') {
				bonus[index]=temp[i];
			}
			else {
				sang[index]=temp[i];
			}
		}
		
		int[] answerscore = new int[3];
		
		for(int i=0; i<3; i++) {
			int temp_score= score[i];
			if(bonus[i]=='D') {
				temp_score = (int)Math.pow(temp_score, 2);
			}
			else if(bonus[i]=='T')  {
				temp_score = (int)Math.pow(temp_score, 3);
			}
			answerscore[i] = temp_score;
			
			if(sang[i]=='*') {
				if(i==0) {
					answerscore[i] *= 2;
				}
				else {
					answerscore[i] *= 2;
					answerscore[i-1] *= 2;
				}
			}
			else if(sang[i]=='#'){
				answerscore[i] *= -1;
			}
			
		}
		
		System.out.println(answerscore[0]+answerscore[1]+answerscore[2]);
		

	}

}
