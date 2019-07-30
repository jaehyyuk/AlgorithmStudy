package no14891톱니바퀴;

import java.io.*;
import java.util.*;

public class solve1 {
	
	static int[][] mat;
	static int[] rolling;
	static boolean[] same;
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		mat = new int[5][8];
		
		for(int i=1; i<=4; i++) {
			String st = br.readLine();
			
			for(int j=0; j<8; j++) {
				mat[i][j] = (int)st.charAt(j)-48;
			}
			
		}
		
		int roll = Integer.parseInt(br.readLine());
		int[][] rollinfo = new int[roll][2]; 
		for(int i=0; i<roll; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine()," ");
			
			rollinfo[i][0] = Integer.parseInt(st1.nextToken()); //톱니바퀴 번호
			rollinfo[i][1] = Integer.parseInt(st1.nextToken()); //어디로 회전
			
		}
		
		// 1: 시계 , -1 : 반시계
		for(int i=0; i<roll; i++) {
			rolling = new int[5];
			same = new boolean[4];
			
			for(int j=1; j<=3; j++) {
				same[j] = (mat[j][2]==mat[j+1][6]);
			}
			
			if(rollinfo[i][0] == 1) {
				rolling[1] = rollinfo[i][1];
				int temp = rollinfo[i][1];
				
				for(int j=1; j<=3; j++) {
					if(!same[j]) {
						temp = temp * -1;
						rolling[j+1] = temp;
						
					}
					else {break;}
				}
				
			}
			else if(rollinfo[i][0] == 2) {
				
				rolling[2] = rollinfo[i][1];
				int temp = rollinfo[i][1];
				
				if(!same[1]) {
					rolling[1] = temp * -1;
				}
				
				for(int j=2; j<=3; j++) {
					if(!same[j]) {
						temp = temp * -1;
						rolling[j+1] = temp;
					}
					else {break;}
				}
				
				
			}
			else if(rollinfo[i][0] == 3) {
				rolling[3] = rollinfo[i][1];
				int temp = rollinfo[i][1];
				
				if(!same[3]) {
					rolling[4] = temp * -1;
				}
				
				for(int j=2; j>=1; j--) {
					if(!same[j]) {
						temp = temp * -1;
						rolling[j] = temp;
					}
					else {break;}
				}
			}
			else {
				rolling[4] = rollinfo[i][1];
				int temp = rollinfo[i][1];
				
				for(int j=3; j>=1; j--) {
					if(!same[j]) {
						temp = temp*-1;
						rolling[j] = temp;
					}
					else {break;}
				}
			}
			
			
			for(int j=1; j<=4; j++) {
				if(rolling[j] == -1) {
					
					int temp = mat[j][0];
					for(int k=0; k<=6; k++) {
						mat[j][k] = mat[j][k+1];
					}
					mat[j][7] = temp;
					
					
				}
				else if(rolling[j] == 1) {
					
					int temp = mat[j][7];
					for(int k=7; k>=1; k--) {
						mat[j][k] = mat[j][k-1];
					}
					mat[j][0] = temp;
				}
				else {
					continue;
				}

			}
			/*
			for(int a=1; a<=4; a++) {
				for(int b=0; b<=7; b++) {
					System.out.print(mat[a][b]+" ");
				}
				System.out.println();
			}
			
			for(int j=1; j<=4; j++) {
				System.out.print(rolling[j]+" ");
			}
			System.out.println();
			//rolling을 토대로 mat 갱신*/
			
		}
		
		int score = 0;
		int[] scores = {1,2,4,8};
		for(int i=1; i<=4; i++) {
			if(mat[i][0]==1) {
				score += scores[i-1];
			}
		}
		System.out.println(score);
		
		
	}

}
