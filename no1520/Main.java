package no1520;

/*
 * 내리막을 내려가는데 무조건 숫자가 작아야 한다.
 * 상하좌우 이동을 할 수 있다.
 * N,M까지 가려고 한다
 * 
 * DFs + DP 문제
 * MN까지 가는 도중에 이미 방문한 곳이라면 이전에 NM까지의 경로를 구했을 것임, 경로가 없다면 0, 있다면 n
 * MN도착시 return 1
 * 
 * 이미 방문했다면 해당 dp값을 더해줌
 * 
 * 
 */
import java.io.*;
import java.util.*;

public class Main {

	static int M,N;
	static int[][] map;
	static int[][] dp;
	static int[]  vecR = {0,1,0,-1};
	static int[] vecC = {1,0,-1,0};
	
	static int dfs(int r, int c) {
		
		if(r==M-1 && c==N-1) {return 1;}
		if(dp[r][c]>-1) {return dp[r][c];} //이미 방문했기에 해당 dp를 리턴해준다
		
		dp[r][c] = 0;
		int nextr, nextc;
		
		for(int i=0; i<4; i++) {
			nextr = r+vecR[i];
			nextc = c+vecC[i];
			
			if(check(nextr, nextc)) {
				if(map[nextr][nextc]<map[r][c]) { //내리막길이라면
					
					dp[r][c] += dfs(nextr, nextc); //진행해준다 이때 dp[r][c]는 아직 방문하지 않았기에 계속 진행
					//여기서 DP계속 더해줌
				}
			}
			
		}
		
		
		return dp[r][c]; //r,c에서 4방향 탐색으로 얻은 최종적인 dp[r][c]를 반환해줌
	}
	
	static boolean check(int r, int c) {
		if(r>=0 && r<M && c>=0 && c<N ) return true;
		return false;
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine()," ");
		//M = r N = c
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		dp = new int[M][N];
		
		for(int r=0; r<M; r++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
	
		for(int r=0; r<M; r++) {
			for(int c=0; c<N; c++) {
				dp[r][c] = -1;
			}
		}
		
		dfs(0,0);
		for(int ar=0; ar<M ;ar++) {
			for(int ac = 0; ac<N; ac++) {
				System.out.print(dp[ar][ac]+" ");
			}System.out.println();
		}
		System.out.println(dp[0][0]);
	}

}
