package no1520내리막길;

/*
 * return 쓸때 초반에 아무거나 집어넣고 나중에 까먹어서 삽질하지 말자;;
 * 
 * DFS+DP문제
 * 한번 갔던루트는 다시 갈 필요없이 그 결과를 DP배열에 쓰는 방식으로 해결해야함
 * 
 * 
 * 
 * 
 */

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Main {
	
	static int row, col;
	static int[][] map;
	static int[][] counts;
	static boolean[][] visit;
	static int[] vecR = {0,1,0,-1};
	static int[] vecC = {1,0,-1,0};
	
	static int dfs(int r, int c) {

		if(r==row-1 && c==col-1) {return 1;} //목적지 도착할 시 '1'반환

		if(visit[r][c]) {return counts[r][c];} //한번 갔던 곳이면 그곳의 dp배열 반환
		
		int nextr, nextc;
		
		visit[r][c] = true;
		
		for(int i=0; i<4; i++) {
			nextr = r+vecR[i];
			nextc = c+vecC[i];
			
			if(check(nextr,nextc) && (map[r][c]>map[nextr][nextc])) { //내리막길이면
				
					counts[r][c] += dfs(nextr,nextc); //dfs진입 (기존의 dp배열에 더해준다)

			}
		}
		//목적지도 아니면서 한번 갔던곳도 아니라면, for문을 돌아 해당 r,c에서의 목적지까지의 경우의 수를 구함
		//그 수를 리턴
		return counts[r][c]; 
		
	}
	
	static boolean check(int r, int c) {
		if(r>=0 && r<row && c>=0 && c<col) {return true;}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		String[] st1 = br.readLine().split(" ");
		
		row = Integer.parseInt(st1[0]);
		col = Integer.parseInt(st1[1]);
		
		map = new int[row][col];
		counts = new int[row][col];
		visit = new boolean[row][col];
		
		for(int r=0; r<row; r++) {
			String[] st2 = br.readLine().split(" ");
			
			for(int c=0; c<col; c++) {
				map[r][c] = Integer.parseInt(st2[c]);
				//counts[r][c] = -1;
			}
		}
		
		dfs(0,0);
		System.out.println(counts[0][0]);
		
	}

}
