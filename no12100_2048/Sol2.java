

import java.io.*;
import java.util.*;

public class Sol2 {
	
	static int N;
	static int map1[][];
	static int max = 0;
	static int outerremain;
	
	static final int UP = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	static final int RIGHT = 4;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < testcase; tc++) {
			N = Integer.parseInt(br.readLine());
			map1 = new int[N][N];
			int remain = 0;
			max=0;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map1[i][j] = Integer.parseInt(st.nextToken());
					if (map1[i][j] != 0) {
						remain++;
					}
				}
			}

			for (int i = 1; i <= 4; i++) {
				dfs(map1, i, 1, remain);
			}

			System.out.println(max);

		}
		
	}
	
	
	static void dfs(int[][] map, int dir, int depth, int remain) {

		
		if(remain==1) {
			find_max(map); return;
		}
		
		
		if(depth >=6) {
			find_max(map); return;
		}
		
		int originremain = remain;
		for(int i=1; i<=4; i++) {
			
			int[][] innerMap = new int[N][N];
			
			innerMap = move(map, i);
			innerMap = press(innerMap, i, remain);
			remain = outerremain;
			innerMap = move(innerMap, i);
			
			
			dfs(innerMap, i, depth+1, remain);
			remain = originremain;
			
		}
		
		
		
	}
	
	static int[][] move(int[][] map, int dir){
		
		int[][] temp = new int[N][N];
		
		switch(dir) {
		
			case UP:
				for(int col=0; col<N; col++) {
					
					int tempRow = 0;
					for(int row=0; row<N; row++) {
						
						if(map[row][col] != 0) {
							temp[tempRow][col] = map[row][col];
							tempRow++;
						}
					}
				}
				break;
				
			case DOWN:
				for(int col=0; col<N; col++) {
					
					int tempRow=N-1;
					for(int row=N-1; row>=0; row--) {
						
						if(map[row][col]!=0) {
							temp[tempRow][col] = map[row][col];
							tempRow--;
						}
					}
				}
				break;
				
			case LEFT:
				for(int row=0; row<N; row++){
					
					int tempCol = 0;
					for(int col=0; col<N; col++) {
						
						if(map[row][col]!=0) {
							temp[row][tempCol] = map[row][col];
							tempCol++;
						}
					}
				}
				break;
				
			case RIGHT:
				for(int row = 0; row<N; row++) {
					
					int tempCol = N-1;
					for(int col=N-1; col>=0; col--) {
						
						if(map[row][col]!=0) {
							temp[row][tempCol] = map[row][col];
							tempCol--;
						}
					}
				}
				break;

		}
		
		return temp;
	}
	
	static int[][] press(int[][] map, int dir, int remain){
		
		
		switch(dir) {
		
			case UP:
				for(int col = 0; col<N; col++) {
					int before = 0;
					for(int row=0; row<N; row++) {
						if(map[row][col]==0) {break;}
						
						if(before==0 || (before!=0 && before!=map[row][col])) {
							before = map[row][col];
							map[row][col] = before;
						}
						else if(before == map[row][col]) {
							map[row][col] = map[row][col]*2; remain--;
							map[row-1][col] = 0;
							before = 0;
						}
					}
				}
				break;
			
			case DOWN:
				for(int col=0; col<N; col++) {
					int before = 0;
					for(int row = N-1; row>=0; row--) {
						if(map[row][col]==0) {break;}
						
						if(before==0 || (before!=0 && before!=map[row][col])) {
							before = map[row][col];
							map[row][col] = before;
						}
						else if(before == map[row][col]) {
							map[row][col] = map[row][col]*2; remain--;
							map[row+1][col] = 0;
							before = 0;
						}
						
					}
				}
				break;
				
			case LEFT:
				for(int row=0; row<N; row++) {
					int before = 0;
					for(int col=0; col<N; col++) {
						if(map[row][col]==0) {break;}
						
						if(before==0 || (before!=0 && before!=map[row][col])) {
							before = map[row][col];
							map[row][col] = before;
						}
						else if(before == map[row][col]) {
							map[row][col] = map[row][col]*2; remain--;
							map[row][col-1] = 0;
							before = 0;
						}
					}
				}
				break;
				
			case RIGHT:
				for(int row=0; row<N; row++) {
					int before = 0;
					for(int col=N-1; col>=0; col--) {
						if(map[row][col]==0) {break;}
						
						if(before==0 || (before!=0 && before!=map[row][col])) {
							before = map[row][col];
							map[row][col] = before;
						}
						else if(before == map[row][col]) {
							map[row][col] = map[row][col]*2; remain--; 
							map[row][col+1] = 0;
							before = 0;
						}
					}
				}
				break;
				
		}
		
		
		outerremain = remain;
		return map;
		
	}
	
	static void find_max(int[][] map) {
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c]>max) {
					max=map[r][c];
				}
			}
		}
	}

}
