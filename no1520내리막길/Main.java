package no1520��������;

/*
 * return ���� �ʹݿ� �ƹ��ų� ����ְ� ���߿� ��Ծ �������� ����;;
 * 
 * DFS+DP����
 * �ѹ� ������Ʈ�� �ٽ� �� �ʿ���� �� ����� DP�迭�� ���� ������� �ذ��ؾ���
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

		if(r==row-1 && c==col-1) {return 1;} //������ ������ �� '1'��ȯ

		if(visit[r][c]) {return counts[r][c];} //�ѹ� ���� ���̸� �װ��� dp�迭 ��ȯ
		
		int nextr, nextc;
		
		visit[r][c] = true;
		
		for(int i=0; i<4; i++) {
			nextr = r+vecR[i];
			nextc = c+vecC[i];
			
			if(check(nextr,nextc) && (map[r][c]>map[nextr][nextc])) { //���������̸�
				
					counts[r][c] += dfs(nextr,nextc); //dfs���� (������ dp�迭�� �����ش�)

			}
		}
		//�������� �ƴϸ鼭 �ѹ� �������� �ƴ϶��, for���� ���� �ش� r,c������ ������������ ����� ���� ����
		//�� ���� ����
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
