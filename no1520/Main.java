package no1520;

/*
 * �������� �������µ� ������ ���ڰ� �۾ƾ� �Ѵ�.
 * �����¿� �̵��� �� �� �ִ�.
 * N,M���� ������ �Ѵ�
 * 
 * DFs + DP ����
 * MN���� ���� ���߿� �̹� �湮�� ���̶�� ������ NM������ ��θ� ������ ����, ��ΰ� ���ٸ� 0, �ִٸ� n
 * MN������ return 1
 * 
 * �̹� �湮�ߴٸ� �ش� dp���� ������
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
		if(dp[r][c]>-1) {return dp[r][c];} //�̹� �湮�߱⿡ �ش� dp�� �������ش�
		
		dp[r][c] = 0;
		int nextr, nextc;
		
		for(int i=0; i<4; i++) {
			nextr = r+vecR[i];
			nextc = c+vecC[i];
			
			if(check(nextr, nextc)) {
				if(map[nextr][nextc]<map[r][c]) { //���������̶��
					
					dp[r][c] += dfs(nextr, nextc); //�������ش� �̶� dp[r][c]�� ���� �湮���� �ʾұ⿡ ��� ����
					//���⼭ DP��� ������
				}
			}
			
		}
		
		
		return dp[r][c]; //r,c���� 4���� Ž������ ���� �������� dp[r][c]�� ��ȯ����
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
